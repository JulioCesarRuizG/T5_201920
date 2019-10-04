package model.data_structures;

import java.util.HashMap;
import java.util.Iterator;

public class HashSC <K extends Comparable<K>, V> {
	
	private DuplaSC[] llaves;
	private int N;
	
	public HashSC(int capacidadInicial) {
		llaves = (DuplaSC[])new Comparable[capacidadInicial];
		N = capacidadInicial;
	}
	
	public void put(K pKey, V pValue)
	{   while()
		if(N-1== llaves.length)
		{
			K[] llaves2=(K[])new Comparable[N * 2];;
			int i=0;
			for(K k: llaves)
			{
				int posicion = hash(k);
			
				llaves2[posicion] = k;
				i++;
			}
		 llaves=llaves2;
		}
		N++;
		
		
	}
	
	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % N;
	}


	
	public Iterator<K> Keys() {
		Iterator<K> Keys= null;
		for(K k : llaves)
		{
			Keys.
		}
	}
}