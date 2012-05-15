package typechecking;

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
import syntaxtree.While;
import table.ClassTable;

public interface TypeCheckVisitor {

	public ClassTable visit(Program n);
	public void visit( MainClass n);
	public void visit(ClassDeclSimple n);
	public void visit(ClassDeclExtends n);
	public void visit(MethodDecl n);
	public void visit(Block n);
	public void visit( If n);
	public void visit(While n);
	public void visit(Print n);
	public void visit(Assign n);
	public void visit(ArrayAssign n);
	public Symbol visit( Formal n);
	public Symbol visit( And n);
	public Symbol visit( LessThan n);
	public Symbol visit( Minus n);
	public Symbol visit( Plus n);
	public Symbol visit( Times n);
	public Symbol visit (ArrayLength n);
	public Symbol visit( IntegerLiteral n);
	public Symbol visit( This n);
	public Symbol visit(IdentifierExp n);
	public Symbol visit(True n);
	public Symbol visit(False n);
	public Symbol visit(NewObject n);
	public Symbol visit(NewArray n);
	public Symbol visit(ArrayLookup n);
	public Symbol visit(Call n);
	public Symbol visit(Not n);
	public Symbol visit( BooleanType n);
	public Symbol visit( IdentifierType n);
	public Symbol visit( IntegerType n);
	public Symbol visit( IntArrayType n);
	public Symbol visit( Identifier n);
	
	
	
	
}