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
import tectijuana.votBit.hibernate.dao.CategoriaDao;

public class CategoriaResource extends ServerResource {

	public CategoriaResource() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Representation delete() throws ResourceException {
		Representation respuesta = null;
		Form forma = null;
		String idCategoria = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idCategoria = forma.getValues("id");
			System.out.println("Voy a borrar Categoria con Id " + idCategoria);
			if (idCategoria != null) {
				id = Long.parseLong(idCategoria);

				estado = CategoriaDao.borrarCategoria(id);

				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio Id Categoria";
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
		String idCategoria = null;
		Boolean estado = null;
		Long id = null;
		String mensaje = null;
		JSONObject datoJSON = null;

		try {
			forma = this.getQuery();
			idCategoria = forma.getValues("id");
			if (idCategoria != null) {
				System.out.println("Voy a obtener Categoria con Id " + idCategoria);
				id = Long.parseLong(idCategoria);

				Categoria categoria = CategoriaDao.obtenerCategoria(id);

				if (categoria != null) {
					datoJSON = categoria.toJSON();
				} else {
					estado = false;
					mensaje = "No existe una Categoria para el id " + id.toString();
				}
			} else {
				List<Categoria> categorias = (ArrayList<Categoria>) CategoriaDao.obtenerCategoria();

				String textoJson = "{\"categoria\":[";
				for (Categoria categoria : categorias) {
					textoJson += categoria.toJSON().toString() + ",";
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
		Categoria categoria = null;

		// Parametros de entidad.
		Long id = null;
		String nombre = null;

		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());

			if (parametros.has("id")) {

				id = parametros.getLong("id");
				nombre = parametros.getString("nombre");

				if (id != null && nombre != null) {
					System.out.println("Se tratara de actualizar registro.");

					categoria = new Categoria();
					categoria.setId(id);
					categoria.setNombre(nombre);

					estado = CategoriaDao.actualizarCategoria(categoria);

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
				mensaje = "Falto enviar id de Categoria.";
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
		Categoria categoria = null;

		// Parametros de entidad.
		String nombre = null;

		try {
			// Obtener y asignar valores para nueva entidad.
			parametros = new JSONObject(entity.getText());
			nombre = parametros.getString("nombre");


			if (nombre != null) {
				System.out.println("Se tratara de agregar nuevo registro.");

				categoria = new Categoria();
				categoria.setNombre("nombre");

				estado = CategoriaDao.guardarCategoria(categoria);

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
