package translate;

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
import tree.BINOP;
import tree.CALL;
import tree.NAME;
import temp.Label;

public class TreeIRVisitor implements VisitorIR {

	Stack<Frame> frames;
	Frame frameAtual;
	ClassTable classes;
	MethodInfo metodo;
	ClassInfo classe;
	
	public TreeIRVisitor(ClassTable k, Frame frameAtual)
	{
		classes = k;
		this.frameAtual = frameAtual;
		frames = new Stack<Frame>();
		frames.push(frameAtual);
		
	}
	
	@Override
	public Exp visit(Program n) {
		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
			
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(MainClass n) {
		Stm body =new EXP(n.s.accept(this).unEx());
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(ClassDeclSimple n) {
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(ClassDeclExtends n) {
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(VarDecl n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(MethodDecl n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Formal n) {
		// TODO Auto-generated method stub
		return null;
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
												
												/*new ESEQ(new SEQ(new CJUMP(CJUMP.GT,cond,new CONST(0),iff,elsee),
														new SEQ(new LABEL(iff),
																new LABEL(elsee)),new CONST(0))));*/


		// TODO Auto-generated method stub
		return new Exp(Cx);
	}

	@Override
	public Exp visit(While n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Print n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Assign n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(ArrayAssign n) {
		// TODO Auto-generated method stub
		return null;
	}

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
		//Checar como implementar o LESSTHAN
		// TODO Auto-generated method stub
		return new Exp(new BINOP(BINOP.PLUS,e1.unEx(),e2.unEx()));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(ArrayLength n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Call n) {
		ClassInfo j = (ClassInfo)classes.get(Symbol.symbol(n.e.toString()));
		MethodInfo x = (MethodInfo)j.getMetodo(Symbol.symbol(n.i.toString()));
		tree.ExpList lista = null;
		for (int i = n.el.size() -1; i >= 0 ; i--) {
			lista = new ExpList(n.el.elementAt(i).accept(this).unEx(),lista);
		}
		//lista = new ExpList(,lista);
		// TODO Auto-generated method stub
		return new Exp(new CALL(new NAME(new Label(j.id.toString()+"$"+n.i.toString())),lista));
	}

	@Override
	public Exp visit(IntegerLiteral n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(True n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(False n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(IdentifierExp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(This n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(NewArray n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(NewObject n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Not n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Identifier n) {
		// TODO Auto-generated method stub
		return null;
	}

}
