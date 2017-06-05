package tectijuana.votBit.hibernate.dao;

import java.util.List;

import javax.persistence.Query;

import tectijuana.votBit.hibernate.Usuario;

public class UsuarioDao extends DAO {
	public static List<Usuario> obtenerUsuario() {
		List<Usuario> lista = null;
		Query consulta = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Usuario");
			lista = consulta.getResultList();
			cometerTransacciones();
		}
		catch(Exception e) {
			regresarEstado();
		}
		
		return lista;
	}
	
	public static Usuario obtenerUsuario(long valorId) {
		Query consulta = null;
		Usuario dato = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Usuario WHERE id = :idUsuario");
			consulta.setParameter("idUsuario", valorId);
			dato = (Usuario) consulta.getSingleResult();
			cometerTransacciones();
			System.out.println("Usuario obtenido");
		}
		catch (Exception e) {
			regresarEstado();
		}
		return dato;
	}
	
	public static boolean guardarUsuario(Usuario valorDato) {
		boolean estado = true;
		
		try {
			iniciar();
			obtenerSesion().save(valorDato);
			cometerTransacciones();
			System.out.println("Usuario guardado");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean borrarUsuario(long valorId) {
		boolean estado = true;
		Usuario dato = null;
		
		try {
			dato = obtenerUsuario(valorId);
			iniciar();
			obtenerSesion().delete(dato);
			cometerTransacciones();
			System.out.println("Usuario borrado");

		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean actualizarUsuario(Usuario valorDato){
		boolean estado = true;
		try {
			iniciar();
			obtenerSesion().update(valorDato);
			cometerTransacciones();
			System.out.println("Usuario actualizado");
		} catch (Exception e) {
			e.printStackTrace();
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
}
