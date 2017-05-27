package tectijuana.votBit.hibernate.dao;

import java.util.List;

import org.apache.log4j.BasicConfigurator;

import tectijuana.votBit.hibernate.Pregunta;
import tectijuana.votBit.hibernate.RespuestaOpciones;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.util.HibernateUtil;

public class PruebaRespuestaOpciones {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		HibernateUtil.verificarFabricaSesion();
		PruebasRespuestaOpciones();
	}

	public static void Desplegar(String texto)
	{
		System.out.println(texto);
	}
	
	public static void PruebasRespuestaOpciones()
	{
		RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
		Pregunta pregunta = new Pregunta();
		Usuario usuario = new Usuario();
		usuario.setId(1);
		pregunta.setId(1);
		respuestaOpciones.setId(1);
		respuestaOpciones.setIdPregunta(pregunta);
		respuestaOpciones.setIdUsuario(usuario);
		respuestaOpciones.setCreado("19/05/2017");
		respuestaOpciones.setModificado("");
		respuestaOpciones.setRespuesta("opcion b");
		
		RespuestaOpcionesDao.guardarRespuestaOpciones(respuestaOpciones);
		
		List<RespuestaOpciones> listaRespuestaOpciones = RespuestaOpcionesDao.obtenerRespuestaOpciones();
		if(listaRespuestaOpciones == null) {
			System.out.println("No data in table");
		}
		Desplegar(listaRespuestaOpciones.toString());
				
		//RespuestaOpcionesDao.borrarRespuestaOpciones(respuestaOpciones);
		//Desplegar(RespuestaOpcionesDao.obtenerRespuestaOpciones(respuestaOpciones.getId()).toString());
	
		//respuestaOpciones.setModificado("22/05/2017");
		//RespuestaOpcionesDao.actualizarRespuestaOpciones(respuestaOpciones);
	}
	
}
