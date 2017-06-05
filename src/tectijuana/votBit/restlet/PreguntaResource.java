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

import tectijuana.votBit.hibernate.Categoria;
import tectijuana.votBit.hibernate.Pregunta;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.dao.CategoriaDao;
import tectijuana.votBit.hibernate.dao.PreguntaDao;
import tectijuana.votBit.hibernate.dao.UsuarioDao;

public class PreguntaResource extends ServerResource {

	public PreguntaResource() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Representation delete() throws ResourceException {
		Representation respuesta = null;
		Form forma = null;
		String idPregunta = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idPregunta = forma.getValues("id");
			System.out.println("Voy a borrar Pregunta con Id " + idPregunta);
			if (idPregunta != null) {
				id = Long.parseLong(idPregunta);

				estado = PreguntaDao.borrarPregunta(id);

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
		String idPregunta = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idPregunta = forma.getValues("id");
			if (idPregunta != null) {
				System.out.println("Voy a obtener Pregunta con Id " + idPregunta);
				id = Long.parseLong(idPregunta);

				Pregunta pregunta = PreguntaDao.obtenerPregunta(id);

				if (pregunta != null) {
					datoJSON = pregunta.toJSON();
				} else {
					estado = false;
					mensaje = "No existe un Pregunta para el id " + id.toString();
				}
			} else {
				List<Pregunta> preguntas = (ArrayList<Pregunta>) PreguntaDao.obtenerPregunta();

				String textoJson = "{\"pregunta\":[";
				for (Pregunta pregunta : preguntas) {
					textoJson += pregunta.toJSON().toString() + ",";
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
		Pregunta pregunta = null;

		// Parametros de entidad.
		Long id = null;
		Usuario idUsuario = null;
		Categoria idCategoria = null;
		String titulo = null;
		String creado = null;
		String modificado = null;

		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());

			if (parametros.has("id")) {

				id = parametros.getLong("id");
				titulo = parametros.getString("titulo");
				creado = parametros.getString("creado");
				modificado = parametros.getString("modificado");
				idUsuario = UsuarioDao.obtenerUsuario(parametros.getLong("idUsuario"));
				idCategoria = CategoriaDao.obtenerCategoria(parametros.getLong("idCategoria"));

				if (id != null && titulo != null && creado != null && modificado != null && idUsuario != null && idCategoria != null) {
					System.out.println("Se tratara de actualizar registro.");

					pregunta = new Pregunta();
					pregunta.setId(id);
					pregunta.setTitulo(titulo);
					pregunta.setCreado(creado);
					pregunta.setIdCategoria(idCategoria);
					pregunta.setIdUsuario(idUsuario);
					pregunta.setModificado(modificado);

					estado = PreguntaDao.actualizarPregunta(pregunta);

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
				mensaje = "Falto enviar id de Pregunta.";
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
		Pregunta pregunta = null;

		// Parametros de entidad.
		Usuario idUsuario = null;
		Categoria idCategoria = null;
		String titulo = null;
		String creado = null;
		String modificado = null;
		
		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());
			titulo = parametros.getString("titulo");
			creado = parametros.getString("creado");
			modificado = parametros.getString("modificado");
			idUsuario = UsuarioDao.obtenerUsuario(parametros.getLong("idUsuario"));
			idCategoria = CategoriaDao.obtenerCategoria(parametros.getLong("idCategoria"));


			if (titulo != null && creado != null && modificado != null && idUsuario != null && idCategoria != null) {
				System.out.println("Se tratara de agregar nuevo registro.");

				pregunta = new Pregunta();
				pregunta.setTitulo(titulo);
				pregunta.setCreado(creado);
				pregunta.setIdCategoria(idCategoria);
				pregunta.setIdUsuario(idUsuario);
				pregunta.setModificado(modificado);

				estado = PreguntaDao.guardarPregunta(pregunta);

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
