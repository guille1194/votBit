package tectijuana.votBit.hibernate.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory fabricaSesion;
	
    /*private static void crearFabricaSesion() {
    	Configuration  configuracion     = null;
    	ServiceRegistry servicioRegistro = null;
    	
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	configuracion = new Configuration();
        	configuracion.configure();
        	
        	servicioRegistro = new StandardServiceRegistryBuilder().applySettings(configuracion.getProperties()).build();
        	
            fabricaSesion = configuracion.buildSessionFactory(servicioRegistro);
        } catch (Exception e) {
        	//Make sure you log the exception, as it might be swallowed
            System.out.println("[HibernateUtil.crearFabricaSesion] Error ---> " + e);
        }
    }*/
    
    private static void crearFabricaSesion() {
    	Configuration configuracion = null;
    	
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	configuracion = new Configuration();
        	configuracion.configure();
        	
            fabricaSesion = configuracion.buildSessionFactory();
        } catch (Exception e) {
        	//Make sure you log the exception, as it might be swallowed
            System.out.println("[HibernateUtil.crearFabricaSesion] Error ---> " + e);
        }
    }
    
    public static SessionFactory obtenerFabricaSesion() {
        return fabricaSesion;
    }
    
    public static void cerrarFabricaSesion(){
    	try {
    		if(fabricaSesion != null){
    			fabricaSesion.close();
    		}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[HibernateUtil.cerrarFabricaSesion] Error Hibernate ---> " + e);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[HibernateUtil.cerrarFabricaSesion] Error ---> " + e);
		} finally {
			fabricaSesion = null;
		}
    }
    
    public static void verificarFabricaSesion(){
    	System.out.println("/*********************************************************/");
    	System.out.println("Verificando Fabrica de Sesion!");
    	
    	try {
			if(fabricaSesion != null){
				if(fabricaSesion.isClosed()){
					System.out.println("Creamos Fabrica de Sesion, esta cerrada!");
					fabricaSesion = null;
					crearFabricaSesion();
				} else {
					System.out.println("Esta Creada la Fabrica de Sesion!");
				}
			} else {
				System.out.println("Creamos Fabrica de Sesion, es null!");
				crearFabricaSesion();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("[HibernateUtil.verificarFabricaSesion] Error ---> " + e);
		}
    	
    	System.out.println("/*********************************************************/");
    }
}