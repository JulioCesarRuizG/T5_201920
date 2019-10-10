package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.opencsv.CSVReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.HashLP;
import model.data_structures.HashSC;
import model.data_structures.IArregloDinamico;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private  HashSC<String,Double[]> sc;
	private HashLP<String,Double> lp;


	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 * @throws FileNotFoundException 
	 */
	public MVCModelo() throws FileNotFoundException
	{
		CSVReader reader1 = null;
		CSVReader reader2 = null;
		CSVReader reader3 = null;
		CSVReader reader4 = null;
		String carga1 = "";
		String carga2 = "";
		String carga3 = "";
		String carga4 = "";
		
			carga1 = "./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv";
			carga2 = "./data/bogota-cadastral-2018-2-All-HourlyAggregate.csv";
			carga3 = "./data/bogota-cadastral-2018-3-All-HourlyAggregate.csv";
			carga4 = "./data/bogota-cadastral-2018-4-All-HourlyAggregate.csv";
			
		boolean primero = false;
		Double[] datosPrimero = new Double[4];
		Double[] datosUltimo = new Double[4];
		int trimestre = 1;
		int total = 0;
		reader1= new CSVReader(new FileReader(carga1));
		for(String[] nextLine : reader1) {
			if(nextLine.toString().contains("sourceid,dstid,hod,mean_travel_time,standard_deviation_travel_time,geometric_mean_travel_time,geometric_standard_deviation_travel_time"))
			{

			}
			else
			{
				int  inicioID=Integer.parseInt(nextLine[0]);
				int destinoID=Integer.parseInt(nextLine[1]);
				int dia=Integer.parseInt(nextLine[2]);
				double diaValue= dia;
				double tiempoPromedio=Double.parseDouble(nextLine[3]);
				double desviacionEstandar=Double.parseDouble(nextLine[4]);

				String llave = trimestre + "-" + inicioID + "-" + destinoID;
				
				Double[] valor = new Double[2];
				valor[0] = tiempoPromedio;
				valor[1] = diaValue;

				if(primero == false)
				{
					datosPrimero[0] = inicioID+0.1-0.1;
					datosPrimero[1] = destinoID+0.1-0.1;
					datosPrimero[2] = dia+0.1-0.1;
					datosPrimero[3]	= tiempoPromedio;
				}

				primero = true;				
				
				sc.putInSet(llave, valor);
				lp.putInSet(llave, valor);
				total++;
			}
		}
		trimestre = 2;
		
		reader2= new CSVReader(new FileReader(carga2));
		for(String[] nextLine : reader2) {
			if(nextLine.toString().contains("sourceid,dstid,hod,mean_travel_time,standard_deviation_travel_time,geometric_mean_travel_time,geometric_standard_deviation_travel_time"))
			{

			}
			else
			{
				int  inicioID=Integer.parseInt(nextLine[0]);
				int destinoID=Integer.parseInt(nextLine[1]);
				int dia=Integer.parseInt(nextLine[2]);
				double diaValue = dia;
				double tiempoPromedio=Double.parseDouble(nextLine[3]);
				double desviacionEstandar=Double.parseDouble(nextLine[4]);

				String llave = trimestre + "-" + inicioID + "-" + destinoID;
				Double[] valor = new Double[2];
				valor[0] = tiempoPromedio;
				valor[1] = diaValue;
				
				
				sc.putInSet(llave, valor);
				lp.putInSet(llave, valor);
				total++;
			}
		}
		
		trimestre = 3;
		
		reader3= new CSVReader(new FileReader(carga3));
		for(String[] nextLine : reader3) {
			if(nextLine.toString().contains("sourceid,dstid,hod,mean_travel_time,standard_deviation_travel_time,geometric_mean_travel_time,geometric_standard_deviation_travel_time"))
			{

			}
			else
			{
				int  inicioID=Integer.parseInt(nextLine[0]);
				int destinoID=Integer.parseInt(nextLine[1]);
				int dia=Integer.parseInt(nextLine[2]);
				double diaValue= dia;
				double tiempoPromedio=Double.parseDouble(nextLine[3]);	
				double desviacionEstandar=Double.parseDouble(nextLine[4]);

				String llave = trimestre + "-" + inicioID + "-" + destinoID;
				Double[] valor = new Double[2];
				valor[0] = tiempoPromedio;
				valor[1] = diaValue;
				
				sc.putInSet(llave, valor);
				lp.putInSet(llave, valor);
				total++;
			}
		}
		
		trimestre = 4;
		
		reader4= new CSVReader(new FileReader(carga4));
		for(String[] nextLine : reader4) {
			if(nextLine.toString().contains("sourceid,dstid,hod,mean_travel_time,standard_deviation_travel_time,geometric_mean_travel_time,geometric_standard_deviation_travel_time"))
			{

			}
			else
			{
				int  inicioID=Integer.parseInt(nextLine[0]);
				double inicioIDValue=inicioID;
				int destinoID=Integer.parseInt(nextLine[1]);
				double destinoIDValue=destinoID;
				int dia=Integer.parseInt(nextLine[2]);
				double diaValue=dia;
				double tiempoPromedio=Double.parseDouble(nextLine[3]);
				double desviacionEstandar=Double.parseDouble(nextLine[4]);

				String llave = trimestre + "-" + inicioID + "-" + destinoID;		
				Double[] valor = new Double[2];
				valor[0] = tiempoPromedio;
				valor[1] = diaValue;
				
				datosUltimo[0] = inicioIDValue;
				datosUltimo[1] = destinoIDValue;
				datosUltimo[2] = diaValue;
				datosUltimo[3] = tiempoPromedio;
				
				sc.putInSet(llave, valor);
				lp.putInSet(llave, valor);
				total++;
			}
		}
		
		System.out.println("Los cantidad de viajes cargados fue: " + total);
		System.out.println("Los datos del primer viaje fueron:\n" + "Zona de origen: " + datosPrimero[0] + "\n" + "Zona de destino: " + datosPrimero[1] + "\n" + "Día: " + datosPrimero[2] + "\n" + "Tiempo promedio: " + datosPrimero[3]);
		System.out.println("Los datos del último viaje fueron:\n" + "Zona de origen: " + datosUltimo[0] + "\n" + "Zona de destino: " + datosUltimo[1] + "\n" + "Día: " + datosUltimo[2] + "\n" + "Tiempo promedio: " + datosUltimo[3]);



	}
	
	public HashSC darHashSC()
	{
		return sc;
	}
	
	public HashLP darHashLP()
	{
		return lp;
	}
}
