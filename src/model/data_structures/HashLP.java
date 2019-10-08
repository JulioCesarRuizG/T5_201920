package model.data_structures;

import java.util.Iterator;

public class HashLP<K extends Comparable<K>, V> {
	private K[] llaves;
	private V[] values;
	private V[][] valuesSet;
	private K[] keysSet;
	private int N;
	private int nElementosIndividuales;
	private int nElementosSet;
	private int capacidadSet;
	
	public HashLP(int capacidadInicial) {
		capacidadSet=7;
		llaves = (K[]) new Comparable[capacidadInicial];
		keysSet = (K[]) new Comparable[capacidadInicial];
		values= (V[]) new Object[capacidadInicial];
		valuesSet= (V[][]) new Object[capacidadInicial][capacidadSet];
		N = capacidadInicial;
		nElementosIndividuales=0;
		nElementosSet=0;
	}
	
	public void put(K pKey, V pValue)
	{  boolean existe=false;
	   int posicion=hash(pKey);
	   K actual= llaves[posicion];
	while(!existe&&actual!=null){
		
		if(actual.compareTo(pKey)==0){
			existe=true;
			values[posicion]= pValue;
		    	
		}
		posicion=(posicion+1)%N;
		actual=llaves[posicion];
	}
		if(existe){
			
		}else{
			nElementosIndividuales++;
	if(nElementosIndividuales==N){
		N=N*2;
		K[] auxKeys = (K[])new Comparable[N];
		V[] auxValues= (V[])new Object[N];
		int posActual=0;
		while(auxKeys.length<nElementosIndividuales){
			int hash=hash((K) llaves[posActual]);
			auxKeys[hash] = llaves[posActual];
			auxValues[hash]= values[posActual];
		}
		llaves=auxKeys;
		values=auxValues;
		
	}
	int hashKey= hash(pKey);
	while(llaves[hashKey]!=null){
		hashKey=(hashKey+1)%N;
	}
	llaves[hashKey]=pKey;
	values[hashKey]=pValue;
	
	}
	}
		public void putInSet(K pKey, V[] pValues){
			boolean existe=false;
			   int posicion=hash(pKey);
			   K actual= keysSet[posicion];
			while(!existe&&actual!=null){
				
			if(actual.compareTo(pKey)==0){
					existe=true;
					valuesSet[posicion]= pValues;
				    	
				}
				posicion=(posicion+1)%N;
				actual=llaves[posicion];
			}
			nElementosSet+=pValues.length;
			 rehash();
				if(existe){
					
				}else{
		    
			int hashKey= hash(pKey);
			while(keysSet[hashKey]!=null){
				hashKey=(hashKey+1)%N;
			}
			keysSet[hashKey]=pKey;
			valuesSet[hashKey]=pValues;
			
			}
			
		}
		public V get(K pKey){
		   int pos=hash(pKey);
		    K actual = llaves[pos];
		   while(actual!=null){
			  
			   if(actual.compareTo(pKey)==0)
				   return values[pos];
			   pos=(pos+1)%N;
			   actual = llaves[pos];
		   }
		   return null;
		}
		public V delete(K pKey){
			   int pos=hash(pKey);
			    K actual = llaves[pos];
			    boolean encontrado=false;
			   while(actual!=null){
				  
				   if(actual.compareTo(pKey)==0&&!encontrado){
					   llaves[pos]=null;
					   encontrado =true;
				   }
				   if(encontrado){
					   V valor= values[pos];
					   llaves[pos]=null;
					   put(actual,valor);
				   }
				   pos++;
				   if(pos==N)
					   pos=0;
				   actual = llaves[pos];
			   }
			   return null;
			}

	
	
	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % N;
	}

// private IteratorD<K> Keys(){
//	 IteratorD retornar= ;
//	 
//	 return retornar;
// }
 public int darNElementosIndividuales(){
	 return nElementosIndividuales;
 }
 public int darNElementosSet(){
	 return nElementosSet;
	 
 }
 public void rehash(){
		if(nElementosSet*4>=N*3){
			N=N*2;
			K[] auxKeys = (K[])new Comparable[N];
			V[][] auxValues= (V[][])new Object[N][capacidadSet];
			int posActual=0;
			while(auxKeys.length<nElementosSet){
				int hash=hash(keysSet[posActual]);
				auxKeys[hash] = keysSet[posActual];
				auxValues[hash]= valuesSet[posActual];
			}
			keysSet=auxKeys;
			valuesSet=auxValues;
			
		}
 }

	
 
	
	
	public static void main(String[] args) {
		HashLP<Integer,String> hash=new HashLP<Integer,String>(3);
		hash.put(1,  "uno");
		hash.put(2,  "uno");
		hash.put(3,  "tres");
		hash.put(4,  "dos");
		System.out.println(hash.get(1));
		System.out.println(hash.get(4));
		System.out.println(hash.get(3));
		System.out.println(hash.get(2));
		
	}
	
}
