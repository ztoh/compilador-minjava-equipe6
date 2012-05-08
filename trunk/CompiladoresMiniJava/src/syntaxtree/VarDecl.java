package syntaxtree;
import symbol.Symbol;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import table.VarInfo;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class VarDecl {
  public Type t;
  public Identifier i;
  
  public VarDecl(Type at, Identifier ai) {
    t=at; i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public VarInfo accept(ImperativeSymbolTableVisitor n)
  {
	  return new VarInfo(t, Symbol.symbol(i.toString()));
  }
  
  public String toString()
  {
	  return this.i.toString();
  }
}
