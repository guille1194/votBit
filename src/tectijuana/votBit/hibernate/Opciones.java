package tectijuana.votBit.hibernate;

import org.json.JSONObject;

public class Opciones {
	private long id;
	private Pregunta idPregunta;
	private String opcion;
	private Integer numOpciones;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public Integer getNumOpciones() {
		return numOpciones;
	}

	public void setNumOpciones(Integer numOpciones) {
		this.numOpciones = numOpciones;
	}

	public Pregunta getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Pregunta idPregunta) {
		this.idPregunta = idPregunta;
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("id", this.id);
		json.put("id pregunta", this.idPregunta);
		json.put("opcion", this.opcion);
		json.put("num Opciones", this.numOpciones);

		return json;
	}
}
