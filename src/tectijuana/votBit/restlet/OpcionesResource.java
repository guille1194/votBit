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

import tectijuana.votBit.hibernate.Opciones;
import tectijuana.votBit.hibernate.Pregunta;
import tectijuana.votBit.hibernate.dao.OpcionesDao;

public class OpcionesResource extends ServerResource {

	public OpcionesResource() {
		
	}
	
	@Override
	protected Representation get() throws ResourceException {
		// TODO Auto-generated method stub
		Representation respuesta = null;
		JSONObject datoJSON = new JSONObject();
		OpcionesDao opcionesDao = null;
		List<Opciones> parameters = new ArrayList<Opciones>();
		Series<Parameter> headers = (Series<Parameter>) getRequest().getAttributes().get("org.restlet.http.headers");
		String valores = headers.getValues("id");
		
		if(valores == null) {
			opcionesDao = new OpcionesDao();
			datoJSON.put("Data", opcionesDao.obtenerOpciones());
			datoJSON.put("mensaje", "servicio funcionando");
		}
		else {
			String [] str = valores.split(", ");
			opcionesDao = new OpcionesDao();
			for(String s : str) {
				parameters.add(opcionesDao.obtenerOpciones(Long.parseLong(s)));
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
		Integer NombreNumOpciones = null;
		String NombreOpcion = null;
		//solo para crear uno nuevo
		Pregunta NombreIdPregunta = null;
		Opciones opciones = null;
		
		Long idPregunta = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			
			NombreNumOpciones = parametros.getInt("numOpciones");
			NombreOpcion = parametros.getString("opcion");
			//pregunta
			idPregunta = parametros.getLong("idPregunta");
			
			if (NombreNumOpciones != null && idPregunta != null && NombreOpcion != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				NombreIdPregunta = new Pregunta();
				NombreIdPregunta.setId(idPregunta);
				
				opciones = new Opciones();
				opciones.setIdPregunta(NombreIdPregunta);
				opciones.setNumOpciones(NombreNumOpciones);
				opciones.setOpcion(NombreOpcion);
				
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
		Integer NombreNumOpciones = null;
		String NombreOpcion = null;
		Pregunta NombreIdPregunta = null;
		Opciones opciones = null;
		
		Long idPregunta = null;
		
		Long id = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			NombreNumOpciones = parametros.getInt("numOpciones");
			NombreOpcion = parametros.getString("opcion");
			idPregunta = parametros.getLong("idPregunta");
			
			id = parametros.getLong("id");
			if (NombreNumOpciones != null && idPregunta != null && NombreOpcion != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				NombreIdPregunta = new Pregunta();
				NombreIdPregunta.setId(idPregunta);
				
				opciones = new Opciones();
				opciones.setId(id);
				opciones.setIdPregunta(NombreIdPregunta);
				opciones.setNumOpciones(NombreNumOpciones);
				opciones.setOpcion(NombreOpcion);

				estado = OpcionesDao.actualizarOpciones(opciones);
				
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
		String idOpciones = null;
		Boolean estado = null;
		Long Id = null;
		String mensaje = null;
		JSONObject datoJSON = null;
		
		try {
			forma = this.getQuery();
			idOpciones = forma.getValues("id");
			System.out.println("Voy a borrar opciones con id " + idOpciones);
			if (idOpciones != null) {
				Id = Long.parseLong(idOpciones);
				Opciones opciones = new Opciones();
				opciones.setId(Id);
				estado = OpcionesDao.borrarOpciones(opciones);
				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio ID Opciones";
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
