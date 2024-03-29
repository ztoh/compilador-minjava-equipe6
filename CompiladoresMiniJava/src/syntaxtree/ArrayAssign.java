package syntaxtree;
import translate.TreeIRVisitor;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ArrayAssign extends Statement {
  public Identifier i;
  public Exp e1,e2;

  public ArrayAssign(Identifier ai, Exp ae1, Exp ae2) {
    i=ai; e1=ae1; e2=ae2;
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

