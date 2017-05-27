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
		// TODO Auto-generated method stub
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
	protected Representation post(Representation entity) throws ResourceException {
		
		//Falta obtener el representation
		Representation respuesta = null;
		JSONObject datoJSON = new JSONObject();
		RolesDao rolesDao = null;
		Roles roles = new Roles();
		try {
			String dato = entity.getText();
			roles.setEstatus(dato);
			rolesDao = new RolesDao();
			rolesDao.guardarRoles(roles);
			datoJSON.put("Objeto Insertado", "hehe");
			respuesta = new JsonRepresentation(datoJSON);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return respuesta;
	}
	
	@Override
	protected Representation put(Representation entity) throws ResourceException {
		return super.delete();
	}
	
	@Override
	protected Representation delete() throws ResourceException {
		return super.delete();
	}
}