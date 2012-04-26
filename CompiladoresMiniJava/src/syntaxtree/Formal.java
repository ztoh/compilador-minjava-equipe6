package syntaxtree;
import symbol.Symbol;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;
import table.VarInfo;

public class Formal {
  public Type t;
  public Identifier i;
 
  public Formal(Type at, Identifier ai) {
    t=at; i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public VarInfo accept(ImperativeSymbolTableVisitor v){
	  return new VarInfo(t, Symbol.symbol(i.toString()));
  }
}
