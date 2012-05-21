package syntaxtree;
import translate.TreeIRVisitor;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public abstract class Statement {
  public abstract void accept(Visitor v);
  public abstract Type accept(TypeVisitor v);
  public abstract void accept(TypeCheckVisitor concreteTypeCheckVisitor) ;
  public abstract translate.Exp accept(TreeIRVisitor v);
  }
