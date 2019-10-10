package model.data_structures;

import java.util.Iterator;

public class HashLP<K extends Comparable<K>, V> {
	private K[] llaves;
	private V[] values;
	private V[][] valuesSet;
	private K[] keysSet;
	private int NIndividual;
	private int NSet;
	private int nElementosIndividuales;
	private int nElementosSet;
	private int capacidadSet;

	public HashLP(int capacidadInicial) {
		capacidadSet=7;
		llaves = (K[]) new Comparable[capacidadInicial];
		keysSet = (K[]) new Comparable[capacidadInicial];
		values= (V[]) new Object[capacidadInicial];
		valuesSet= (V[][]) new Object[capacidadInicial][capacidadSet];
		NIndividual = capacidadInicial;
		NSet=capacidadInicial;
		nElementosIndividuales=0;
		nElementosSet=0;
	}

	public void put(K pKey, V pValue)
	{  boolean existe=false;
	int posicion=hashIndividual(pKey);
	K actual= llaves[posicion];
	while(!existe&&actual!=null){

		if(actual.compareTo(pKey)==0){
			existe=true;
			values[posicion]= pValue;

		}
		posicion=(posicion+1)%NIndividual;
		actual=llaves[posicion];
	}
	if(existe){

	}else{
		nElementosIndividuales++;
		if(nElementosIndividuales==NIndividual-1){

			rehashIndividual(2*NIndividual);

		}
		int hashKey= hashIndividual(pKey);
		while(llaves[hashKey]!=null){
			hashKey=(hashKey+1)%NIndividual;
		}
		llaves[hashKey]=pKey;
		values[hashKey]=pValue;

	}
	}
	public void putInSet(K pKey, V[] pValues){
		boolean existe=false;
		int posicion=hashSet(pKey);
		K actual= keysSet[posicion];
		while(!existe&&actual!=null){

			if(actual.compareTo(pKey)==0){
				existe=true;
				valuesSet[posicion]= pValues;

			}
			posicion=(posicion+1)%NSet;
			actual=llaves[posicion];
		}
		nElementosSet+=pValues.length;
		rehashSet(2*NSet);
		if(existe){

		}else{

			int hashKey= hashSet(pKey);
			while(keysSet[hashKey]!=null){
				hashKey=(hashKey+1)%NSet;
			}
			keysSet[hashKey]=pKey;
			valuesSet[hashKey]=pValues;

		}

	}
	public V get(K pKey){
		int pos=hashIndividual(pKey);
		K actual = llaves[pos];
		while(actual!=null){

			if(actual.compareTo(pKey)==0)
				return values[pos];
			pos=(pos+1)%NIndividual;
			actual = llaves[pos];
		}
		return null;
	}
	public Iterador<V> getSet(K pKey){
		int pos=hashSet(pKey);
		K actual = keysSet[pos];
		while(actual!=null){

			if(actual.compareTo(pKey)==0)
				return new Iterador<V>(valuesSet[pos]);
			pos=(pos+1)%NSet;
			actual = keysSet[pos];
		}
		return null;
	}
	
	public V delete(K pKey){
		int pos=hashIndividual(pKey);
		K actual = llaves[pos];
		boolean encontrado=false;
		V eliminado = null;
		while(actual!=null){

			if(actual.compareTo(pKey)==0&&!encontrado){
				llaves[pos]=null;
				eliminado=values[pos];
				values[pos]=null;
				encontrado =true;
			}
			if(encontrado){
				V valor= values[pos];
				llaves[pos]=null;
				put(actual,valor);
			}
			pos++;
			if(pos==NIndividual)
				pos=0;
			actual = llaves[pos];
		}
		return eliminado;
	}
	public Iterador<V> deleteSet(K pKey){
		int pos=hashSet(pKey);
		K actual = keysSet[pos];
		boolean encontrado=false;
		Iterador<V> eliminado=null;
		while(actual!=null){

			if(actual.compareTo(pKey)==0&&!encontrado){
				keysSet[pos]=null;
				eliminado=new Iterador<V>(valuesSet[pos]);
				valuesSet[pos]=null;
				encontrado =true;
			}
			if(encontrado){
				V[] valor= valuesSet[pos];
				keysSet[pos]=null;
				putInSet(actual,valor);
			}
			pos++;
			if(pos==NSet)
				pos=0;
			actual = keysSet[pos];
		}
		return eliminado;
	}



	private int hashIndividual(K key)
	{
		return (key.hashCode() & 0x7fffffff) % NIndividual;
	}
	private int hashSet(K key)
	{
		return (key.hashCode() & 0x7fffffff) % NSet;
	}


	public int darNElementosIndividuales(){
		return nElementosIndividuales;
	}
	public int darNElementosSet(){
		return nElementosSet;

	}
	public void rehashSet(int capacidad){
		if(nElementosSet*4>=NSet*3){
			HashLP<K, V> temp = new HashLP<K, V>(capacidad);
			for (int i = 0; i < nElementosSet; i++) {
				if (llaves[i] != null) {
					temp.putInSet(keysSet[i], valuesSet[i]);
				}
			}
			keysSet = temp.keysSet;
			valuesSet = temp.valuesSet;
			NSet= temp.NSet;
			nElementosSet    = temp.nElementosSet;
		}

		}
	
	private void rehashIndividual(int capacity) {
		HashLP<K, V> temp = new HashLP<K, V>(capacity);
		for (int i = 0; i < nElementosIndividuales; i++) {
			if (llaves[i] != null) {
				temp.put(llaves[i], values[i]);
			}
		}
		llaves = temp.llaves;
		values = temp.values;
		
		nElementosIndividuales    = temp.nElementosIndividuales;
	}


  public Iterador<K> Keys(){
	  int pos=0;
	  K[] auxiliar=(K[]) new Comparable[llaves.length];
	  int agregados = 0;
	  while(pos<llaves.length){
		  if(llaves[pos]!=null){
		   auxiliar[agregados]=llaves[pos];
		   agregados++;
		  }
		  pos++;
	  }
	  return new Iterador<K>(auxiliar);
  }

	public static void main(String[] args) {
		HashLP<Integer,String> hash=new HashLP<Integer,String>(3);
		hash.put(1,"uno");
		hash.put(2,"uno");
		hash.put(3,"tres");
		hash.put(4,"dos");
		System.out.println(hash.get(1));
		System.out.println(hash.get(4));
		System.out.println(hash.get(3));
		System.out.println(hash.get(2));

	}

}
