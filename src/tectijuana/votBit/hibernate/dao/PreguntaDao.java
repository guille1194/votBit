package tectijuana.votBit.hibernate.dao;

import java.util.List;

import javax.persistence.Query;

import tectijuana.votBit.hibernate.Pregunta;

public class PreguntaDao extends DAO {
	public static List<Pregunta> obtenerPregunta() {
		List<Pregunta> lista = null;
		Query consulta = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Pregunta");
			lista = consulta.getResultList();
			cometerTransacciones();
		}
		catch(Exception e) {
			regresarEstado();
		}
		
		return lista;
	}
	
	public static Pregunta obtenerPregunta(long valorId) {
		Query consulta = null;
		Pregunta dato = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Pregunta WHERE id = :idPregunta");
			consulta.setParameter("idPregunta", valorId);
			dato = (Pregunta) consulta.getSingleResult();
			cometerTransacciones();
			System.out.println("Pregunta obtenida");
		}
		catch (Exception e) {
			regresarEstado();
		}
		return dato;
	}
	
	public static boolean guardarPregunta(Pregunta valorDato) {
		boolean estado = true;
		
		try {
			iniciar();
			obtenerSesion().save(valorDato);
			cometerTransacciones();
			System.out.println("Pregunta guardada");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean borrarPregunta(long valorId) {
		boolean estado = true;
		
		try {
			iniciar();
			obtenerSesion().delete(valorId);
			cometerTransacciones();
			System.out.println("Pregunta borrada");

		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean actualizarPregunta(Pregunta valorDato){
		boolean estado = true;
		try {
			iniciar();
			obtenerSesion().update(valorDato);
			cometerTransacciones();
			System.out.println("Pregunta actualizada");
		} catch (Exception e) {
			e.printStackTrace();
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
}
