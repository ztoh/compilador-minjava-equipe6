package util;
import java.util.ArrayList;
import java.util.List;

import tree.ExpList;

public class Conversor {

	public static tree.ExpList converterList(List<tree.Exp> lista)
	{
		tree.ExpList retorno = null;
		for (int i = lista.size()-1; i >= 0; i++) {
			retorno = new tree.ExpList((tree.Exp)lista.get(i),retorno);
		}
		return retorno;
	}
	
	public static List<tree.Exp> converterExpList(ExpList a)
	{
		ArrayList<tree.Exp> retorno = new ArrayList<tree.Exp>();
		ExpList temp = a;
		while(temp.head != null)
		{
			retorno.add(temp.head);
			temp = temp.tail;
			
		}
		return retorno;
	}
	
}
