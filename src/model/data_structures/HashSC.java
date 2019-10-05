package model.data_structures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashSC <K extends Comparable<K>, T> implements Iterator<K>{

	private HashMap[] llaves;
	private int N;
	private int M;
	private int pos = N;

	public HashSC(int modulo) {
		llaves = new HashMap[modulo];
		M = modulo;
	}

	public void putInSet( K pKey, T pValue)
	{   
		int posicion = hash(pKey);
		if(llaves[posicion] != null)
		{
			HashMap<K, T> valor= new HashMap<K, T>(); 
			llaves[posicion] = valor;
			llaves[posicion].put(pKey, pValue);
		}
		else 
		{
			llaves[posicion].put(pKey, pValue);
		}
		N++;
		if(N/M >= 5)
		{
			rehash();
		}


	}

	public Iterator<T> getSet(K pkey)
	{
		HashMap<K, T> buscado = null;
		int posicion = hash(pkey);
		HashMap<K, T> buscar = llaves[posicion];
		if(buscar.containsKey(pkey));
		{
			for(Map.Entry<K, T>entry:buscar.entrySet())
			{
				if(entry.getKey() == pkey)
				{
					T valor = entry.getValue();
					buscado.put(pkey, valor);
				}
			}
		}
		if(buscado == null)
		{
			return null;
		}
		else
		{
			Iterator<T> values= buscado.values().iterator();
			return values;
		}

	}

	public Iterator<T> deleteSet(K pkey)
	{
		Iterator<T> values= getSet(pkey);
		int posicion = hash(pkey);
		if(getSet(pkey) != null)
		{
			HashMap<K, T> buscar = llaves[posicion];
			for(Map.Entry<K, T>entry:buscar.entrySet())
			{
				llaves[posicion].remove(entry.getKey());
			}
		}
		return values;
	}

	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}

	private HashMap<T, K> totales()
	{
		//		for(DuplaSC k : llaves)
		//			{
		//				numElem = numElem + k.darTamanio();
		//			}
		//		T[] todos = (T[]) new Object[numElem];
		//		for(DuplaSC k : llaves)
		//		{
		//			for(Object t : k.darValores())
		//			{
		//				todos[todos.length] = (T) t;
		//			}
		//			
		//		}
		//		valores = todos;
		HashMap<T, K> diccionario = new HashMap<T, K>();
		for(HashMap<K, T> k : llaves)
		{
			for(Map.Entry<K, T>entry:k.entrySet())
			{
				diccionario.put(entry.getValue(), entry.getKey());
			}
		}
		return diccionario;
	}

	public void rehash()
	{
		boolean encontrado = false;
		int[] primos = {7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
		for(int i:primos)
		{
			if(encontrado == true)
			{
				M = i;
				HashMap[] llaves2 = new HashMap[M];
				for(int k=0 ; k<llaves.length ; k++)
				{
					if(llaves[i] != null)
					{
						HashMap<K, T> actual = llaves[i];
						for(Map.Entry<K, T>entry:actual.entrySet())
						{
							int posicion = hash(entry.getKey());
							llaves2[posicion].put(entry.getKey(), entry.getValue());
						}
						
					}
					
				}
				llaves = llaves2;
				return;
			}
			if(M == i) {
				encontrado = true;
			}
		}
	}
	public Iterator<K> Keys()
	{
		HashMap<T, K> diccionario = totales();
		Iterator<K> values = (Iterator<K>) diccionario.values();
		return values;
	}


	@Override
	public boolean hasNext() {
		if(pos==0)
			return false;
		else return true;
	}

	@Override
	public K next() {
		int actual = pos;
		pos--;
		return (K) llaves[actual].darLlave();
	}

}
