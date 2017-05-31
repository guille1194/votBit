package tectijuana.votBit.restlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.data.Parameter;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import tectijuana.votBit.hibernate.Roles;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.dao.UsuarioDao;

public class UsuarioResource extends ServerResource {
	
	public UsuarioResource() {
		
	}
	
	@Override
	protected Representation get() throws ResourceException {
		// TODO Auto-generated method stub
		Representation respuesta = null;
		JSONObject datoJSON = new JSONObject();
		UsuarioDao usuarioDao = null;
		List<Usuario> parameters = new ArrayList<Usuario>();
		Series<Parameter> headers = (Series<Parameter>) getRequest().getAttributes().get("org.restlet.http.headers");
		String valores = headers.getValues("id");
		
		if(valores == null) {
			usuarioDao = new UsuarioDao();
			datoJSON.put("Data", usuarioDao.obtenerUsuario());
			datoJSON.put("mensaje", "servicio funcionando");
		}
		else {
			String [] str = valores.split(", ");
			usuarioDao = new UsuarioDao();
			for(String s : str) {
				parameters.add(usuarioDao.obtenerUsuario(Long.parseLong(s)));
			}
			datoJSON.put("dato", parameters);
			datoJSON.put("mensaje", "servicio funcionando");
		}
		respuesta = new JsonRepresentation(datoJSON);
		
		return respuesta;
		
	}
	
	@Override
	protected Representation post(Representation entity) throws ResourceException{
		Representation respuesta = null;
		JSONObject parametros = null;
		Boolean estado = null;
		String mensaje = null;
		JSONObject datoJSON = null;
		
		//Parametros Modelo
		String NombreUsuario = null;
		String ContrasenaUsuario = null;
		String CorreoUsuario = null;
		String EdadUsuario = null;
		String PerfilUsuario = null;
		Roles NombreIdRoles = null;
		
		Long idRoles = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			NombreUsuario = parametros.getString("nombre");
			ContrasenaUsuario = parametros.getString("contrasena");
			CorreoUsuario = parametros.getString("correo");
			EdadUsuario = parametros.getString("edad");
			PerfilUsuario = parametros.getString("perfil");
			idRoles = parametros.getLong("tipoRoles");
			
			if (NombreUsuario != null && ContrasenaUsuario != null && CorreoUsuario != null && EdadUsuario != null && PerfilUsuario != null && idRoles != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				Usuario usuario = new Usuario();
				
				NombreIdRoles = new Roles();
				NombreIdRoles.setId(idRoles);
				
				usuario.setContrasena(ContrasenaUsuario);
				usuario.setCorreo(CorreoUsuario);
				usuario.setEdad(EdadUsuario);
				usuario.setPerfil(PerfilUsuario);
				usuario.setNombre(NombreUsuario);

				estado = UsuarioDao.guardarUsuario(usuario);
				
				if (estado) {
					mensaje = "Registro agregado.";
				} else {
					mensaje = "Registro no agregado.";
				} 
			} else {
				estado = false;
				mensaje = "Falto enviar algun valor.";
			} 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			estado = false;
			mensaje = "Error en el servicio JSON.";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			estado = false;
			mensaje = "Error en el servicio.";
		} finally {
			// TODO: handle finally clause
			datoJSON = new JSONObject();
			datoJSON.put("estado", estado);
			datoJSON.put("mensaje", mensaje);

			respuesta = new JsonRepresentation(datoJSON);
		}
		return respuesta;
	}

	@Override
	public Representation put(Representation entity) throws ResourceException {
		Representation respuesta = null;
		JSONObject parametros = null;
		Boolean estado = null;
		String mensaje = null;
		JSONObject datoJSON = null;
		
		//Parametros Modelo
		String NombreUsuario = null;
		String ContrasenaUsuario = null;
		String CorreoUsuario = null;
		String EdadUsuario = null;
		String PerfilUsuario = null;
		Roles NombreIdRoles = null;
		
		Long idRoles = null;
		Long id = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			NombreUsuario = parametros.getString("nombre");
			ContrasenaUsuario = parametros.getString("contrasena");
			CorreoUsuario = parametros.getString("correo");
			EdadUsuario = parametros.getString("edad");
			PerfilUsuario = parametros.getString("perfil");
			idRoles = parametros.getLong("tipoRoles");
			id = parametros.getLong("id");
			if (NombreUsuario != null && ContrasenaUsuario != null && CorreoUsuario != null && EdadUsuario != null && PerfilUsuario != null && idRoles != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
								
				Usuario usuario = new Usuario();
				
				NombreIdRoles = new Roles();
				NombreIdRoles.setId(idRoles);
				
				usuario.setId(id);
				usuario.setContrasena(ContrasenaUsuario);
				usuario.setCorreo(CorreoUsuario);
				usuario.setEdad(EdadUsuario);
				usuario.setPerfil(PerfilUsuario);
				usuario.setNombre(NombreUsuario);
				


				estado = UsuarioDao.guardarUsuario(usuario);
				
				if (estado) {
					mensaje = "Registro agregado.";
				} else {
					mensaje = "Registro no agregado.";
				} 
			} else {
				estado = false;
				mensaje = "Falto enviar algun valor.";
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			estado = false;
			mensaje = "Error en el servicio.";
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
			estado = false;
			mensaje = "Error en el servicio JSON.";
		} finally {
			// TODO: handle finally clause
			datoJSON = new JSONObject();
			datoJSON.put("estado", estado);
			datoJSON.put("mensaje", mensaje);

			respuesta = new JsonRepresentation(datoJSON);
		}

		return respuesta;
	}
	
	@Override
	protected Representation delete() throws ResourceException {
		Representation respuesta = null;
		Form forma = null;
		String idUsuario = null;
		Boolean estado = null;
		Long Id = null;
		String mensaje = null;
		JSONObject datoJSON = null;
		
		try {
			forma = this.getQuery();
			idUsuario = forma.getValues("id");
			System.out.println("Voy a borrar respuesta con id " + idUsuario);
			if (idUsuario != null) {
				Id = Long.parseLong(idUsuario);
				Usuario usuario = new Usuario();
				usuario.setId(Id);
				estado = UsuarioDao.borrarUsuario(usuario);
				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio ID respuesta";
			} 
			
		} catch (Exception e) {
			// TODO: handle exception
			estado = false;
			mensaje = "Error en el servicio.";
		} finally{
			datoJSON = new JSONObject();
			datoJSON.put("estado", estado);
			datoJSON.put("mensaje", mensaje);

			respuesta = new JsonRepresentation(datoJSON);
		}
		
		return respuesta;
	}


}
