package model.data_structures;

public class DuplaSC<T, K>{

	private K llave;
	private T[] valores;
	private int tam;

	public DuplaSC(K pLlave, T pValue)
	{
		llave = pLlave;
	}

	public void agregarValor(T pValue)
	{
		for(int i=0 ; i<valores.length ; i++)
		{
			if(valores[i] == null)
			{
				valores[i] = pValue;
			}
		}
	}
	
	public void cambiarValor(T pValue)
	{
		valores[0] = pValue;
		for(int i=1 ; i<valores.length-1 ; i++)
		{
			valores[i] = null;
		}
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
