package jouette;

import frame.Frame;
import temp.Temp;
import temp.TempList;
import tree.BINOP;
import tree.CALL;
import tree.CJUMP;
import tree.CONST;
import tree.EXP;
import tree.JUMP;
import tree.MEM;
import tree.SEQ;
import tree.NAME;

public class Codegen {

	Frame frame;
	private assem.InstrList ilist = null, last = null;

	public Codegen(Frame f) {
		frame = f;
	}

	private void emit(assem.Instr inst) {
		if (last != null)
			last = last.tail = new assem.InstrList(inst, null);
		else
			last = ilist = new assem.InstrList(inst, null);
	}

	void munchStm(tree.Stm s) {
		if (s instanceof SEQ) {
			munchStm(((tree.SEQ) s).left);
			munchStm(((tree.SEQ) s).right);
		}
		if (s instanceof tree.MOVE)
			munchMove(((tree.MOVE) s).dst,((tree.MOVE) s).src);
		if (s instanceof tree.LABEL)
			emit(new assem.LABEL(s.toString() + ":\n", ((tree.LABEL) s).label));

		if (s instanceof tree.JUMP)
			munchJump((tree.JUMP) s);
		if (s instanceof tree.CJUMP)//incompleto, a espera da resposta do professor
			munchCJump((tree.CJUMP) s);
		else
			//munchStm(EXP(CALL (e,args)))
			{Temp r = munchExp(((EXP)s).exp);
			 TempList l = munchArgs(0,args);
			 emit(new assem.OPER("CALL ‘s0\n",calldefs,L(r,l)));}

			munchExp(((tree.EXP)s).exp);

	}
	
	private void munchCJump(CJUMP s) {
		Temp r = munchExp(new BINOP(BINOP.MINUS,s.left,s.right));
        if(s.relop==CJUMP.EQ){
                emit(new assem.OPER("BRANCHEQ if "+r+" = 0 goto "+s.iftrue, null,
                                new temp.TempList(r,null), new temp.LabelList(s.iftrue, null)));
        }
        else if(s.relop==CJUMP.GE){
                emit(new assem.OPER("BRANCHGE if "+r+" >= 0 goto "+s.iftrue, null,
                                new temp.TempList(r,null), new temp.LabelList(s.iftrue, null)));
        }
        else if(s.relop==CJUMP.LT){
                emit(new assem.OPER("BRANCHLT if "+r+ " < 0 goto "+s.iftrue, null,
                                new temp.TempList(r,null), new temp.LabelList(s.iftrue, null)));
        }
        else if(s.relop==CJUMP.NE){
                emit(new assem.OPER("BRANCHNE if "+r+" != 0 goto "+s.iftrue, null,
                                new temp.TempList(r,null), new temp.LabelList(s.iftrue, null)));
        }else
        emit(new assem.OPER("goto "+s.iffalse.toString(), null, null, new temp.LabelList(s.iffalse, null)));  
		// TODO Auto-generated method stub

	}

	private void munchJump(JUMP s) {
		emit(new assem.OPER("goto "+((tree.NAME)s.exp).label.toString(), null, null, new temp.LabelList(((tree.NAME)s.exp).label, null))); 
		// TODO Auto-generated method stub

	}

	void munchMove(tree.MEM dst, tree.Exp src) {
		// MOVE(MEM(BINOP(PLUS, e1 , CONST(i))), e2 )
		if (dst.exp instanceof BINOP && ((BINOP)dst.exp).binop==BINOP.PLUS
		&& ((BINOP)dst.exp).right instanceof CONST)
		{
			//munchExp(((BINOP)dst.exp).left); munchExp(src);// emit("STORE");
			temp.TempList destino = new temp.TempList(munchExp(((BINOP)dst.exp).left),null);
            
			temp.TempList fonte = new temp.TempList(munchExp(src),null);
            
            emit(new assem.OPER("STORE M["+frame.FP() + " + "+destino.head+" + "+((CONST)((BINOP)dst.exp).right).value + " ] <- "+fonte.head, destino, fonte));
		}
		// MOVE(MEM(BINOP(PLUS, CONST(i), e1 )), e2 )
		else if (dst.exp instanceof BINOP && ((BINOP)dst.exp).binop==BINOP.PLUS
		&& ((BINOP)dst.exp).left instanceof CONST)
		{
			//munchExp(((BINOP)dst.exp).right); munchExp(src);// emit("STORE");
			
			temp.TempList destino = new temp.TempList(munchExp(((BINOP)dst.exp).right),null);
            
            temp.TempList fonte = new temp.TempList(munchExp(src),null);
                                    
            emit(new assem.OPER("STORE M["+frame.FP() + " + "+destino.head+" + " + ((CONST)((BINOP)dst.exp).left).value + "] <- "+fonte.head,destino, fonte));
		}
		
		// MOVE(MEM(e1 ), MEM(e2 ))
		else if (src instanceof MEM)
		{
			temp.TempList destino = new temp.TempList(munchExp(dst.exp), null);
			temp.TempList fonte = new temp.TempList(munchExp(src), null);

			emit(new assem.OPER("MOVEM M["+frame.FP() + " + "+destino.head+"] <- M["+fonte.head+"]", destino, fonte));
			//munchExp(dst.exp); munchExp(((MEM)src).exp); //emit("MOVEM");
		}
		
		//MOVE(MEM(CONST(i)),e2)
        else if(dst.exp instanceof MEM && ((MEM)dst.exp).exp instanceof CONST){

        	temp.TempList destino = new temp.TempList(munchExp(dst.exp), null);
			temp.TempList fonte = new temp.TempList(munchExp(src), null);
                
                emit(new assem.OPER("STORE M["+frame.FP() + " + "+((CONST)((MEM)dst.exp).exp).value + "] <- "+fonte.head,destino,fonte,null));
        }
		
		// MOVE(MEM(e1 ), e2 )
		else
		{
			temp.TempList destino = new temp.TempList(munchExp(dst.exp), null);
			temp.TempList fonte = new temp.TempList(munchExp(src), null);
			//munchExp(dst.exp); munchExp(src); //emit("STORE");
			emit(new assem.OPER("STORE M["+frame.FP() + " + "+destino.head+" + 0] <- "+fonte.head, destino, fonte));
		}

	}
	
	void munchMove(tree.TEMP dst, tree.Exp src) {
		 // MOVE(TEMP(t1), e) 
        Temp t =  munchExp(src); 
        emit(new assem.MOVE("MOVEA "+dst.temp+" <- "+t, dst.temp,t));
		// MOVE(TEMP(t1 ), e)
		//munchExp(src);// emit("ADD");
	}
	void munchMove(tree.Exp dst, tree.Exp src) {
		// MOVE(d, e)
		//Primeiro é o caso de uma posicao de memoria, segundo o de um temporario
		if (dst instanceof MEM) munchMove((MEM)dst,src);
		else if (dst instanceof tree.TEMP) munchMove((tree.TEMP)dst,src);
	}


	temp.Temp munchExp(tree.Exp s) {
		if (s instanceof tree.MEM)
			return munchMem((tree.MEM) s);
		
		if (s instanceof tree.CONST) {
			temp.Temp r = new temp.Temp();
			emit(new assem.OPER("ADDI ‘d0 <- r0+" + ((tree.CONST) s).value
					+ "\n", new temp.TempList(r, null), null));
			return r;
		}

		if (s instanceof tree.BINOP)
			return munchBinop((tree.BINOP) s);
		if (s instanceof tree.TEMP)
			return ((tree.TEMP) s).temp;
		else //implementar
			return munchCall((tree.CALL)s);
	}

	private Temp munchCall(CALL s) {
		// TODO Auto-generated method stub
		return null;
	}

	private Temp munchBinop(BINOP s) {
		//munchExp(BINOP(PLUS,e1,CONST (i)))
		if(s.binop == BINOP.PLUS && s.right instanceof CONST)
		{
			Temp r = new Temp();
			TempList fonte = new temp.TempList(munchExp(s.left), null);
			emit(new assem.OPER("ADDI ‘d0 <- " +fonte.head+" + " + ((CONST)s.right).value + "\n",
			new temp.TempList(r,null), fonte));
			return r;
			
		}
		
		//munchExp(BINOP(PLUS,CONST (i),e1))
		if(s.binop == BINOP.PLUS && s.left instanceof CONST)
		{
			Temp r = new Temp();
			TempList fonte = new temp.TempList(munchExp(s.right), null);
			emit(new assem.OPER("ADDI ‘d0 <- " +fonte.head+" + " + ((CONST)s.left).value + "\n",
			new temp.TempList(r,null), fonte));
			return r;
			
		}
		
		//munchExp(BINOP(PLUS,e1,e2))
		if(s.binop == BINOP.PLUS)
		{
			Temp r = new Temp();
			TempList fonte = new temp.TempList(munchExp(s.left), new temp.TempList(munchExp(s.right), null));
			emit(new assem.OPER("ADD ‘d0 <- " +fonte.head +"+" +fonte.tail.head + "\n",
			new temp.TempList(r,null), fonte));
			return r;
		}
		
		//munchExp(BINOP(MUL,e1,e2))
		if(s.binop == BINOP.MUL)
		{
			Temp r = new Temp();
			TempList fonte = new temp.TempList(munchExp(s.left), new temp.TempList(munchExp(s.right), null));
			emit(new assem.OPER("MUL ‘d0 <- " +fonte.head +"*" +fonte.tail.head + "\n",
			new temp.TempList(r,null), fonte));
			return r;
		}
				
				
		//munchExp(BINOP(DIV,e1,e2))
		if(s.binop == BINOP.DIV)
		{
			Temp r = new Temp();
			TempList fonte = new temp.TempList(munchExp(s.left), new temp.TempList(munchExp(s.right), null));
			emit(new assem.OPER("DIV ‘d0 <- " +fonte.head +"/" +fonte.tail.head + "\n",
			new temp.TempList(r,null), fonte));
			return r;
		}
		
		//munchExp(BINOP(SUB,e1,CONST(i)))
		if(s.binop == BINOP.MINUS && s.right instanceof CONST)
		{
			Temp r = new Temp();
			TempList fonte = new temp.TempList(munchExp(s.left), null);
			emit(new assem.OPER("SUBI ‘d0 <- " +fonte.head+" - " + ((CONST)s.right).value + "\n",
			new temp.TempList(r,null), fonte));
			return r;
			
		}
		
		//munchExp(BINOP(SUB,e1,e2))
		if(s.binop == BINOP.MINUS)
		{
			Temp r = new Temp();
			TempList fonte = new temp.TempList(munchExp(s.left), new temp.TempList(munchExp(s.right), null));
			emit(new assem.OPER("SUB ‘d0 <- " +fonte.head +"-" +fonte.tail.head + "\n",
			new temp.TempList(r,null), fonte));
			return r;
		}
		// TODO Auto-generated method stub
		return null;
	}

	private Temp munchMem(MEM s) {
		//munchExp(MEM(BINOP(PLUS,e1,CONST(i))))

		if(s.exp instanceof tree.BINOP && (((tree.BINOP)s.exp).binop == tree.BINOP.PLUS) && 
		((tree.BINOP)s.exp).right instanceof tree.CONST)
		{
			Temp r = new Temp();
			emit(new assem.OPER("LOAD ‘d0 <- M[‘s0+" + ((tree.CONST)(((tree.BINOP)s.exp).right)).value + "]\n",
			new temp.TempList(r,null), new temp.TempList(munchExp(((tree.BINOP)s.exp).left),null)));
			return r;
			
		}
		
		//munchExp(MEM(BINOP(PLUS,CONST(i),e1)))

		if(s.exp instanceof tree.BINOP && (((tree.BINOP)s.exp).binop == tree.BINOP.PLUS) && 
			((tree.BINOP)s.exp).left instanceof tree.CONST)
		{
			Temp r = new Temp();
			emit(new assem.OPER("LOAD ‘d0 <- M[‘s0+" + ((tree.CONST)(((tree.BINOP)s.exp).left)).value + "]\n",
			new temp.TempList(r,null), new temp.TempList(munchExp(((tree.BINOP)s.exp).right),null)));
			return r;
				
		}
		
		//munchExp(MEM(CONST (i)))

		if(s.exp instanceof tree.CONST)
		{
			Temp r = new Temp();
			emit(new assem.OPER("LOAD ‘d0 <- M[r0+" + ((CONST)s.exp).value + "]\n",
			new temp.TempList(r,null), null));
			return r;
		}
		
		//munchExp(MEM(e1))
		Temp r = new Temp();
		emit(new assem.OPER("LOAD ‘d0 <- M[‘s0+0]\n",
		new temp.TempList(r,null), new temp.TempList(munchExp(s.exp),null)));
		return r;

		
		
		
		
		// TODO Auto-generated method stub
	}

	assem.InstrList codegen(tree.Stm s) {
		assem.InstrList l;
		munchStm(s);
		l = ilist;
		ilist = last = null;
		return l;
	}
}
