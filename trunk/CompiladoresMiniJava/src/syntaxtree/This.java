package syntaxtree;
import symbol.Symbol;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class This extends Exp {
	
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
}
