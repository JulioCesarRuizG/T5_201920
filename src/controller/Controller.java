package controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 * @throws FileNotFoundException 
	 */
	public Controller () throws FileNotFoundException
	{
		view = new MVCView();
		MVCModelo modelo = null;
	}
		
	public void run() throws FileNotFoundException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		int trim = 0;
		int zonaO = 0;
		int zonaD = 0;
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					System.out.println("--------- \nCrear Hash");
					modelo = new MVCModelo();
					System.out.println("HashSC y HashLP creados");		
					break;

				case 2:
					System.out.println("--------- \nBuscar viajes.");
					System.out.println("--------- \nDar trimestre:");
					trim = Integer.parseInt(lector.next());
					System.out.println("--------- \nDar zona de Origen:");
					zonaO = Integer.parseInt(lector.next());
					System.out.println("--------- \nDar zona de Destino:");
					zonaD = Integer.parseInt(lector.next());
					modelo.darHashSC().buscarTiemposDeViaje(trim, zonaO, zonaD);
										
					break;
					
				case 3: 
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
