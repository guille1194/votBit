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

import tectijuana.votBit.hibernate.Roles;
import tectijuana.votBit.hibernate.dao.RolesDao;

public class RolesResource extends ServerResource {
	
	public RolesResource() {
		
	}
	
	@Override
	protected Representation get() throws ResourceException {
		Representation respuesta = null;
		JSONObject datoJSON = new JSONObject();
		RolesDao rolesDao = null;
		List<Roles> parameters = new ArrayList<Roles>();
		Series<Parameter> headers = (Series<Parameter>) getRequest().getAttributes().get("org.restlet.http.headers");
		String valores = headers.getValues("id");
		
		if(valores == null) {
			rolesDao = new RolesDao();
			datoJSON.put("Data", rolesDao.obtenerRoles());
			datoJSON.put("mensaje", "servicio funcionando");
		}
		else {
			String [] str = valores.split(", ");
			rolesDao = new RolesDao();
			for(String s : str) {
				parameters.add(rolesDao.obtenerRoles(Long.parseLong(s)));
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
		String NombreRoles = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			NombreRoles = parametros.getString("estatus");
			
			if (NombreRoles != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				Roles roles = new Roles();
				
				roles.setEstatus(NombreRoles);

				estado = RolesDao.guardarRoles(roles);
				
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
		String NombreRoles = null;
		Long id = null;
		
		try {
			String text = entity.getText();
			parametros = new JSONObject(text);
			NombreRoles = parametros.getString("estatus");
			id = parametros.getLong("id");
			if (NombreRoles != null) {
				System.out.println("Se tratara de agregar nuevo registro.");
				
				Roles roles = new Roles();
				roles.setId(id);
				roles.setEstatus(NombreRoles);

				
				estado = RolesDao.actualizarRoles(roles);
				
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
		String idRoles = null;
		Boolean estado = null;
		Long Id = null;
		String mensaje = null;
		JSONObject datoJSON = null;
		
		try {
			forma = this.getQuery();
			idRoles = forma.getValues("id");
			System.out.println("Voy a borrar roles con id " + idRoles);
			if (idRoles != null) {
				Id = Long.parseLong(idRoles);
				Roles roles = new Roles();
				roles.setId(Id);
				estado = RolesDao.borrarRoles(roles);
				if (estado) {
					mensaje = "Registro borrado";
				} else {
					mensaje = "Registro no borrado";
				}
			} else {
				estado = false;
				mensaje = "No se envio ID roles";
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