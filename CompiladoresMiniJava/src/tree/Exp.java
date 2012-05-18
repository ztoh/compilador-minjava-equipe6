package tree;

public abstract class Exp {
	abstract public ExpList kids();
	abstract public Exp build(ExpList kids);
}
