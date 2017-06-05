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

import tectijuana.votBit.hibernate.Pregunta;
import tectijuana.votBit.hibernate.Respuesta;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.dao.PreguntaDao;
import tectijuana.votBit.hibernate.dao.RespuestaDao;
import tectijuana.votBit.hibernate.dao.UsuarioDao;

public class RespuestaResource extends ServerResource {

	public RespuestaResource() {

	}

	@Override
	public Representation delete() throws ResourceException {
		Representation respuesta = null;
		Form forma = null;
		String idRespuesta = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idRespuesta = forma.getValues("id");
			System.out.println("Voy a borrar Respuestas con Id " + idRespuesta);
			if (idRespuesta != null) {
				id = Long.parseLong(idRespuesta);

				estado = RespuestaDao.borrarRespuesta(id);

				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio Id Respuesta";
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
		String idRespuesta = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idRespuesta = forma.getValues("id");
			if (idRespuesta != null) {
				System.out.println("Voy a obtener Respuesta con Id " + idRespuesta);
				id = Long.parseLong(idRespuesta);

				Respuesta respuestas = RespuestaDao.obtenerRespuesta(id);

				if (respuestas != null) {
					datoJSON = respuestas.toJSON();
				} else {
					estado = false;
					mensaje = "No existe Respuesta para el id " + id.toString();
				}
			} else {
				List<Respuesta> respuestass = (ArrayList<Respuesta>) RespuestaDao.obtenerRespuesta();

				String textoJson = "{\"respuesta\":[";
				for (Respuesta respuestas : respuestass) {
					textoJson += respuestas.toJSON().toString() + ",";
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
		Respuesta respuestas = null;

		// Parametros de entidad.
		Long id = null;
		Usuario idUsuario = null;
		Pregunta idPregunta = null;
		String creado = null;
		String modificado = null;
		String respuestass = null;

		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());

			if (parametros.has("id")) {

				id = parametros.getLong("id");
				idPregunta = PreguntaDao.obtenerPregunta(parametros.getLong("idPregunta"));
				idUsuario = UsuarioDao.obtenerUsuario(parametros.getLong("idUsuario"));
				respuestass = parametros.getString("respuesta");
				creado = parametros.getString("creado");
				modificado = parametros.getString("modificado");

				if (id != null && idPregunta != null && idUsuario != null && respuestass != null && creado != null && modificado != null) {
					System.out.println("Se tratara de actualizar registro.");

					respuestas = new Respuesta();
					respuestas.setCreado(creado);
					respuestas.setId(id);
					respuestas.setIdPregunta(idPregunta);
					respuestas.setIdUsuario(idUsuario);
					respuestas.setModificado(modificado);
					respuestas.setRespuesta(respuestass);
					
					estado = RespuestaDao.actualizarRespuesta(respuestas);

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
				mensaje = "Falto enviar id de Respuesta.";
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
		Respuesta respuestas = null;

		// Parametros de entidad.
		Usuario idUsuario = null;
		Pregunta idPregunta = null;
		String creado = null;
		String modificado = null;
		String respuestass = null;
		
		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());
			idPregunta = PreguntaDao.obtenerPregunta(parametros.getLong("idPregunta"));
			idUsuario = UsuarioDao.obtenerUsuario(parametros.getLong("idUsuario"));
			respuestass = parametros.getString("respuesta");
			creado = parametros.getString("creado");
			modificado = parametros.getString("modificado");


			if (idPregunta != null && idUsuario != null && respuestass != null && creado != null && modificado != null) {
				System.out.println("Se tratara de agregar nuevo registro.");

				respuestas = new Respuesta();
				respuestas.setCreado(creado);
				respuestas.setIdPregunta(idPregunta);
				respuestas.setIdUsuario(idUsuario);
				respuestas.setModificado(modificado);
				respuestas.setRespuesta(respuestass);

				estado = RespuestaDao.actualizarRespuesta(respuestas);

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
