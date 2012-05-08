package syntaxtree;
import symboltablevisitor.ImperativeSymbolTableVisitor;
import table.Table;
import typechecking.TypeCheckVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class MethodDecl {
  public Type t;
  public Identifier id;
  public FormalList fl;
  public VarDeclList vl;
  public StatementList sl;
  public Exp e;

  public MethodDecl(Type at, Identifier ai, FormalList afl, VarDeclList avl, 
                    StatementList asl, Exp ae) {
    t=at; id=ai; fl=afl; vl=avl; sl=asl; e=ae;
  }
 
  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public Table accept(ImperativeSymbolTableVisitor n)
  {
	  return n.visit(this);
  }
  
  public String toString()
  {
	  return this.id.toString();
  }

  public void accept(TypeCheckVisitor v) {
	  // TODO Auto-generated method stub
	
  }
}
