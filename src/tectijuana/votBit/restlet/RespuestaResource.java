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

import tectijuana.votBit.hibernate.Pregunta;
import tectijuana.votBit.hibernate.Respuesta;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.dao.RespuestaDao;

public class RespuestaResource extends ServerResource {
	
	public RespuestaResource() {
		
	}
	
	@Override
	protected Representation get() throws ResourceException {
		// TODO Auto-generated method stub
		Representation respuesta = null;
		JSONObject datoJSON = new JSONObject();
		RespuestaDao respuestaDao = null;
		List<Respuesta> parameters = new ArrayList<Respuesta>();
		Series<Parameter> headers = (Series<Parameter>) getRequest().getAttributes().get("org.restlet.http.headers");
		String valores = headers.getValues("id");
		
		if(valores == null) {
			respuestaDao = new RespuestaDao();
			datoJSON.put("Data", respuestaDao.obtenerRespuesta());
			datoJSON.put("mensaje", "servicio funcionando");
		}
		else {
			String [] str = valores.split(", ");
			respuestaDao = new RespuestaDao();
			for(String s : str) {
				parameters.add(respuestaDao.obtenerRespuesta(Long.parseLong(s)));
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
		String NombreRespuesta = null;
		Pregunta NombreIdPregunta = null;
		Usuario NombreIdUsuario = null;
		String PreguntaCreado = null;
		String PreguntaModificado = null;
		
		Long idPregunta = null;
		Long idUsuario = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			NombreRespuesta = parametros.getString("NombreRespuesta");
			idPregunta = parametros.getLong("NombreIdPregunta");
			idUsuario = parametros.getLong("NombreIdUsuario");
			PreguntaCreado = parametros.getString("PreguntaCreado");
			PreguntaModificado = parametros.getString("PreguntaModificado");
			
			if (NombreRespuesta != null && idPregunta != null && idUsuario != null && PreguntaCreado != null && PreguntaModificado != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				Respuesta respuestas = new Respuesta();
				
				NombreIdPregunta = new Pregunta();
				NombreIdPregunta.setId(idPregunta);
				
				NombreIdUsuario = new Usuario();
				NombreIdUsuario.setId(idUsuario);
				
				respuestas.setCreado(PreguntaCreado);
				respuestas.setIdPregunta(NombreIdPregunta);
				respuestas.setIdUsuario(NombreIdUsuario);
				respuestas.setModificado(PreguntaModificado);
				respuestas.setRespuesta(NombreRespuesta);
				
				estado = RespuestaDao.guardarRespuesta(respuestas);
				
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
		String NombreRespuesta = null;
		Pregunta NombreIdPregunta = null;
		Usuario NombreIdUsuario = null;
		String PreguntaCreado = null;
		String PreguntaModificado = null;
		
		Long idPregunta = null;
		Long idUsuario = null;
		Long id = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			NombreRespuesta = parametros.getString("NombreRespuesta");
			idPregunta = parametros.getLong("NombreIdPregunta");
			idUsuario = parametros.getLong("NombreIdUsuario");
			PreguntaCreado = parametros.getString("PreguntaCreado");
			PreguntaModificado = parametros.getString("PreguntaModificado");
			id = parametros.getLong("id");
			if (NombreRespuesta != null && idPregunta != null && idUsuario != null && PreguntaCreado != null && PreguntaModificado != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				Respuesta respuestas = new Respuesta();
				
				NombreIdPregunta = new Pregunta();
				NombreIdPregunta.setId(idPregunta);
				
				NombreIdUsuario = new Usuario();
				NombreIdUsuario.setId(idUsuario);
				
				respuestas.setId(id);
				respuestas.setCreado(PreguntaCreado);
				respuestas.setIdPregunta(NombreIdPregunta);
				respuestas.setIdUsuario(NombreIdUsuario);
				respuestas.setModificado(PreguntaModificado);
				respuestas.setRespuesta(NombreRespuesta);

				estado = RespuestaDao.guardarRespuesta(respuestas);
				
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
		String idRespuesta = null;
		Boolean estado = null;
		Long Id = null;
		String mensaje = null;
		JSONObject datoJSON = null;
		
		try {
			forma = this.getQuery();
			idRespuesta = forma.getValues("id");
			System.out.println("Voy a borrar respuesta con id " + idRespuesta);
			if (idRespuesta != null) {
				Id = Long.parseLong(idRespuesta);
				Respuesta respuestas = new Respuesta();
				respuestas.setId(Id);
				estado = RespuestaDao.borrarRespuesta(respuestas);
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
