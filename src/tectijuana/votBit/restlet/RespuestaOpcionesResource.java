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
import tectijuana.votBit.hibernate.RespuestaOpciones;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.dao.PreguntaDao;
import tectijuana.votBit.hibernate.dao.RespuestaOpcionesDao;
import tectijuana.votBit.hibernate.dao.UsuarioDao;

public class RespuestaOpcionesResource extends ServerResource {

	public RespuestaOpcionesResource() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Representation delete() throws ResourceException {
		Representation respuesta = null;
		Form forma = null;
		String idRespuestaOpciones = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idRespuestaOpciones = forma.getValues("id");
			System.out.println("Voy a borrar Respuesta Opciones con Id " + idRespuestaOpciones);
			if (idRespuestaOpciones != null) {
				id = Long.parseLong(idRespuestaOpciones);

				estado = RespuestaOpcionesDao.borrarRespuestaOpciones(id);

				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio Id RespuestaOpciones";
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
		String idRespuestaOpciones = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idRespuestaOpciones = forma.getValues("id");
			if (idRespuestaOpciones != null) {
				System.out.println("Voy a obtener RespuestaOpciones con Id " + idRespuestaOpciones);
				id = Long.parseLong(idRespuestaOpciones);

				RespuestaOpciones respuestaOpciones = RespuestaOpcionesDao.obtenerRespuestaOpciones(id);

				if (respuestaOpciones != null) {
					datoJSON = respuestaOpciones.toJSON();
				} else {
					estado = false;
					mensaje = "No existe RespuestaOpciones para el id " + id.toString();
				}
			} else {
				List<RespuestaOpciones> respuestaOpcioness = (ArrayList<RespuestaOpciones>) RespuestaOpcionesDao.obtenerRespuestaOpciones();

				String textoJson = "{\"respuestaopciones\":[";
				for (RespuestaOpciones respuestaOpciones : respuestaOpcioness) {
					textoJson += respuestaOpciones.toJSON().toString() + ",";
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
		RespuestaOpciones respuestaOpciones = null;

		// Parametros de entidad.
		Long id = null;
		Usuario idUsuario = null;
		Pregunta idPregunta = null;
		String creado = null;
		String modificado = null;
		String respuestaOpc = null;

		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());

			if (parametros.has("id")) {

				id = parametros.getLong("id");
				idPregunta = PreguntaDao.obtenerPregunta(parametros.getLong("idPregunta"));
				idUsuario = UsuarioDao.obtenerUsuario(parametros.getLong("idUsuario"));
				respuestaOpc = parametros.getString("respuesta");
				creado = parametros.getString("creado");
				modificado = parametros.getString("modificado");

				if (id != null && idPregunta != null && idUsuario != null && respuestaOpc != null && creado != null && modificado != null) {
					System.out.println("Se tratara de actualizar registro.");

					respuestaOpciones = new RespuestaOpciones();
					respuestaOpciones.setCreado(creado);
					respuestaOpciones.setId(id);
					respuestaOpciones.setIdPregunta(idPregunta);
					respuestaOpciones.setIdUsuario(idUsuario);
					respuestaOpciones.setModificado(modificado);
					respuestaOpciones.setRespuesta(respuestaOpc);
					
					estado = RespuestaOpcionesDao.actualizarRespuestaOpciones(respuestaOpciones);

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
				mensaje = "Falto enviar id de RespuestaOpciones.";
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
		RespuestaOpciones respuestaOpciones = null;

		// Parametros de entidad.
		Usuario idUsuario = null;
		Pregunta idPregunta = null;
		String creado = null;
		String modificado = null;
		String respuestaOpc = null;
		
		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());
			idPregunta = PreguntaDao.obtenerPregunta(parametros.getLong("idPregunta"));
			idUsuario = UsuarioDao.obtenerUsuario(parametros.getLong("idUsuario"));
			respuestaOpc = parametros.getString("respuesta");
			creado = parametros.getString("creado");
			modificado = parametros.getString("modificado");


			if (idPregunta != null && idUsuario != null && respuestaOpc != null && creado != null && modificado != null) {
				System.out.println("Se tratara de agregar nuevo registro.");

				respuestaOpciones = new RespuestaOpciones();
				respuestaOpciones.setCreado(creado);
				respuestaOpciones.setIdPregunta(idPregunta);
				respuestaOpciones.setIdUsuario(idUsuario);
				respuestaOpciones.setModificado(modificado);
				respuestaOpciones.setRespuesta(respuestaOpc);

				estado = RespuestaOpcionesDao.actualizarRespuestaOpciones(respuestaOpciones);

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
