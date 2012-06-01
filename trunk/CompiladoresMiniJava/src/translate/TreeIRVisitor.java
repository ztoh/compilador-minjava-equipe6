/*
Carlos Drummond de Andrade

 
No meio do caminho


No meio do caminho tinha uma pedra
tinha uma pedra no meio do caminho
tinha uma pedra
no meio do caminho tinha uma pedra.


Nunca me esquecerei desse acontecimento
na vida de minhas retinas tão fatigadas.
 
Nunca me esquecerei que no meio do caminho
tinha uma pedra
 
Tinha uma pedra no meio do caminho
no meio do caminho tinha uma pedra.
*/


//Ultima coisa a ser feita é ajeitar o Call para checar por atributos da super
//e pegar a classe que tem o metodo do call recursivo

package translate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import frame.Frame;
import symbol.Symbol;
import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.Assign;
import syntaxtree.Block;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.False;
import syntaxtree.Formal;
import syntaxtree.Identifier;
import syntaxtree.IdentifierExp;
import syntaxtree.IdentifierType;
import syntaxtree.If;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.Minus;
import syntaxtree.NewArray;
import syntaxtree.NewObject;
import syntaxtree.Not;
import syntaxtree.Plus;
import syntaxtree.Print;
import syntaxtree.Program;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.VarDecl;
import syntaxtree.While;
import table.ClassInfo;
import table.ClassTable;
import table.MethodInfo;
import table.VarInfo;
import tree.CONST;
import tree.ExpList;
import tree.LABEL;
import tree.MOVE;
import tree.TEMP;
import tree.EXP;
import tree.Stm;
import tree.ESEQ;
import tree.SEQ;
import tree.CJUMP;
import tree.JUMP;
import tree.BINOP;
import tree.CALL;
import tree.NAME;
import tree.MEM;
import temp.Label;
import util.Conversor;

public class TreeIRVisitor implements VisitorIR {

	Stack<Frame> frames;
	Frame frameAtual;
	ClassTable classes;
	MethodInfo metodo;
	ClassInfo classe;
	ArrayList <Frag> fragmentos;
	
	public TreeIRVisitor(ClassTable k, Frame frameAtual)
	{
		classes = k;
		this.frameAtual = frameAtual;
		frames = new Stack<Frame>();
		frames.push(frameAtual);
		fragmentos = new ArrayList<Frag>();
		
	}
	
	public translate.Exp pegarEndereco(Symbol var)
	{
		VarInfo vD;

        if((vD=(VarInfo) metodo.paramEntrada.get(var))!=null);
        else if((vD=(VarInfo) metodo.listaDeVariaveis.get(var))!=null);
        else if ((vD=(VarInfo) classe.get(var))!= null);
        else 
        {
        	ClassInfo aux = (ClassInfo)classes.get(classe.extendedClass);
        	while(aux != null)
        	{
        		vD=(VarInfo) aux.get(var);
        		if( vD != null)
        		{
        			break;
        		}
        	}
        	
        }
        System.out.println(classe.id);
        System.out.println(metodo.id);
        return new Exp(vD.access.exp(new TEMP(frameAtual.FP())));
	}
	
	@Override
	public Exp visit(Program n) {
		
		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			this.classe = (ClassInfo)this.classes.get(Symbol.symbol(n.cl.elementAt(i).toString()));
			System.out.println(i);
			n.cl.elementAt(i).accept(this);
			
		}
		tree.Print h = new tree.Print(System.out);
		Stm temp;
		for (int i = 0; i < fragmentos.size(); i++) {
			temp = fragmentos.get(i).body;
			h.prStm(temp);
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(MainClass n) {
		
		this.classe = (ClassInfo)classes.get(Symbol.symbol(n.i1.toString()));
		ArrayList<Boolean> j = new ArrayList<Boolean>();
		j.add(false);
		frameAtual = frameAtual.newFrame(Symbol.symbol("principal"),j); //new ArrayList<Boolean>());
		frames.push(frameAtual);
		Stm body =new EXP(n.s.accept(this).unEx());
		ArrayList<Stm> lista = new ArrayList<Stm>();
		lista.add(body);
		frameAtual.procEntryExit1(lista);
		fragmentos.add(new Frag(body,frameAtual));
		frames.pop();
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(ClassDeclSimple n) {
		this.classe = (ClassInfo)classes.get(Symbol.symbol(n.i.toString()));
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);	
		}
		
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(ClassDeclExtends n) {
		this.classe = (ClassInfo)classes.get(Symbol.symbol(n.i.toString()));
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);	
		}
		
		alocarVariaveisSuper();
		
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		// TODO Auto-generated method stub
		return null;
	}

	private void alocarVariaveisSuper() {
		ClassInfo aux = (ClassInfo)classes.get(classe.extendedClass);
		VarInfo vD;
		while(aux != null)
		{
			Iterator<Symbol> x = aux.atributos.keys().iterator();
			while(x.hasNext())
			{
				vD = (VarInfo) aux.atributos.get(x.next());
				vD.access = frameAtual.allocLocal(false);
			}
			aux = (ClassInfo)classes.get(aux.extendedClass);
			
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Exp visit(VarDecl n) 
	{
		VarInfo vD;

		if(metodo != null)
		{
			vD=(VarInfo) metodo.paramEntrada.get(Symbol.symbol(n.i.toString()));
			if(vD!= null)
			{
				vD.access = frameAtual.allocLocal(false);
				return null;
			} 
			vD =(VarInfo) metodo.listaDeVariaveis.get(Symbol.symbol(n.i.toString()));
			
			if(vD!= null)
			{
				vD.access = frameAtual.allocLocal(false);
				return null;
			} 
			/*if((vD=(VarInfo) metodo.paramEntrada.get(Symbol.symbol(n.i.toString())))!=null);
	        else if((vD=(VarInfo) metodo.listaDeVariaveis.get(Symbol.symbol(n.i.toString())))!=null);
			vD.access = frameAtual.allocLocal(false);*/
		}
        
        vD=(VarInfo) classe.get(Symbol.symbol(n.i.toString()));
        if(vD!= null)
		{
			vD.access = frameAtual.allocLocal(false);
		} 
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(MethodDecl n) {
		
		
		Stm corpo = new EXP( new CONST(0));
		ArrayList<Boolean> j = new ArrayList<Boolean>();
		metodo = (MethodInfo)classe.getMetodo(Symbol.symbol(n.id.toString()));
		if(metodo == null)
			System.out.println("teste");
		for (int i = 0; i <= n.fl.size(); i++) {
			j.add(false);
		}
		frameAtual = frameAtual.newFrame(Symbol.symbol(classe.toString()+"$"+ metodo.toString()), (java.util.List<Boolean>)j);
		frames.push(frameAtual);
		
		for (int i = 0; i < n.fl.size(); i++) {
			n.fl.elementAt(i).accept(this);
		}
		
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);	
		}
		
		for (int i = 0; i < n.sl.size(); i++) {
			corpo = new SEQ(corpo,new EXP(n.sl.elementAt(i).accept(this).unEx()));
		}
		ArrayList<Stm> l = new ArrayList<Stm>();
		l.add(corpo);
		frameAtual.procEntryExit1(l);
		fragmentos.add(new Frag(corpo,frameAtual));
		metodo = null;
		frames.pop();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Formal n) {
		
		VarInfo vD =(VarInfo) metodo.paramEntrada.get(Symbol.symbol(n.i.toString()));

        
		vD.access = frameAtual.allocLocal(false);
		
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Exp visit(Block n) {
		tree.Exp stm = new CONST(0);
		for (int i = 0; i < n.sl.size(); i++) {
			stm = new ESEQ(new SEQ(new EXP(stm),new EXP(n.sl.elementAt(i).accept(this).unEx())),new CONST(0));
		}
		// TODO Auto-generated method stub
		return new Exp(stm);
	}

	@Override
	public Exp visit(If n) {
		Label iff = new Label();
		Label elsee = new Label();
		tree.Exp cond = n.e.accept(this).unEx();
		Exp label1 = n.s1.accept(this);
		Exp label2 = n.s2.accept(this);
		tree.Exp Cx = new ESEQ(new SEQ(new CJUMP(CJUMP.GT,cond,new CONST(0),iff,elsee),new SEQ(new SEQ(new LABEL(iff),new EXP(label1.unEx())),new SEQ(new LABEL(elsee),new EXP(label2.unEx())))),new CONST(0));

		// TODO Auto-generated method stub
		return new Exp(Cx);
	}

	@Override
	public Exp visit(While n) {
		Label teste = new Label();
		Label body = new Label();
		Label done = new Label();
		Exp cond = n.e.accept(this);
		Exp stm = n.s.accept(this);
		
		// TODO Auto-generated method stub
		return new Exp (new ESEQ(
                new SEQ(new LABEL(teste),
                        new SEQ(
                                new CJUMP(CJUMP.GT,cond.unEx(),new CONST(0),body,done),
                                new SEQ(new LABEL(body), new SEQ(new EXP(stm.unEx()),new JUMP(teste)))
                        )
        ), new CONST(0)));
	}

	@Override
	public Exp visit(Print n) {
		Exp expressao = n.e.accept(this);
		tree.ExpList parametros= new tree.ExpList(expressao.unEx(),null); 
		// TODO Auto-generated method stub
		return new Exp( frameAtual.externalCall("print", Conversor.converterExpList(parametros)));
	}

	@Override
	public Exp visit(Assign n) {
		Exp i = n.i.accept(this);
		Exp e = n.e.accept(this);
		// TODO Auto-generated method stub
		return new Exp( new ESEQ (new MOVE( i.unEx(),e.unEx() )  ,new CONST(0)));
	}

	@Override
	public Exp visit(ArrayAssign n) {
		
		Exp i = n.i.accept(this);
		Exp e1 = n.e1.accept(this);
		Exp e2 = n.e2.accept(this);
		// TODO Auto-generated method stub
		return new Exp( new ESEQ(new MOVE(new MEM(new BINOP(BINOP.PLUS,i.unEx(),new BINOP(BINOP.MUL,e1.unEx(), new CONST(frameAtual.wordSize())))  )  ,e2.unEx() )    , new CONST(0))    );
	}
	//
	@Override
	public Exp visit(And n) {
		Exp e1 = n.e1.accept(this);
		Exp e2 = n.e2.accept(this);
		
		// TODO Auto-generated method stub
		return new Exp(new BINOP(BINOP.AND,e1.unEx(),e2.unEx()));
		// TODO Auto-generated method stub
	}

	@Override
	public Exp visit(LessThan n) {
		Exp e1 = n.e1.accept(this);
		Exp e2 = n.e2.accept(this);
		// TODO Auto-generated method stub
		return new Exp(new BINOP(BINOP.MINUS,e2.unEx(),e1.unEx()));
	}

	@Override
	public Exp visit(Plus n) {
		Exp e1 = n.e1.accept(this);
		Exp e2 = n.e2.accept(this);
		
		// TODO Auto-generated method stub
		return new Exp(new BINOP(BINOP.PLUS,e1.unEx(),e2.unEx()));
	}

	@Override
	public Exp visit(Minus n) {
		Exp e1 = n.e1.accept(this);
		Exp e2 = n.e2.accept(this);
		
		// TODO Auto-generated method stub
		return new Exp(new BINOP(BINOP.MINUS,e1.unEx(),e2.unEx()));
	}

	@Override
	public Exp visit(Times n) {
		Exp e1 = n.e1.accept(this);
		Exp e2 = n.e2.accept(this);
		
		// TODO Auto-generated method stub
		return new Exp(new BINOP(BINOP.MUL,e1.unEx(),e2.unEx()));
	}

	@Override
	public Exp visit(ArrayLookup n) {
		Exp e1 = n.e1.accept(this);
		Exp e2 = n.e2.accept(this);
		//Adicionando 1 porque a primeira posicao é para o tamanho do vetor
		// TODO Auto-generated method stub
		return new Exp(new BINOP(BINOP.PLUS,e1.unEx()  ,new BINOP(BINOP.MUL,new BINOP(BINOP.PLUS,new CONST(1) ,e2.unEx()) ,new CONST(frameAtual.wordSize())    )     )       );
	}

	@Override
	public Exp visit(ArrayLength n) {
		// TODO Auto-generated method stub
		return pegarEndereco(Symbol.symbol(((IdentifierExp)n.e).s));
	}

	@Override
	public Exp visit(Call n) {
		
		ClassInfo j = null; 
		MethodInfo r = null;
		if(n.e instanceof This )
		{
			j = this.classe;
			
			while ((r = (MethodInfo)classe.get(j.id)) == null){
				j = (ClassInfo)classes.get(j.extendedClass);
			}
			
		}
		
		else
		{
			if(n.e instanceof NewObject)
			{
				j = (ClassInfo)classes.get(Symbol.symbol(n.e.toString()));
			}
			else
			{
				VarInfo aux;
				aux = (VarInfo)metodo.listaDeVariaveis.get(Symbol.symbol(n.e.toString()));
				if( aux != null)
				{
					j = (ClassInfo)classes.get(Symbol.symbol(aux.gettype().toString()));
				}
				else
				{
					aux = (VarInfo)metodo.paramEntrada.get(Symbol.symbol(n.e.toString()));
					if( aux != null)
					{
						j = (ClassInfo)classes.get(Symbol.symbol(aux.gettype().toString()));
					}
					else
					{
						aux = (VarInfo)classe.get(Symbol.symbol(n.e.toString()));//deve procurar pelos atributos super aqui
						if( aux != null)
						{
							j = (ClassInfo)classes.get(Symbol.symbol(aux.gettype().toString()));
						}
						else
						{
							if( n.e instanceof Call)//Expressao do tipo call
							{
								syntaxtree.Exp ajudante = n.e;
								int i = 0;
								while (ajudante instanceof Call)
								{
									i++;
									ajudante = n.e;
								}
								
								/*Iterator<Symbol> temp = classes.keys().iterator();
								ClassInfo temp2;
								while(temp.hasNext())
								{
									temp2 = (ClassInfo)classes.get(temp.next());
								}*/
								//Procura em todas as classes onde esta este metodo para saber seu retorno(classe de retorno)
								MethodInfo aux2 = null; //= (MethodInfo)classe.getMetodo(Symbol.symbol(((Call)n.e).i.toString()));
								if(aux2 != null)//Pode ser da superclasse,ter que checar
								{
									j = (ClassInfo)classes.get(aux2.retorno);
								}
							}
							else//Procurar em que superclasse esta o atributo
							{
								
							}
						}
					}
				}
				
			}
			//j = (ClassInfo)classes.get(Symbol.symbol(n.e.toString()));
		}
		
		if( j == null)
		{
			
			System.out.println("TESTE");
			System.out.println(classe.id);
			System.out.println(metodo.id);
			System.out.println(n.e);
			System.out.println(n.i);
		}
		
		tree.ExpList lista = null;
		for (int i = n.el.size() -1; i >= 0 ; i--) {
			lista = new ExpList(n.el.elementAt(i).accept(this).unEx(),lista);
		}
		
		lista = new ExpList(n.e.accept(this).unEx(),lista);
		
		// TODO Auto-generated method stub
		
		return new Exp(new CALL(new NAME(new Label(j.id.toString()+"$"+n.i.toString())),lista));
	}

	@Override
	public Exp visit(IntegerLiteral n) {
		
		// TODO Auto-generated method stub	
		return new Exp(new CONST(n.i));
	}

	@Override
	public Exp visit(True n) {
		// TODO Auto-generated method stub
		return new Exp(new CONST(1));
	}

	@Override
	public Exp visit(False n) {
		// TODO Auto-generated method stub
		return new Exp(new CONST(0));
	}

	@Override
	public Exp visit(IdentifierExp n) {
		// TODO Auto-generated method stub
		return pegarEndereco(Symbol.symbol(n.s));
	}	

	@Override
	public Exp visit(This n) {
		
		// TODO Auto-generated method stub
		return new Exp(new MEM(new TEMP( frameAtual.FP() )));
	}

	@Override
	public Exp visit(NewArray n) {
		Exp tam= n.e.accept(this);
		tree.Exp aloc = new BINOP(BINOP.MUL, new BINOP(BINOP.PLUS,tam.unEx(),new CONST(1)) ,new CONST(frameAtual.wordSize()));
		ExpList parametros = new ExpList(aloc,null);
		tree.Exp retorno = frameAtual.externalCall("malloc", Conversor.converterExpList(parametros));
		// TODO Auto-generated method stub
		return new Exp(retorno);
	}

	@Override
	public Exp visit(NewObject n) {
		ClassInfo j = (ClassInfo)classes.get(Symbol.symbol(n.i.toString()));
		int tam = j.atributos.size();
		
		while(classes.get(j.extendedClass)!= null)
		{	
			System.out.println("teste");
			j = (ClassInfo)classes.get(j.extendedClass);
			tam += j.atributos.size();
		}
		ExpList parametros = new ExpList(new BINOP(BINOP.MUL,new CONST(1+tam) , new CONST(frameAtual.wordSize())), null);
		// TODO Auto-generated method stub
		return new Exp( frameAtual.externalCall("malloc", Conversor.converterExpList(parametros)));
	}

	@Override
	public Exp visit(Not n) {
		Exp e = n.e.accept(this);
		
		// TODO Auto-generated method stub
		return new Exp(new BINOP(BINOP.MINUS,new CONST(1),e.unEx()));
	}

	@Override
	public Exp visit(Identifier n) {
		// TODO Auto-generated method stub
		return pegarEndereco(Symbol.symbol(n.s));
	}
	
	@Override
	public Exp visit(IntArrayType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(BooleanType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(IntegerType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(IdentifierType n) {
		// TODO Auto-generated method stub
		return null;
	}	

}
	