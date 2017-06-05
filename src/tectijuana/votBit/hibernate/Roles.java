package tectijuana.votBit.hibernate;

import org.json.JSONObject;

public class Roles {

	private long id;
	private String estatus;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("id", this.id);
		json.put("estatus", this.estatus);
		return json;
	}
}
