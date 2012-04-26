package table;

import symbol.*;
import syntaxtree.*;

public class VarInfo {
	private Type type;
	private Symbol symbol;
	
	public VarInfo(Type type,Symbol symbol)
	{
		this.type = type;
		this.symbol = symbol;
	}
	
	public Type gettype()
	{
		return type;
	}
	
	public Symbol getsymbol()
	{
		return symbol;
	}
	
	public String toString()
	{
		String retorno = "";
		retorno += "Tipo " + type.toString() + " Id " + symbol.toString();
		return retorno;
	}
	
}
