package temp;

import symbol.Symbol;

public class Label {
	String s;
	private static int contador;
	
	public Label()
	{
		this("Label " + contador++);
	}
	
	public Label(String s)
	{
		this.s = s;
	}
	public Label(Symbol s)
	{
		this.s = s.toString();
	}
	
	public String toString()
	{
		return s;
	}
	
	
	
	

}
