package tectijuana.votBit.hibernate.dao;

import java.util.List;

import javax.persistence.Query;

import tectijuana.votBit.hibernate.Categoria;
import tectijuana.votBit.hibernate.Roles;

public class CategoriaDao extends DAO {
	public static List<Categoria> obtenerCategoria() {
		List<Categoria> lista = null;
		Query consulta = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Categoria");
			lista = consulta.getResultList();
			cometerTransacciones();
		}
		catch(Exception e) {
			regresarEstado();
		}
		
		return lista;
	}
	
	public static Categoria obtenerCategoria(long valorId) {
		Query consulta = null;
		Categoria dato = null;
		
		try {
			iniciar();
			consulta = obtenerSesion().createQuery("FROM Categoria WHERE id = :idCategoria");
			consulta.setParameter("idCategoria", valorId);
			dato = (Categoria) consulta.getSingleResult();
			cometerTransacciones();
			System.out.println("Categoria obtenida");
		}
		catch (Exception e) {
			regresarEstado();
		}
		return dato;
	}
	
	public static boolean guardarCategoria(Categoria valorDato) {
		boolean estado = true;
		
		try {
			iniciar();
			obtenerSesion().save(valorDato);
			cometerTransacciones();
			System.out.println("Categoria Guardada");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
	public static boolean borrarCategoria(long valorId) {
		boolean estado = true;
		Categoria dato = null;
		
		try {
			dato = obtenerCategoria(valorId);
			iniciar();
			obtenerSesion().delete(dato);
			cometerTransacciones();
			System.out.println("Categoria borrada");
		}
		catch (Exception e) {
			estado = false;
			regresarEstado();
		}
		return estado;
	}
		
	public static boolean actualizarCategoria(Categoria valorDato){
		boolean estado = true;
		try {
			iniciar();
			obtenerSesion().update(valorDato);
			cometerTransacciones();
			System.out.println("Categoria actualizada");
		} catch (Exception e) {
			e.printStackTrace();
			estado = false;
			regresarEstado();
		}
		return estado;
	}
	
}
