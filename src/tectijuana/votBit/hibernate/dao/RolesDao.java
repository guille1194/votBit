package tectijuana.votBit.hibernate.dao;

import java.util.List;

import javax.persistence.Query;

import tectijuana.votBit.hibernate.Roles;

public class RolesDao extends DAO {
	public static List<Roles> obtenerRoles() {
		List<Roles> lista = null;
		Query consulta = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Roles");
			lista = consulta.getResultList();
			cometerTransacciones();
		}
		catch(Exception e) {
			regresarEstado();
		}
		
		return lista;
	}
	
	public static Roles obtenerRoles(long valorId) {
		Query consulta = null;
		Roles dato = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Roles WHERE id = :idRoles");
			consulta.setParameter("idRoles", valorId);
			dato = (Roles) consulta.getSingleResult();
			cometerTransacciones();
			System.out.println("Roles obtenidos");
		}
		catch (Exception e) {
			regresarEstado();
		}
		return dato;
	}
	
	public static boolean guardarRoles(Roles valorDato) {
		boolean estado = true;
		
		try {
			iniciar();
			obtenerSesion().save(valorDato);
			cometerTransacciones();
			System.out.println("Roles guardados");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean borrarRoles(long valorId) {
		boolean estado = true;
		Roles dato = null;
		
		try {
			dato = obtenerRoles(valorId);
			iniciar();
			obtenerSesion().delete(dato);
			cometerTransacciones();
			System.out.println("Roles borrado");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean actualizarRoles(Roles valorDato){
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
