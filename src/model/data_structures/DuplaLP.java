package model.data_structures;

public class DuplaLP<T,K extends Comparable<K>> implements Comparable<DuplaLP> {

	private K llave;
	private T valor;


	public DuplaLP(K pLlave, T pValor)
	{
	    valor = pValor;
		llave = pLlave;
	}

	public void cambiarValor( T pValor)
	{
	   valor = pValor;
	}
    public K darLlave(){
    	return llave;
    }
    public T darValor(){
    	return valor;
    }
	@Override
	public int compareTo(DuplaLP arg0) {
		
	if(llave.compareTo((K)arg0.llave)==0){
		return 0;
	}else if(llave.compareTo((K)arg0.llave)>0)
		return 1;
	else
		return -1;
}
}