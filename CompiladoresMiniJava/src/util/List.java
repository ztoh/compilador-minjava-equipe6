package util;

public class List<E> {
	public E cabeca;
	public List<E> cauda;
	
	public List()
	{
		
	}
	
	public List(E cabeca)
	{
		this.cabeca = cabeca;
	}
	
	public List(E cabeca, List<E> cauda)
	{
		this.cabeca = cabeca;
		this.cauda = cauda;
	}
	
	public int size() 
	{
		if(cauda != null)
			return 1 + cauda.size();
		if(cabeca != null)
			return 1;
		return 0;
	}
	
	public E get(int n) throws IndexOutOfBoundsException
	{
		if(n == 0)
		{
			return cabeca;
		}
		if(cauda == null)
			throw new IndexOutOfBoundsException();
		return cauda.get(n-1);
	}
	
	public void set(E elemento )
	{
		if(cauda == null)
		{
			cauda = new List<E>(elemento);
		}
		else
		{
			cauda.set(elemento);
		}
			
		
	}
	
	public void set(E elemento, int n)
	{
		if( n == 0)
		{
			E temp = this.get(0);
			this.cabeca = elemento;
			this.reordenar(temp);
		}
		
		else
		{
			if(cauda == null)
			{
				cauda = new List<E>(elemento);
			}
			
			else
			{
				cauda.set(elemento, n-1);
			}
			
		}
	}

	private void reordenar(E elemento) {
		if(cauda != null)
		{
			E temp = this.get(0);
			this.cabeca = elemento;
			this.reordenar(temp);
		}
		else
		{
			cauda = new List<E>(elemento);
		}
		// TODO Auto-generated method stub
		
	}
}
