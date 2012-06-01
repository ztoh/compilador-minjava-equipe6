package syntaxtree;
import symbol.Symbol;
import translate.VisitorIR;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class NewObject extends Exp {
  public Identifier i;
  
  public NewObject(Identifier ai) {
    i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public Symbol accept(TypeCheckVisitor v) {
		return v.visit(this);
		// TODO Auto-generated method stub
		
  }
  
  public translate.Exp accept(VisitorIR v) {
		// TODO Auto-generated method stub
		return v.visit(this);
	}
  
  public String toString()
  {
	  return i.toString();
  }
}
