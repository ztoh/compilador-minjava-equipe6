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
	
	public static void adicionar(temp.TempList lista, temp.Temp var)
	{
		temp.TempList tempo = lista;
		while(tempo.tail != null)
		{
			tempo = tempo.tail;
		}
		tempo.tail = new temp.TempList(var, null);
	}
	
	public static tree.StmList converterParaStmList(List<tree.Stm> lista)
	{
		tree.StmList retorno = null;
		for (int i = lista.size()-1; i >= 0; i--) {
			retorno = new tree.StmList((tree.Stm)lista.get(i),retorno);
		}
		return retorno;
	}
	
	public static List<assem.Instr> converterInsList(assem.InstrList a)
	{
		ArrayList<assem.Instr> retorno = new ArrayList<assem.Instr>();
		assem.InstrList temp = a;
		while(temp != null)
		{
			retorno.add(temp.head);
			temp = temp.tail;
			
		}
		return retorno;
	}
	
	public static assem.InstrList converterParaInsList(List<assem.Instr> lista)
	{
		assem.InstrList retorno = null;
		for (int i = lista.size()-1; i >= 0; i--) {
			retorno = new assem.InstrList((assem.Instr)lista.get(i),retorno);
		}
		return retorno;
	}
	
	
	
}
