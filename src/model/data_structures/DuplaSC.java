package model.data_structures;

public class DuplaSC<T, K>{

	private K llave;
	private T[] valores;
	private int tam;

	public DuplaSC(K pLlave)
	{
		T[] valores = (T[]) new Object[10];
		llave = pLlave;
	}

	public void agregarValor(T pValue)
	{
		if(tam == valores.length)
		{
			int pos = 0;
			T[] valores2 = (T[]) new Object[tam*2];
			for(T t : valores)
			{
				valores2[pos] = t;
				pos++;
			}
			valores = valores2;
		}
		else
		{
			valores[valores.length] = pValue;
			tam++;
		}
		tam++;
	}

	public T[] darValores()
	{
		return valores;
	}

	public int darTamanio()
	{
		return tam;
	}
	
	public K darLlave()
	{
		return llave;
	}
}
