package tectijuana.votBit.restlet;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import tectijuana.votBit.hibernate.Roles;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.dao.RolesDao;
import tectijuana.votBit.hibernate.dao.UsuarioDao;

public class UsuarioResource extends ServerResource {

	public UsuarioResource() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Representation delete() throws ResourceException {
		Representation respuesta = null;
		Form forma = null;
		String idUsuario = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idUsuario = forma.getValues("id");
			System.out.println("Voy a borrar Usuario con Id " + idUsuario);
			if (idUsuario != null) {
				id = Long.parseLong(idUsuario);

				estado = UsuarioDao.borrarUsuario(id);

				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio Id Usuario";
			}
		} catch (JSONException e) {
			estado = false;
			mensaje = "Error en el servicio.";
		} catch (Exception e) {
			estado = false;
			mensaje = "Error en el servicio.";
		} finally {
			datoJSON = new JSONObject();
			datoJSON.put("estado", estado);
			datoJSON.put("mensaje", mensaje);

			respuesta = new JsonRepresentation(datoJSON);
		}

		return respuesta;
	}

	@Override
	public Representation get() throws ResourceException {
		Representation respuesta = null;
		Form forma = null;
		String idUsuario = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idUsuario = forma.getValues("id");
			if (idUsuario != null) {
				System.out.println("Voy a obtener Usuario con Id " + idUsuario);
				id = Long.parseLong(idUsuario);

				Usuario usuario = UsuarioDao.obtenerUsuario(id);

				if (usuario != null) {
					datoJSON = usuario.toJSON();
				} else {
					estado = false;
					mensaje = "No existe un Usuario para el id " + id.toString();
				}
			} else {
				List<Usuario> usuarios = (ArrayList<Usuario>) UsuarioDao.obtenerUsuario();

				String textoJson = "{\"usuario\":[";
				for (Usuario usuario : usuarios) {
					textoJson += usuario.toJSON().toString() + ",";
				}
				textoJson += "]}";
				System.out.println(textoJson);
				datoJSON = new JSONObject(textoJson);
			}
		} catch (JSONException e) {
			estado = false;
			mensaje = "Error en el servicio de JSON.";
		} catch (Exception e) {
			estado = false;
			mensaje = "Error en el servicio o id no valido.";
		} finally {
			if (datoJSON == null) {
				datoJSON = new JSONObject();
				datoJSON.put("estado", estado);
				datoJSON.put("mensaje", mensaje);
			}

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
		Usuario usuario = null;

		// Parametros de entidad.
		Long id = null;
		String nombre = null;
		String perfil = null;
		String Edad = null;
		String correo = null;
		String contrasena = null;
		Roles tipoRoles = null;

		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());

			if (parametros.has("id")) {

				id = parametros.getLong("id");
				nombre = parametros.getString("nombre");
				perfil = parametros.getString("perfil");
				Edad = parametros.getString("edad");
				correo = parametros.getString("correo");
				contrasena = parametros.getString("contrasena");
				tipoRoles = RolesDao.obtenerRoles(parametros.getLong("tipoRoles"));
				

				if (id != null && nombre != null && perfil != null && Edad != null && correo != null && contrasena != null && tipoRoles != null) {
					System.out.println("Se tratara de actualizar registro.");

					usuario = new Usuario();
					usuario.setId(id);
					usuario.setNombre(nombre);
					usuario.setContrasena(contrasena);
					usuario.setCorreo(correo);
					usuario.setEdad(Edad);
					usuario.setPerfil(perfil);
					usuario.setTipoRoles(tipoRoles);

					estado = UsuarioDao.actualizarUsuario(usuario);

					if (estado) {
						mensaje = "Registro actualizado.";
					} else {
						mensaje = "Registro no actualizado.";
					}
				} else {
					estado = false;
					mensaje = "Falto enviar algun valor.";
				}
			} else {
				estado = false;
				mensaje = "Falto enviar id de Usuario.";
			}
		} catch (JSONException e) {
			System.out.println(e.toString());
			estado = false;
			mensaje = "Error en el servicio JSON.";
		} catch (Exception e) {
			estado = false;
			mensaje = "Error en el servicio.";
		} finally {
			datoJSON = new JSONObject();
			datoJSON.put("estado", estado);
			datoJSON.put("mensaje", mensaje);

			respuesta = new JsonRepresentation(datoJSON);
		}

		return respuesta;
	}

	@Override
	public Representation post(Representation entity) throws ResourceException {
		Representation respuesta = null;
		JSONObject parametros = null;
		Boolean estado = null;
		String mensaje = null;
		JSONObject datoJSON = null;
		Usuario usuario = null;

		// Parametros de entidad.
		String nombre = null;
		String perfil = null;
		String Edad = null;
		String correo = null;
		String contrasena = null;
		Roles tipoRoles = null;
		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());
			nombre = parametros.getString("nombre");
			perfil = parametros.getString("perfil");
			Edad = parametros.getString("edad");
			correo = parametros.getString("correo");
			contrasena = parametros.getString("contrasena");
			tipoRoles = RolesDao.obtenerRoles(parametros.getLong("tipoRoles"));


			if (nombre != null && perfil != null && Edad != null && correo != null && contrasena != null && tipoRoles != null) {
				System.out.println("Se tratara de agregar nuevo registro.");

				usuario = new Usuario();
				usuario.setNombre(nombre);
				usuario.setContrasena(contrasena);
				usuario.setCorreo(correo);
				usuario.setEdad(Edad);
				usuario.setPerfil(perfil);
				usuario.setTipoRoles(tipoRoles);


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
			estado = false;
			mensaje = "Error en el servicio JSON.";
		} catch (Exception e) {
			estado = false;
			mensaje = "Error en el servicio.";
		} finally {
			datoJSON = new JSONObject();
			datoJSON.put("estado", estado);
			datoJSON.put("mensaje", mensaje);

			respuesta = new JsonRepresentation(datoJSON);
		}

		return respuesta;
	}
}
