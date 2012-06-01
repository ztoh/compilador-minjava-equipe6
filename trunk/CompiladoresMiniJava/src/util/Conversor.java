package util;
import java.util.ArrayList;
import java.util.List;

import temp.TempList;
import tree.ExpList;

public class Conversor {

	public static tree.ExpList converterList(List<tree.Exp> lista)
	{
		tree.ExpList retorno = null;
		for (int i = lista.size()-1; i >= 0; i--) {
			retorno = new tree.ExpList((tree.Exp)lista.get(i),retorno);
		}
		return retorno;
	}
	
	public static List<tree.Exp> converterExpList(ExpList a)
	{
		ArrayList<tree.Exp> retorno = new ArrayList<tree.Exp>();
		ExpList temp = a;
		while(temp != null)
		{
			retorno.add(temp.head);
			temp = temp.tail;
			
		}
		return retorno;
	}
	
	
	public static temp.TempList converterListaTemp(List<temp.Temp> lista)
	{
		temp.TempList retorno = null;
		for (int i = lista.size()-1; i >= 0; i--) {
			retorno = new temp.TempList((temp.Temp)lista.get(i),retorno);
		}
		return retorno;
	}
	
	public static List<temp.Temp> converterTempList(TempList a)
	{
		ArrayList<temp.Temp> retorno = new ArrayList<temp.Temp>();
		TempList temp = a;
		while(temp != null)
		{
			retorno.add(temp.head);
			temp = temp.tail;
			
		}
		return retorno;
	}
	
	public static temp.Temp[] converterTempListParaVetor(TempList a)
	{
		temp.Temp retorno[] = new temp.Temp[Conversor.converterTempList(a).size()];
		TempList temp = a;
		for (int i = 0; i < retorno.length; i++) {
			retorno[i] = temp.head;
			temp = temp.tail;
		}
			
		return retorno;
	}
	
	public static temp.TempList converterVetorTemp(temp.Temp lista[])
	{
		temp.TempList retorno = null;
		for (int i = lista.length-1; i >= 0; i--) {
			retorno = new temp.TempList((temp.Temp)lista[i],retorno);
		}
		return retorno;
	}
	
	
	
}
