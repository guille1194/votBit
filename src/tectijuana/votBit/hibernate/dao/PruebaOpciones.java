package tectijuana.votBit.hibernate.dao;

import java.util.List;

import org.apache.log4j.BasicConfigurator;

import tectijuana.votBit.hibernate.Opciones;
import tectijuana.votBit.hibernate.Pregunta;
import tectijuana.votBit.hibernate.util.HibernateUtil;

public class PruebaOpciones {

	public static void main(String[] args) {

		BasicConfigurator.configure();
		HibernateUtil.verificarFabricaSesion();
		PruebasOpciones();
		
	}

	public static void Desplegar(String texto)
	{
		System.out.println(texto);
	}

	public static void PruebasOpciones()
	{
		Opciones opciones = new Opciones();
		Pregunta pregunta = new Pregunta();
		pregunta.setId(2);
		opciones.setId(2);
		opciones.setIdPregunta(pregunta);
		opciones.setNumOpciones(4);
		opciones.setOpcion("1. string, 2. int, 3. float, 4. double");
		
		OpcionesDao.guardarOpciones(opciones);
		
		List<Opciones> listaOpciones = OpcionesDao.obtenerOpciones();
		if(listaOpciones == null) {
			System.out.println("No data in table");
		}
		Desplegar(listaOpciones.toString());
				
		//OpcionesDao.borrarOpciones(opciones);
		//Desplegar(OpcionesDao.obtenerOpciones(opciones.getId()).toString());
		
		//opciones.setNumOpciones(3);
		//OpcionesDao.actualizarOpciones(opciones);
	}
	
}
