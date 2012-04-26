package syntaxtree;
import symbol.Symbol;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class BooleanType extends Type {
  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public Symbol accept (ImperativeSymbolTableVisitor v){
	  return Symbol.symbol(this.toString());
  }
  
  public String toString(){
	  return "Boolean";
  }
}
