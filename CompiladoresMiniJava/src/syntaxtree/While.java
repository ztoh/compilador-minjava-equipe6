package syntaxtree;
import translate.TreeIRVisitor;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class While extends Statement {
  public Exp e;
  public Statement s;

  public While(Exp ae, Statement as) {
    e=ae; s=as; 
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
  public translate.Exp accept(TreeIRVisitor v) {
  	return v.visit(this);
  	// TODO Auto-generated method stub
  	
  }
}

