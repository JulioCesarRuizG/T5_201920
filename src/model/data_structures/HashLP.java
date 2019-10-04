package model.data_structures;

import java.util.Iterator;

public class HashLP<K extends Comparable<K>, V> {
	private DuplaLP[] llaves;
	private int N;
	private int nElementos;
	
	public HashLP(int capacidadInicial) {
		llaves = (DuplaLP[])new Comparable[capacidadInicial];
		N = capacidadInicial;
		nElementos=0;
	}
	
	public void put(K pKey, V pValue)
	{  boolean existe=false;
	   int posicion=0;
	while(!existe&&posicion<N){
		DuplaLP actual= llaves[posicion];
		if(actual!=null&&actual.darLlave()==pKey){
			existe=true;
			actual.cambiarValor(pValue);
		    	
		}
		posicion++;
	}
		if(existe){
			
		}else{
			nElementos++;
	if(nElementos==N){
		N=N*2;
		DuplaLP[] aux = (DuplaLP[])new Comparable[N];
		int posActual=0;
		while(aux.length<nElementos){
			aux[hash((K) llaves[posActual].darLlave())] = llaves[posActual];
		}
		llaves=aux;
		
	}
	int hashKey= hash(pKey);
	while(llaves[hashKey]!=null){
		hashKey++;
		if(hashKey==N){
			hashKey=0;
		}
		llaves[hashKey]=new DuplaLP(pKey, pValue);
	}
	
	}
	}
		
		public V get(K pKey){
		   int pos=hash(pKey);
		    DuplaLP actual = llaves[pos];
		   while(actual!=null){
			  
			   if(actual!=null&&actual.darLlave().compareTo(pKey)==0)
				   return (V) actual.darValor();
			   pos++;
			   if(pos==N)
				   pos=0;
			   actual = llaves[pos];
		   }
		   return null;
		}
		public V delete(K pKey){
			   int pos=hash(pKey);
			    DuplaLP actual = llaves[pos];
			    boolean encontrado=false;
			   while(actual!=null){
				  
				   if(actual!=null&&actual.darLlave().compareTo(pKey)==0&&!encontrado){
					   llaves[pos]=null;
					   encontrado =true;
				   }
				   if(encontrado){
					   llaves[pos]=null;
					   put((K)actual.darLlave(),(V)actual.darValor());
				   }
				   pos++;
				   if(pos==N)
					   pos=0;
				   actual = llaves[pos];
			   }
			   return null;
			}

	public V getI(K pKey){
		IteratorD iterador = Keys(); 
		while(iterador.hasNext()){
			DuplaLP actual= (DuplaLP) iterador.next();
			if(actual.darLlave().compareTo(pKey)==0)
				return (V) actual.darValor();
		}
		return null;
	}
	
	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % N;
	}

 private IteratorD Keys(){
	 IteratorD retornar= new IteradorD();
	 return IteratorD;
 }

	private class IteratorD<DuplaLP> implements Iterator{
         private int pos=nElementos;
         
		@Override
		public boolean hasNext() {
			if(pos<=0)
			return false;
			else return true;
		}

		@Override
		public DuplaLP next() {
			int volar = nElementos-pos;
			DuplaLP actual= (DuplaLP) llaves[0];
			int posAct=0;
			while(actual==null||volar!=0){
				posAct++;
				actual= (DuplaLP) llaves[posAct];
				if(actual!=null&& volar>0){
					volar--;
					actual=null;
				}
			}
			pos--;
			return actual;
		}
		
	}
}
