package tectijuana.votBit.hibernate;

import org.json.JSONObject;

public class Categoria {
	private long id;
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}
}
