package symboltablevisitor;

import symbol.Symbol;
import syntaxtree.BooleanType;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.Formal;
import syntaxtree.Identifier;
import syntaxtree.IdentifierType;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerType;
import syntaxtree.MethodDecl;
import syntaxtree.Program;
import syntaxtree.VarDecl;
import table.ClassInfo;
import table.ClassTable;
import table.MethodInfo;
import table.MethodTable;
import table.Table;
import table.VarInfo;
import table.VarTable;

public class ImperativeSymbolTableVisitor implements SymbolTableVisitor {

	@Override
	public Table visit(Program n) {
		ClassTable k = new ClassTable();
		ClassInfo s = n.m.accept(this,Symbol.symbol(n.m.i1.toString()));
		k.put(Symbol.symbol(n.m.i1.toString()), s);
		for (int i = 0; i < n.cl.size(); i++) {
			s = n.cl.elementAt(i).accept(this);
			if(n.cl.elementAt(i) instanceof ClassDeclExtends)
			{
				k.put(Symbol.symbol(((ClassDeclExtends) n.cl.elementAt(i)).toString()), s);
			}
			else
			{
				k.put(Symbol.symbol(((ClassDeclSimple) n.cl.elementAt(i)).toString()), s);
			}
		}
		// TODO Auto-generated method stub
		return k;
	}

	@Override
	public Table visit(ClassDeclSimple n) {
		VarInfo s;
		VarTable k = new VarTable();
		MethodInfo s1;
		MethodTable k1 = new MethodTable();
		for (int i = 0; i < n.vl.size(); i++) {
			 s = n.vl.elementAt(i).accept(this);
			 k.put(Symbol.symbol(s.toString()), s);
		}
		
		for (int i = 0; i < n.ml.size() ; i++) {
			s1 = (MethodInfo)n.ml.elementAt(i).accept(this);
			k1.put(Symbol.symbol(s1.toString()), s1);
		}
		
		// TODO Auto-generated method stub
		return new ClassInfo(Symbol.symbol(n.i.toString()));
	}

	@Override
	public Table visit(ClassDeclExtends n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table visit(MethodDecl n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VarInfo visit(VarDecl n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VarInfo visit(Formal n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(Identifier n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(IntegerType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(BooleanType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(IntArrayType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol visit(IdentifierType n) {
		// TODO Auto-generated method stub
		return null;
	}

}
