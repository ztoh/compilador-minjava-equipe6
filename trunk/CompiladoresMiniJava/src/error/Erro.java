package error;

public class Erro {

	
	public static void raiseError (String x)
	{
		System.out.println(x);
	}
	
	public static void raiseError(String x,int linha, int coluna)
	{
		System.out.println("Linha " + linha + " Coluna "+ coluna);
		raiseError(x);
		
		
	}
}