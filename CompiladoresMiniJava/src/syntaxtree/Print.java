package syntaxtree;
import translate.TreeIRVisitor;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Print extends Statement {
  public Exp e;

  public Print(Exp ae) {
    e=ae; 
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  @Override
  public void accept(TypeCheckVisitor v) {
	v.visit(this);
	// TODO Auto-generated method stub
	
  }
  
  @Override
  public void accept(TreeIRVisitor v) {
  	v.visit(this);
  	// TODO Auto-generated method stub
  	
  }
}
