package tectijuana.votBit.hibernate.dao;

import java.util.List;

import org.apache.log4j.BasicConfigurator;

import tectijuana.votBit.hibernate.Roles;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.util.HibernateUtil;

public class PruebaUsuario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicConfigurator.configure();
		HibernateUtil.verificarFabricaSesion();
		PruebasUsuario();
	}

	public static void Desplegar(String texto)
	{
		System.out.println(texto);
	}
	
	public static void PruebasUsuario()
	{
		Usuario usuario = new Usuario();
		Roles roles = new Roles();
		roles.setId(2);
		usuario.setId(2);
		usuario.setTipoRoles(roles);
		usuario.setContrasena("holajuan");
		usuario.setCorreo("holajuan@hola.com");
		usuario.setEdad("10/04/1994");
		usuario.setNombre("Juan");
		usuario.setPerfil("Usuario");
		
		
		UsuarioDao.guardarUsuario(usuario);
		
		List<Usuario> listaUsuario = UsuarioDao.obtenerUsuario();
		if(listaUsuario == null) {
			System.out.println("No data in table");
		}
		Desplegar(listaUsuario.toString());
				
		//UsuarioDao.borrarUsuario(usuario);
		//Desplegar(UsuarioDao.obtenerUsuario(usuario.getId()).toString());
		System.out.println("22222222222222222222222222222222222222");

		//usuario.setNombre("Jorge");
		//UsuarioDao.actualizarUsuario(usuario);
	}
	
}
