package tree;

//import java.util.List;



public class ExpList{
  public Exp head;
  public ExpList tail;
 /* public ExpList(Exp h,List<Exp> t) { 
	  head=h; 
	  if(!t.isEmpty())
	  {
		  tail= new ExpList(t.get(0),(List<Exp>) t.subList(1, t.size()-1));
	  }
  }*/
  public ExpList(Exp h, ExpList t)
  {
	  head = h;
	  tail = t;
  }
}