package tectijuana.votBit.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import tectijuana.votBit.hibernate.util.HibernateUtil;


public class DAO {
	private static final ThreadLocal<Session> sesionLocal = new ThreadLocal<Session>();
	
	protected DAO() {
		HibernateUtil.verificarFabricaSesion();
	}
	
	public static Session obtenerSesion() {
		Session sesionHibernate = null;
		
		try {
			sesionHibernate = sesionLocal.get();
			
			if(sesionHibernate == null) {
				sesionHibernate = HibernateUtil.obtenerFabricaSesion().openSession();
				sesionLocal.set(sesionHibernate);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[DAO.obtenerSesion] Error Hibernate --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[DAO.obtenerSesion] Error --> " + e);
		}
		
		return sesionHibernate;
	}
	
	protected static void iniciar() {
		obtenerSesion().beginTransaction();
	}
	
	protected static void cometerTransacciones() {
		obtenerSesion().getTransaction().commit();
	}
	
	protected static void regresarEstado() {
		try {
			obtenerSesion().getTransaction().rollback();
		}catch(HibernateException e) {
			System.out.println("[DAO.regresarEstado] Error Hibernate -->" + e);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[DAO.cerrarSesion] Error --> " + e);
		} finally {
			cerrarSesion();
		}
	}
	
	public static void cerrarSesion() {
		try {
			obtenerSesion().close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[DAO.cerrarSesion] Error Hibernate --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[DAO.cerrarSesion] Error --> " + e);
		} finally {
			sesionLocal.set(null);
		}
	}
	
	public static void vaciarSesion() {
		try {
			obtenerSesion().flush();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[DAO.vaciarSesion] Error Hibernate --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[DAO.vaciarSesion] Error --> " + e);
		}
	}
	
	public static void limpiarSesion() {
		try {
			obtenerSesion().clear();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[DAO.limpiarSesion] Error Hibernate --> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[DAO.limpiarSesion] Error --> " + e);
		}
	}
}