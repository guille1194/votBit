package tectijuana.votBit.hibernate.dao;

import java.util.List;

import org.apache.log4j.BasicConfigurator;

import tectijuana.votBit.hibernate.Pregunta;
import tectijuana.votBit.hibernate.Respuesta;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.util.HibernateUtil;

public class PruebaRespuesta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicConfigurator.configure();
		HibernateUtil.verificarFabricaSesion();
		PruebasRespuesta();
	}

	public static void Desplegar(String texto)
	{
		System.out.println(texto);
	}
	
	public static void PruebasRespuesta()
	{
		Respuesta respuesta = new Respuesta();
		Pregunta pregunta = new Pregunta();
		Usuario usuario = new Usuario();
		pregunta.setId(1);
		usuario.setId(1);
		respuesta.setId(1);
		respuesta.setCreado("19/05/2017");
		respuesta.setIdPregunta(pregunta);
		respuesta.setIdUsuario(usuario);
		respuesta.setModificado("");
		respuesta.setRespuesta("Crear objeto");
		
		RespuestaDao.guardarRespuesta(respuesta);
		
		List<Respuesta> listaRespuesta = RespuestaDao.obtenerRespuesta();
		if(listaRespuesta == null) {
			System.out.println("No data in table");
		}
		Desplegar(listaRespuesta.toString());
		
		
		//RespuestaDao.borrarRespuesta(respuesta);
		//Desplegar(RespuestaDao.obtenerRespuesta(respuesta.getId()).toString());
		
		//respuesta.setModificado("22/05/2017");
		//RespuestaDao.actualizarRespuesta(respuesta);
	}
	
}
