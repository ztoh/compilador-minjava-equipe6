package typechecking;

import java.util.Stack;

import error.Erro;

import symbol.Symbol;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.Assign;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.False;
import syntaxtree.Formal;
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
import syntaxtree.While;
import table.ClassInfo;
import table.ClassTable;
import table.Table;

public class ConcreteTypeCheckVisitor implements TypeCheckVisitor {

	ClassTable contexto;
	Stack <Table> scope;
	
	@Override
	public ClassTable visit(Program n) {
		scope = new Stack<Table>();
		ImperativeSymbolTableVisitor vis = new ImperativeSymbolTableVisitor();
		Table k = vis.visit(n);
		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		// TODO Auto-generated method stub
		return (ClassTable) k;
	}
	
	public Table beginScope(Symbol key)
	{
		Table t =(Table) this.contexto.get(key);
		scope.push(t);
		return t;
	}
	
	public void endScope()
	{
		scope.pop();
	}

	@Override
	public void visit(MainClass n) {
		n.s.accept(this);
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ClassDeclSimple n) {
		ClassInfo k = (ClassInfo) this.beginScope(Symbol.symbol(n.i.toString()));
		
		// TODO Auto-generated method stub
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		
		this.endScope();

	}

	@Override
	public void visit(ClassDeclExtends n) {
		
		ClassInfo k = (ClassInfo) this.beginScope(Symbol.symbol(n.i.toString()));

		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		
		if((this.contexto.get(Symbol.symbol(n.j.toString()))) == null)
		{
			Erro.raiseError("SuperClasse " + n.j.toString() +" nao encontrada");
		}
		
		this.endScope();
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(MethodDecl n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(If n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(While n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Print n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Assign n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ArrayAssign n) {
		// TODO Auto-generated method stub

	}

	@Override
	public Symbol visit(Formal n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(And n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(LessThan n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(Minus n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(Plus n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(Times n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(ArrayLength n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(IntegerLiteral n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(This n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(IdentifierExp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(True n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(False n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(NewObject n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(NewArray n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(ArrayLookup n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(Call n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(Not n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(BooleanType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(IdentifierType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(IntegerType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(IntArrayType n) {
		// TODO Auto-generated method stub
		return null;
	}

}