package syntaxtree;
//import symbol.Symbol;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import table.ClassInfo;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ClassDeclSimple extends ClassDecl {
  public Identifier i;
  public VarDeclList vl;  
  public MethodDeclList ml;
 
  public ClassDeclSimple(Identifier ai, VarDeclList avl, MethodDeclList aml) {
    i=ai; vl=avl; ml=aml;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public ClassInfo accept(ImperativeSymbolTableVisitor v)
  {
	  //ClassInfo k = new ClassInfo(Symbol.symbol(i.toString()));
	  return (ClassInfo) v.visit(this);
	  
	  //return k;
	  //return new ClassInfo(Symbol.symbol(i.toString()));
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
}
