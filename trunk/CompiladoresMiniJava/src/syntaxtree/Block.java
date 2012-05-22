package syntaxtree;
import translate.TreeIRVisitor;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Block extends Statement {
  public StatementList sl;

  public Block(StatementList asl) {
    sl=asl;
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

