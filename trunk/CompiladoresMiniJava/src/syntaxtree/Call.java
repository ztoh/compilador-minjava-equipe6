package syntaxtree;
import symbol.Symbol;
import translate.VisitorIR;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Call extends Exp {
  public Exp e;
  public Identifier i;
  public ExpList el;
  
  public Call(Exp ae, Identifier ai, ExpList ael) {
    e=ae; i=ai; el=ael;
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
}
