package tectijuana.votBit.hibernate.dao;

import java.util.List;

import javax.persistence.Query;

import tectijuana.votBit.hibernate.RespuestaOpciones;

public class RespuestaOpcionesDao extends DAO {
	public static List<RespuestaOpciones> obtenerRespuestaOpciones() {
		List<RespuestaOpciones> lista = null;
		Query consulta = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM RespuestaOpciones");
			lista = consulta.getResultList();
			cometerTransacciones();
		}
		catch(Exception e) {
			regresarEstado();
		}
		
		return lista;
	}
	
	public static RespuestaOpciones obtenerRespuestaOpciones(long valorId) {
		Query consulta = null;
		RespuestaOpciones dato = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM RespuestaOpciones WHERE id = :idRespuestaOpciones");
			consulta.setParameter("idRespuestaOpciones", valorId);
			dato = (RespuestaOpciones) consulta.getSingleResult();
			cometerTransacciones();
			System.out.println("Dato obtenido");
		}
		catch (Exception e) {
			regresarEstado();
		}
		return dato;
	}
	
	public static boolean guardarRespuestaOpciones(RespuestaOpciones valorDato) {
		boolean estado = true;
		
		try {
			iniciar();
			obtenerSesion().save(valorDato);
			cometerTransacciones();
			System.out.println("Dato guardado");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean borrarRespuestaOpciones(long valorId) {
		boolean estado = true;		
		try {
			iniciar();
			obtenerSesion().delete(valorId);
			cometerTransacciones();
			System.out.println("Dato borrado");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean actualizarRespuestaOpciones(RespuestaOpciones valorDato){
		boolean estado = true;
		try {
			iniciar();
			obtenerSesion().update(valorDato);
			cometerTransacciones();
			System.out.println("Dato actualizado");
		} catch (Exception e) {
			e.printStackTrace();
			estado = false;
			regresarEstado();
		}
		return estado;
	}
}
