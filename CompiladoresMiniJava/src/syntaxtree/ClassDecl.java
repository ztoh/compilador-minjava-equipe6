package syntaxtree;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import table.ClassInfo;
import table.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public abstract class ClassDecl {
  public abstract void accept(Visitor v);
  public abstract Type accept(TypeVisitor v);
  public abstract ClassInfo accept(ImperativeSymbolTableVisitor v);
}
