package syntaxtree;
import symbol.Symbol;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import table.VarInfo;
import translate.VisitorIR;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

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
  
  public VarInfo accept(ImperativeSymbolTableVisitor v)
  {
	  return new VarInfo(t,Symbol.symbol(i.toString()));
  }

  public Symbol accept(TypeCheckVisitor v) {
	// TODO Auto-generated method stub
	return v.visit(this);
  }
  
  public void accept(VisitorIR v)
  {
	  v.visit(this);
  }
  
  /*public String toString()
  {
	  return this.i.toString();
  }*/
}
