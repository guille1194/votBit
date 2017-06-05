package tectijuana.votBit.hibernate.dao;

import java.util.List;

import javax.persistence.Query;

import tectijuana.votBit.hibernate.Respuesta;

public class RespuestaDao extends DAO {
	public static List<Respuesta> obtenerRespuesta() {
		List<Respuesta> lista = null;
		Query consulta = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Respuesta");
			lista = consulta.getResultList();
			cometerTransacciones();
		}
		catch(Exception e) {
			regresarEstado();
		}
		
		return lista;
	}
	
	public static Respuesta obtenerRespuesta(long valorId) {
		Query consulta = null;
		Respuesta dato = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Respuesta WHERE id = :idRespuesta");
			consulta.setParameter("idRespuesta", valorId);
			dato = (Respuesta) consulta.getSingleResult();
			cometerTransacciones();
			System.out.println("Respuesta obtenida");

		}
		catch (Exception e) {
			regresarEstado();
		}
		return dato;
	}
	
	public static boolean guardarRespuesta(Respuesta valorDato) {
		boolean estado = true;
		
		try {
			iniciar();
			obtenerSesion().save(valorDato);
			cometerTransacciones();
			System.out.println("Respuesta guardada");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean borrarRespuesta(long valorId) {
		boolean estado = true;
		Respuesta dato = null;
		
		try {
			dato = obtenerRespuesta(valorId);
			iniciar();
			obtenerSesion().delete(dato);
			cometerTransacciones();
			System.out.println("Respuesta borrada");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean actualizarRespuesta(Respuesta valorDato){
		boolean estado = true;
		try {
			iniciar();
			obtenerSesion().update(valorDato);
			cometerTransacciones();
			System.out.println("Respuesta actualizada");
		} catch (Exception e) {
			e.printStackTrace();
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
}
