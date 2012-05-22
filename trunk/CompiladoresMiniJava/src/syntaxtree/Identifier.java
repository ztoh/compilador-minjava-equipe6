package syntaxtree;
import symbol.Symbol;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import translate.VisitorIR;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Identifier {
  public String s;

  public Identifier(String as) { 
    s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public String toString(){
    return s;
  }
  
  public Symbol accept(ImperativeSymbolTableVisitor v)
  {
	  return Symbol.symbol(s.toString());
  }

  public Symbol accept(TypeCheckVisitor v) {
	// TODO Auto-generated method stub
	  return v.visit(this);
  }
  
  public translate.Exp accept(VisitorIR v) {
		// TODO Auto-generated method stub
		return v.visit(this);
	}
  
  
}
