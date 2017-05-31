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
import tectijuana.votBit.hibernate.Pregunta;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.dao.PreguntaDao;

public class PreguntaResource extends ServerResource {
	
	public PreguntaResource() {
		
	}
	
	@Override
	protected Representation get() throws ResourceException {
		// TODO Auto-generated method stub
		Representation respuesta = null;
		JSONObject datoJSON = new JSONObject();
		PreguntaDao preguntaDao = null;
		List<Pregunta> parameters = new ArrayList<Pregunta>();
		Series<Parameter> headers = (Series<Parameter>) getRequest().getAttributes().get("org.restlet.http.headers");
		String valores = headers.getValues("id");
		
		if(valores == null) {
			preguntaDao = new PreguntaDao();
			datoJSON.put("Data", preguntaDao.obtenerPregunta());
			datoJSON.put("mensaje", "servicio funcionando");
		}
		else {
			String [] str = valores.split(", ");
			preguntaDao = new PreguntaDao();
			for(String s : str) {
				parameters.add(preguntaDao.obtenerPregunta(Long.parseLong(s)));
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
		String TituloPregunta = null;
		String PreguntaCreado = null;
		String PreguntaModificado = null;
		Categoria NombreIdCategoria = null;
		Usuario NombreIdUsuario = null;
		
		Long idCategoria = null;
		Long idUsuario = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			idCategoria = parametros.getLong("idCategoria");
			idUsuario = parametros.getLong("idUsuario");
			TituloPregunta = parametros.getString("titulo");
			PreguntaCreado = parametros.getString("creado");
			PreguntaModificado = parametros.getString("modificado");
			
			if (idCategoria != null && idUsuario != null && TituloPregunta != null && PreguntaCreado != null && PreguntaModificado != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				Pregunta pregunta = new Pregunta();
				
				NombreIdCategoria = new Categoria();
				NombreIdCategoria.setId(idCategoria);
				
				NombreIdUsuario = new Usuario();
				NombreIdUsuario.setId(idUsuario);
				
				pregunta.setIdCategoria(NombreIdCategoria);
				pregunta.setIdUsuario(NombreIdUsuario);
				pregunta.setTitulo(TituloPregunta);
				pregunta.setCreado(PreguntaCreado);
				pregunta.setModificado(PreguntaModificado);

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
		String TituloPregunta = null;
		String PreguntaCreado = null;
		String PreguntaModificado = null;
		Categoria NombreIdCategoria = null;
		Usuario NombreIdUsuario = null;
		
		Long idCategoria = null;
		Long idUsuario = null;
		Long id = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			idCategoria = parametros.getLong("idCategoria");
			idUsuario = parametros.getLong("idUsuario");
			TituloPregunta = parametros.getString("titulo");
			PreguntaCreado = parametros.getString("creado");
			PreguntaModificado = parametros.getString("modificado");
			id = parametros.getLong("id");
			
			if (idCategoria != null && idUsuario != null && TituloPregunta != null && PreguntaCreado != null && PreguntaModificado != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				Pregunta pregunta = new Pregunta();
				
				NombreIdCategoria = new Categoria();
				NombreIdCategoria.setId(idCategoria);
				
				NombreIdUsuario = new Usuario();
				NombreIdUsuario.setId(idUsuario);
				
				pregunta.setId(id);
				pregunta.setIdCategoria(NombreIdCategoria);
				pregunta.setIdUsuario(NombreIdUsuario);
				pregunta.setTitulo(TituloPregunta);
				pregunta.setCreado(PreguntaCreado);
				pregunta.setModificado(PreguntaModificado);

				estado = PreguntaDao.actualizarPregunta(pregunta);
				
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
		String idPregunta = null;
		Boolean estado = null;
		Long Id = null;
		String mensaje = null;
		JSONObject datoJSON = null;
		
		try {
			forma = this.getQuery();
			idPregunta = forma.getValues("id");
			System.out.println("Voy a borrar pregunta con id " + idPregunta);
			if (idPregunta != null) {
				Id = Long.parseLong(idPregunta);
				Pregunta pregunta = new Pregunta();
				pregunta.setId(Id);
				estado = PreguntaDao.borrarPregunta(pregunta);
				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio ID pregunta";
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
