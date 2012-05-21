package syntaxtree;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import table.ClassInfo;
import translate.TreeIRVisitor;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public abstract class ClassDecl {
  public abstract void accept(Visitor v);
  public abstract Type accept(TypeVisitor v);
  public abstract ClassInfo accept(ImperativeSymbolTableVisitor v);
  public abstract void accept(TypeCheckVisitor v);
  public abstract void accept(TreeIRVisitor v);
  }
