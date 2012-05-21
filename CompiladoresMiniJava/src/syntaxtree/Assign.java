package syntaxtree;
import translate.TreeIRVisitor;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Assign extends Statement {
  public Identifier i;
  public Exp e;

  public Assign(Identifier ai, Exp ae) {
    i=ai; e=ae; 
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

