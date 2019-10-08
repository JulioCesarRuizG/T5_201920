package model.data_structures;


public class HashSC <K extends Comparable<K>, T> {

	private Node<DuplaSC>[] llaves;
	private int N;
	private int M;
	private int pos = N;

	public HashSC(int modulo) {
		llaves = new Node[modulo];
		M = modulo;
	}

	public void putInSet( K pKey, T pValue)
	{   
		int posicion = hash(pKey);
		DuplaSC dupla = new DuplaSC(pKey, pValue);
		if(llaves[posicion].darItem() == null)
		{
			llaves[posicion].cambiarItem(dupla);
		}
		else
		{
			boolean encontrado = false;
			Node<DuplaSC> nodo = llaves[posicion];
			while(nodo.darSiguiente() != null && !encontrado)
			{
				DuplaSC dupla2 = nodo.darItem();
				K llave = (K) dupla2.darLlave();
				if( llave == pKey)
				{
					llaves[posicion].darItem().agregarValor(pValue);
					encontrado = true;
				}
				nodo = nodo.darSiguiente();
			}
		}
		N++;
		if(N/M >= 5)
		{
			rehash();
		}


	}

	public Iterador<T> getSet(K llave)
	{
		Node buscado = null;
		int posicion = hash(llave);
		Node buscar = llaves[posicion];
		T[] todos = null;
		int lugar = 0;
		while(buscar.darSiguiente() != null)
		{
			DuplaSC dupla = (DuplaSC) buscar.darItem();
			K key = (K) dupla.darLlave();
			if(key == llave)
			{
				T[] sacar = (T[]) dupla.darValores();
				for(T valor : sacar)
				{
					todos[lugar] = valor;
					lugar++;
				}
			}
			buscar = buscar.darSiguiente();
		}
		if(buscado == null)
		{
			return null;
		}
		else
		{
			Iterador<T> it = new Iterador<T>(todos);
			return it;
		}

	}

	public void put(K pkey, T pvalor)
	{
		int posicion = hash(pkey);
		Node nodo = llaves[posicion];
		while(nodo.darSiguiente() != null)
		{
			DuplaSC dupla = (DuplaSC) nodo.darItem();
			K llave = (K) dupla.darLlave();
			if(llave == pkey)
			{
				dupla.cambiarValor(pvalor);
			}
			nodo = nodo.darSiguiente();
		}
	}
	
	public T get(K pkey)
	{
		int posicion = hash(pkey);
		Node nodo = llaves[posicion];
		boolean encontrado = false;
		T valor = null;
		while(nodo.darSiguiente() != null && !encontrado)
		{
			DuplaSC dupla = (DuplaSC) nodo.darItem();
			K llave = (K) dupla.darLlave();
			if(llave == pkey)
			{
				valor = (T) dupla.darValores();
				encontrado = true;
			}
			nodo = nodo.darSiguiente();
		}
		return valor;
	}
	
	public T delete(K pkey)
	{
		int posicion = hash(pkey);
		Node nodo = llaves[posicion];
		Node primero = nodo;
		boolean encontrado = false;
		T valor = null;
		while(nodo.darSiguiente() != null && !encontrado)
		{
			DuplaSC dupla = (DuplaSC) nodo.darItem();
			K llave = (K) dupla.darLlave();
			if(llave == pkey)
			{
				dupla.cambiarValor(null);
				encontrado = true;
				nodo.cambiarItem(dupla);
			}
			nodo = nodo.darSiguiente();
		}
		llaves[posicion] = primero;
		return valor;
	}
	
	

	public Iterador<T> deleteSet(K pkey)
	{
		Iterador<T> values= getSet(pkey);
		int posicion = hash(pkey);
		if(getSet(pkey) != null)
		{
			boolean primerCaso = false;
			Node buscar = llaves[posicion];
			while(primerCaso == false)
			{
				DuplaSC dupla = (DuplaSC) buscar.darItem();
				K key = (K) dupla.darLlave();
				if(key == pkey)
				{
					llaves[posicion] = buscar.darSiguiente();
				}
				else
				{
					primerCaso = true;
				}
			}
			while(buscar.darSiguiente() != null)
			{
				DuplaSC dupla = (DuplaSC) buscar.darSiguiente().darItem();
				K key = (K) dupla.darLlave();
				if(key == pkey)
				{
					buscar.cambiarSiguiente(buscar.darSiguiente().darSiguiente());
				}
				buscar = buscar.darSiguiente();
			}
			DuplaSC dupla = (DuplaSC) buscar.darSiguiente().darItem();
			K key = (K) dupla.darLlave();
			if(key == pkey)
			{
				buscar.cambiarSiguiente(null);
			}
		}
		return values;
	}

	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public void buscarTiemposDeViaje(int trimestre, int zonaOrigen, int zonaDestimo)
	{
		String[] viajes = null;
		for(Node k : llaves)
		{
			Node actual = k;
			while(actual.darSiguiente() != null)
			{
				DuplaSC dupla = (DuplaSC) actual.darItem();
				String separar = (String) dupla.darLlave();
				String[] partes = separar.split("-");
				int trimestreK = Integer.parseInt(partes[0]);
				int zonaorigenK = Integer.parseInt(partes[1]);
				int zonadestinoK = Integer.parseInt(partes[2]);
				if(trimestreK == trimestre && zonadestinoK == zonaDestimo && zonaorigenK == zonaOrigen)
				{
					T[] sacar = (T[]) dupla.darValores();
					for(T valor : sacar)
					{
						double[] valores = (double[]) valor;
						double tiempoPromedio = valores[0];
						int dia = (int) valores[1];
						int lugar = dia -1;
						viajes[lugar] = viajes[lugar] + "-" + trimestreK + "," + zonaorigenK + "," + zonadestinoK + "," + dia + "," + tiempoPromedio;
					}
				}
				actual = actual.darSiguiente();
			}
		}
		for(String k : viajes)
		{
			int lugar = 1;
			String[] separados = k.split("-");
			for(int i=1 ; i<separados.length ; i++)
			{
				String[] datos = separados[i].split(",");
				System.out.println("Viaje " + lugar + ": trimestre = " + datos[0] + " zona de origen = " + datos[1] + " zona de destino = " + datos[2] + " tiempo promedio " + datos[3]);
				lugar ++;
			}
		}
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
				Node[] llaves2 = new Node[M];
				for(int k=0 ; k<llaves.length ; k++)
				{
					if(llaves[i] != null)
					{
						Node actual = llaves[i];
						while(actual.darSiguiente() != null)
						{
							DuplaSC dupla = (DuplaSC) actual.darItem();
							K key = (K) dupla.darLlave();

							int posicion = hash(key);
							Node nodo = new Node(dupla, null);
							llaves2[posicion].agregarAlFinal(nodo);
							actual = actual.darSiguiente();
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
	
	public Iterador<K> Keys()
	{
		K[] todas = null;
		int lugar = 0;
		for(Node n: llaves)
		{
			Node actual = n;
			while(actual.darSiguiente() != null)
			{
				todas[lugar] = (K) n.darItem();
				actual = actual.darSiguiente();
			}
		}
		Iterador it = new Iterador(todas);
		return it;
	}


}
