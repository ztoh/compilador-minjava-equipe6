package symboltablevisitor;

import symbol.Symbol;
import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.Assign;
import syntaxtree.Block;
import syntaxtree.BooleanType;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.Formal;
import syntaxtree.Identifier;
import syntaxtree.IdentifierType;
import syntaxtree.If;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.Minus;
import syntaxtree.Plus;
import syntaxtree.Print;
import syntaxtree.Program;
import syntaxtree.Times;
import syntaxtree.VarDecl;
import syntaxtree.While;
import table.ClassInfo;
import table.ClassTable;
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
