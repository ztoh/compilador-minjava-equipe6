package translate;

import java.util.Stack;

import frame.Frame;

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
		n.s.accept(this);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(ClassDeclSimple n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(ClassDeclExtends n) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(If n) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(LessThan n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Plus n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Minus n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp visit(Times n) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
