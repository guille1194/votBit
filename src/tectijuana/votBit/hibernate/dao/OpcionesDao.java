package tectijuana.votBit.hibernate.dao;

import java.util.List;

import javax.persistence.Query;

import tectijuana.votBit.hibernate.Opciones;

public class OpcionesDao extends DAO {
	public static List<Opciones> obtenerOpciones() {
		List<Opciones> lista = null;
		Query consulta = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Opciones");
			lista = consulta.getResultList();
			cometerTransacciones();
		}
		catch(Exception e) {
			regresarEstado();
		}
		
		return lista;
	}
	
	public static Opciones obtenerOpciones(long valorId) {
		Query consulta = null;
		Opciones dato = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Opciones WHERE id = :idOpciones");
			consulta.setParameter("idOpciones", valorId);
			dato = (Opciones) consulta.getSingleResult();
			cometerTransacciones();
			System.out.println("Opciones obtenidas");
		}
		catch (Exception e) {
			regresarEstado();
		}
		return dato;
	}
	
	public static boolean guardarOpciones(Opciones valorDato) {
		boolean estado = true;
		
		try {
			iniciar();
			obtenerSesion().save(valorDato);
			cometerTransacciones();
			System.out.println("Opciones guardadas");

		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean borrarOpciones(Opciones valorId) {
		boolean estado = true;
		
		try {
			iniciar();
			obtenerSesion().delete(valorId);
			cometerTransacciones();
			System.out.println("Opciones borrado");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean actualizarOpciones(Opciones valorDato){
		boolean estado = true;
		try {
			iniciar();
			obtenerSesion().update(valorDato);
			cometerTransacciones();
			System.out.println("Opciones actualizadas");
		} catch (Exception e) {
			e.printStackTrace();
			estado = false;
			regresarEstado();
		}
		return estado;
	}
}
