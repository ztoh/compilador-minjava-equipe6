package syntaxtree;
import symbol.Symbol;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Not extends Exp {
  public Exp e;
  
  public Not(Exp ae) {
    e=ae; 
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
}
