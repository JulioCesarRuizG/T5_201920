package test.data_structures;


import java.io.FileNotFoundException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.HashLP;
import model.data_structures.HashSC;
import model.logic.MVCModelo;
public class TestHash<K> {
	
	private MVCModelo modelo;
	private HashSC sc;
	private HashLP lp;
	
	@Before
	private void setUp1() throws FileNotFoundException
	{
		modelo = new MVCModelo();
		sc = modelo.darHashSC();
		lp = modelo.darHashLP();
	}
	
	@Test
	public void ConsultasAleatorias()
	{
		//creación de arreglos
		
		String[] llavesNoExisten = null;
		String[] llavesExisten = null;
		Iterator<K> llaves = sc.Keys();
		Iterator<K> avance = llaves;
		int contador = 0;
		int contador2 = 0;
		boolean existe = false;
		while(llavesNoExisten.length <= 2000)
		{
			int inicio = (int) (Math.random() * (1500 - 1)) + 1;
			int destino = (int) (Math.random() * (1500 - 1)) + 1;
			int trimestre = (int) (Math.random() * (4 - 1)) + 1;
			String llave = trimestre + "-" + inicio + "-" + destino;
			while(avance.hasNext() && existe == false)
			{
				if(avance.equals(llave))
				{
					existe = true;
				}
			}
			if(existe == false)
			{
				llavesNoExisten[contador] = llave;
				contador++;
			}
			avance = llaves;
		}
		
		while(contador2 <= 8000 && llaves.hasNext())
		{
			llavesExisten[contador2] = (String) llaves.next();
			contador2++;
		}
		
		//Conteo separate chaining
		
		long inicioSC = System.currentTimeMillis();
		
		for(String k : llavesExisten)
		{
			sc.getSet(k);
		}
		
		for(String k : llavesNoExisten)
		{
			sc.getSet(k);
		}
		long endTimeSC = System.currentTimeMillis();
		long tiempoHashSC = endTimeSC - inicioSC;
		System.out.println("El tiempo tomado para Hash separate chaining fue de: " + tiempoHashSC);
		
		//Conteo linear probing
		
		long inicioLP = System.currentTimeMillis();
		
		for(String k : llavesExisten)
		{
			lp.get(k);
		}
		
		for(String k : llavesNoExisten)
		{
			lp.get(k);
		}
		long endTimeLP = System.currentTimeMillis();
		long tiempoHashLP = endTimeLP - inicioLP;
		System.out.println("El tiempo tomado para Hash lineas probing fue de: " + tiempoHashLP);
	}
	

}
