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
		// TODO Auto-generated method stub
		return null;
	}
}
