package tectijuana.votBit.hibernate.dao;

import java.util.List;

import org.apache.log4j.BasicConfigurator;

import tectijuana.votBit.hibernate.Categoria;
import tectijuana.votBit.hibernate.Pregunta;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.util.HibernateUtil;

public class PruebaPregunta {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		HibernateUtil.verificarFabricaSesion();
		PruebasPregunta();
	}

	public static void Desplegar(String texto)
	{
		System.out.println(texto);
	}
	
	public static void PruebasPregunta()
	{
		Pregunta pregunta = new Pregunta();
		Categoria categoria = new Categoria();
		Usuario usuario = new Usuario();
		categoria.setId(1);
		usuario.setId(1);
		pregunta.setId(1);
		pregunta.setIdCategoria(categoria);
		pregunta.setCreado("19/05/2017");
		pregunta.setIdUsuario(usuario);
		pregunta.setModificado("");
		pregunta.setTitulo("Hola");
		
		PreguntaDao.guardarPregunta(pregunta);
		
		List<Pregunta> listaPregunta = PreguntaDao.obtenerPregunta();
		if(listaPregunta == null) {
			System.out.println("No data in table");
		}
		Desplegar(listaPregunta.toString());
				
		//PreguntaDao.borrarPregunta(pregunta);
		//Desplegar(PreguntaDao.obtenerPregunta(pregunta.getId()).toString());
		
		//pregunta.setModificado("22/05/2017");
		//PreguntaDao.actualizarPregunta(pregunta);
	}
	
}
