package model.data_structures;

public class DuplaSC<T, K>{

	private K llave;
	private T valor;
	private int tam;

	public DuplaSC(K pLlave, T pValue)
	{
		llave = pLlave;
		valor = pValue;
	}

	public void agregarValor(T pValue)
	{
		valor = pValue;
	}

	public T darValor()
	{
		return valor;
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
