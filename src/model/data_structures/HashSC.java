package model.data_structures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashSC <K extends Comparable<K>, T> implements Iterator<K>{

	private DuplaSC[] llaves;
	private int N;
	private int numElem;
	private int pos = numElem;

	public HashSC(int modulo) {
		llaves = (DuplaSC[])new Comparable[modulo];
		N = modulo;
	}

	public void putInSet( K pKey, T pValue)
	{   
		int posicion = hash(pKey);
		if(llaves[posicion] != null)
		{
			llaves[posicion].agregarValor(pValue);
		}
		else 
		{
			DuplaSC i = new DuplaSC(posicion);
			llaves[posicion] = i;
			llaves[posicion].agregarValor(pValue);
		}


	}

	public Iterator<T> getSet(K pkey)
	{
		DuplaSC buscado = null;
		for(DuplaSC k : llaves)
		{
			if(k.darLlave() == pkey);
			{
				buscado = k;
			}
		}
		if(buscado == null)
		{
			return null;
		}
		else
		{
			HashMap<K, T> diccionario = null;
			for(Object k : buscado.darValores())
			{
				T valor =  (T) k;
				K llave = (K) buscado.darLlave();
				diccionario.put(llave, valor);
			}
			Iterator<T> values= diccionario.values().iterator();
			return values;
		}
		
	}

	public Iterator<T> deleteSet(K pkey)
	{
		Iterator<T> values= getSet(pkey);
		for(int i=0 ; i<llaves.length ; i++)
		{
			if(llaves[i].darLlave() == pkey)
			{
				llaves[i] = null;
			}
		}
		return values;
	}

	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % N;
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
		for(DuplaSC k : llaves)
		{
			for(Object t : k.darValores())
			{
				K llave = (K) k.darLlave();
				T value = (T) t;
				diccionario.put(value, llave);
			}


		}
		return diccionario;
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
