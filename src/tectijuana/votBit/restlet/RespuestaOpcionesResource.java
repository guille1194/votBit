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
import tectijuana.votBit.hibernate.RespuestaOpciones;
import tectijuana.votBit.hibernate.Usuario;
import tectijuana.votBit.hibernate.dao.RespuestaOpcionesDao;

public class RespuestaOpcionesResource extends ServerResource {
	
	public RespuestaOpcionesResource() {
		
	}
	
	@Override
	protected Representation get() throws ResourceException {
		// TODO Auto-generated method stub
		Representation respuesta = null;
		JSONObject datoJSON = new JSONObject();
		RespuestaOpcionesDao respuestaOpcionesDao = null;
		List<RespuestaOpciones> parameters = new ArrayList<RespuestaOpciones>();
		Series<Parameter> headers = (Series<Parameter>) getRequest().getAttributes().get("org.restlet.http.headers");
		String valores = headers.getValues("id");
		
		if(valores == null) {
			respuestaOpcionesDao = new RespuestaOpcionesDao();
			datoJSON.put("Data", respuestaOpcionesDao.obtenerRespuestaOpciones());
			datoJSON.put("mensaje", "servicio funcionando");
		}
		else {
			String [] str = valores.split(", ");
			respuestaOpcionesDao = new RespuestaOpcionesDao();
			for(String s : str) {
				parameters.add(respuestaOpcionesDao.obtenerRespuestaOpciones(Long.parseLong(s)));
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
		String NombreRespuestaOpciones = null;
		Pregunta NombreIdPregunta = null;
		Usuario NombreIdUsuario = null;
		String PreguntaCreado = null;
		String PreguntaModificado = null;
		
		Long idPregunta = null;
		Long idUsuario = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			NombreRespuestaOpciones = parametros.getString("respuesta");
			idPregunta = parametros.getLong("idPregunta");
			idUsuario = parametros.getLong("idUsuario");
			PreguntaCreado = parametros.getString("creado");
			PreguntaModificado = parametros.getString("modificado");
			
			if (NombreRespuestaOpciones != null && idPregunta != null && idUsuario != null && PreguntaCreado != null && PreguntaModificado != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
				
				NombreIdPregunta = new Pregunta();
				NombreIdPregunta.setId(idPregunta);
				
				NombreIdUsuario = new Usuario();
				NombreIdUsuario.setId(idUsuario);
				
				respuestaOpciones.setCreado(PreguntaCreado);
				respuestaOpciones.setIdPregunta(NombreIdPregunta);
				respuestaOpciones.setIdUsuario(NombreIdUsuario);
				respuestaOpciones.setModificado(PreguntaModificado);
				respuestaOpciones.setRespuesta(NombreRespuestaOpciones);
				estado = RespuestaOpcionesDao.guardarRespuestaOpciones(respuestaOpciones);
				
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
		String NombreRespuestaOpciones = null;
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
			NombreRespuestaOpciones = parametros.getString("respuesta");
			idPregunta = parametros.getLong("idPregunta");
			idUsuario = parametros.getLong("idUsuario");
			PreguntaCreado = parametros.getString("creado");
			PreguntaModificado = parametros.getString("modificado");
			id = parametros.getLong("id");
			if (NombreRespuestaOpciones != null && idPregunta != null && idUsuario != null && PreguntaCreado != null && PreguntaModificado != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
				
				NombreIdPregunta = new Pregunta();
				NombreIdPregunta.setId(idPregunta);
				
				NombreIdUsuario = new Usuario();
				NombreIdUsuario.setId(idUsuario);
				
				respuestaOpciones.setId(id);
				respuestaOpciones.setCreado(PreguntaCreado);
				respuestaOpciones.setIdPregunta(NombreIdPregunta);
				respuestaOpciones.setIdUsuario(NombreIdUsuario);
				respuestaOpciones.setModificado(PreguntaModificado);
				respuestaOpciones.setRespuesta(NombreRespuestaOpciones);

				estado = RespuestaOpcionesDao.guardarRespuestaOpciones(respuestaOpciones);
				
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
		String idRespuestaOpciones = null;
		Boolean estado = null;
		Long Id = null;
		String mensaje = null;
		JSONObject datoJSON = null;
		
		try {
			forma = this.getQuery();
			idRespuestaOpciones = forma.getValues("id");
			System.out.println("Voy a borrar opcion respuesta con id " + idRespuestaOpciones);
			if (idRespuestaOpciones != null) {
				Id = Long.parseLong(idRespuestaOpciones);
				RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
				respuestaOpciones.setId(Id);
				estado = RespuestaOpcionesDao.borrarRespuestaOpciones(respuestaOpciones);
				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio ID opcion respuesta";
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
