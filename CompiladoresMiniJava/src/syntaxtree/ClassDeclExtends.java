package syntaxtree;
//import symbol.Symbol;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import table.ClassInfo;
import translate.TreeIRVisitor;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ClassDeclExtends extends ClassDecl {
  public Identifier i;
  public Identifier j;
  public VarDeclList vl;  
  public MethodDeclList ml;
 
  public ClassDeclExtends(Identifier ai, Identifier aj, 
                  VarDeclList avl, MethodDeclList aml) {
    i=ai; j=aj; vl=avl; ml=aml;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public ClassInfo accept(ImperativeSymbolTableVisitor v)
  {
	  return (ClassInfo) v.visit(this);
	  //return new ClassInfo(Symbol.symbol(i.toString()),Symbol.symbol(j.toString()));
  }
  
  public String toString()
  {
	  return this.i.toString();
  }

  @Override
  public void accept(TypeCheckVisitor v) {
	  v.visit(this);
	// TODO Auto-generated method stub
	
}

  @Override
  public void accept(TreeIRVisitor v) {
	// TODO Auto-generated method stub
	v.visit(this);
  }
}
