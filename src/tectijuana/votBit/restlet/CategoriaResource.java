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

import tectijuana.votBit.hibernate.Categoria;
import tectijuana.votBit.hibernate.dao.CategoriaDao;

public class CategoriaResource extends ServerResource {

	public CategoriaResource() {
	}

	@Override
	protected Representation get() throws ResourceException {
		Representation respuesta = null;
		JSONObject datoJSON = new JSONObject();
		CategoriaDao categoriaDao = null;
		List<Categoria> parameters = new ArrayList<Categoria>();
		Series<Parameter> headers = (Series<Parameter>) getRequest().getAttributes().get("org.restlet.http.headers");
		String valores = headers.getValues("id");
		
		if(valores == null) {
			categoriaDao = new CategoriaDao();
			datoJSON.put("Data", categoriaDao.obtenerCategoria());
			datoJSON.put("mensaje", "servicio funcionando");
		}
		else {
			System.out.println(valores);
			String [] str = valores.split(", ");
			categoriaDao = new CategoriaDao();
			for(String s : str) {
				parameters.add(categoriaDao.obtenerCategoria(Long.parseLong(s)));
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
		String NombreCategoria = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			NombreCategoria = parametros.getString("nombre");
			
			if (NombreCategoria != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				Categoria categoria = new Categoria();
				
				categoria.setNombre(NombreCategoria);

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
		String NombreCategoria = null;
		Long id = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			NombreCategoria = parametros.getString("nombre");
			id = parametros.getLong("id");
			if (NombreCategoria != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				Categoria categoria = new Categoria();
				categoria.setId(id);
				categoria.setNombre(NombreCategoria);

				estado = CategoriaDao.actualizarCategoria(categoria);
				
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
		String idCategoria = null;
		Boolean estado = null;
		Long Id = null;
		String mensaje = null;
		JSONObject datoJSON = null;
		
		try {
			forma = this.getQuery();
			idCategoria = forma.getValues("id");
			System.out.println("Voy a borrar categoria con id " + idCategoria);
			if (idCategoria != null) {
				Id = Long.parseLong(idCategoria);
				Categoria categoria = new Categoria();
				categoria.setId(Id);
				estado = CategoriaDao.borrarCategoria(categoria);
				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio ID categoria";
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