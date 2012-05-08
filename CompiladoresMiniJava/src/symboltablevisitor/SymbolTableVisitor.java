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
import table.Table;
import table.VarInfo;

public  interface SymbolTableVisitor {
	
		public Table visit( Program n);
		public Table visit(ClassDeclSimple n);
		public Table visit(ClassDeclExtends n);
		public Table visit(MethodDecl n);
		public VarInfo visit(VarDecl n);
		public VarInfo visit(Formal n);
		public Symbol visit (Identifier n);
		public Symbol visit( IntegerType n);
		public Symbol visit(BooleanType n);
		public Symbol visit(IntArrayType n);
		public Symbol visit(IdentifierType n);		
		
		

}
