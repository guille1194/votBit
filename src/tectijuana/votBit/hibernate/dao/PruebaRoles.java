package tectijuana.votBit.hibernate.dao;

import java.util.List;

import org.apache.log4j.BasicConfigurator;

import tectijuana.votBit.hibernate.Roles;
import tectijuana.votBit.hibernate.util.HibernateUtil;

public class PruebaRoles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicConfigurator.configure();
		HibernateUtil.verificarFabricaSesion();
		PruebasRoles();
	}

	public static void Desplegar(String texto)
	{
		
		System.out.println(texto);
	}
	
	public static void PruebasRoles()
	{
		Roles roles = new Roles();
		//set
		//roles.setId(0);
		//roles.setEstatus("Usuario");
		
		//guardar
		//RolesDao.guardarRoles(roles);
		
		//deplegar
		//List<Roles> listaRoles = RolesDao.obtenerRoles();
		//if(listaRoles == null) {
		//	System.out.println("No data in table");
		//}
		//Desplegar(listaRoles.toString());
		//System.out.println("11111111111111111111111111111111111111");
		//Desplegar(listaRoles.toString());
		
		//actualizar
		//roles.setEstatus("Gestor");
		//RolesDao.actualizarRoles(roles);
		
		//borrar
		RolesDao.borrarRoles(roles);
		
		//Desplegar(RolesDao.obtenerRoles(roles.getId()).toString());
		
		System.out.println("22222222222222222222222222222222222222");

	}
}
