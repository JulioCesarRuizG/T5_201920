package model.data_structures;

public class DuplaSC<T> implements Comparable<DuplaSC>{

	private int llave;
	private T[] valores;
	private int tam;

	public DuplaSC(int pLlave)
	{
		int tam = 3;
		T[] valores = (T[]) new Object[3];
		llave = pLlave;
	}

	public void agregarValor(int pLlave, T valor)
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
			valores[valores.length] = valor;
			tam++;
		}
	}

	public int compareTo(DuplaSC arg0) {
		if(this.llave > arg0.llave)
		{
			return 1;
		}
		else if(this.llave < arg0.llave)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}
