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

import tectijuana.votBit.hibernate.Opciones;
import tectijuana.votBit.hibernate.Pregunta;
import tectijuana.votBit.hibernate.dao.OpcionesDao;
import tectijuana.votBit.hibernate.dao.PreguntaDao;

public class OpcionesResource extends ServerResource {

	public OpcionesResource() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Representation delete() throws ResourceException {
		Representation respuesta = null;
		Form forma = null;
		String idOpciones = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idOpciones = forma.getValues("id");
			System.out.println("Voy a borrar Opciones con Id " + idOpciones);
			if (idOpciones != null) {
				id = Long.parseLong(idOpciones);

				estado = OpcionesDao.borrarOpciones(id);

				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio Id Pregunta";
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
		String idOpciones = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idOpciones = forma.getValues("id");
			if (idOpciones != null) {
				System.out.println("Voy a obtener Opciones con Id " + idOpciones);
				id = Long.parseLong(idOpciones);

				Opciones opciones = OpcionesDao.obtenerOpciones(id);

				if (opciones != null) {
					datoJSON = opciones.toJSON();
				} else {
					estado = false;
					mensaje = "No existe Opciones para el id " + id.toString();
				}
			} else {
				List<Opciones> opcioness = (ArrayList<Opciones>) OpcionesDao.obtenerOpciones();

				String textoJson = "{\"opciones\":[";
				for (Opciones opciones : opcioness) {
					textoJson += opciones.toJSON().toString() + ",";
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
		Opciones opciones = null;

		// Parametros de entidad.
		Long id = null;
		Pregunta idPregunta = null;
		String opcion = null;
		Integer numOpciones = null;

		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());

			if (parametros.has("id")) {

				id = parametros.getLong("id");
				idPregunta = PreguntaDao.obtenerPregunta(parametros.getLong("idPregunta"));
				opcion = parametros.getString("opcion");
				numOpciones = parametros.getInt("numOpciones");

				if (id != null && idPregunta != null && opcion != null && numOpciones != null) {
					System.out.println("Se tratara de actualizar registro.");

					opciones = new Opciones();
					opciones.setId(id);
					opciones.setIdPregunta(idPregunta);
					opciones.setNumOpciones(numOpciones);
					opciones.setOpcion(opcion);
					
					estado = OpcionesDao.actualizarOpciones(opciones);

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
				mensaje = "Falto enviar id de Opciones.";
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
		Opciones opciones = null;

		// Parametros de entidad.
		Pregunta idPregunta = null;
		String opcion = null;
		Integer numOpciones = null;
		
		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());
			idPregunta = PreguntaDao.obtenerPregunta(parametros.getLong("idPregunta"));
			opcion = parametros.getString("opcion");
			numOpciones = parametros.getInt("numOpciones");


			if (idPregunta != null && opcion != null && numOpciones != null) {
				System.out.println("Se tratara de agregar nuevo registro.");

				opciones = new Opciones();
				opciones.setIdPregunta(idPregunta);
				opciones.setNumOpciones(numOpciones);
				opciones.setOpcion(opcion);

				estado = OpcionesDao.guardarOpciones(opciones);

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
