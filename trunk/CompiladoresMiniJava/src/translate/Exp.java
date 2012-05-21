package translate;
public class Exp {
	private tree.Exp e;
	public Exp(tree.Exp e)
	{
		this.e = e;
	}
	
	public tree.Exp unEx() {
		return e;
	}

}
