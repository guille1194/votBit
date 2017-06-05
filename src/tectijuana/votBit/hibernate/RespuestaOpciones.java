package tectijuana.votBit.hibernate;

import org.json.JSONObject;

public class RespuestaOpciones {

	private long id;
	private Usuario idUsuario;
	private Pregunta idPregunta;
	private String creado;
	private String modificado;
	private String respuesta;

	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Pregunta getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Pregunta idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getCreado() {
		return creado;
	}

	public void setCreado(String creado) {
		this.creado = creado;
	}

	public String getModificado() {
		return modificado;
	}

	public void setModificado(String modificado) {
		this.modificado = modificado;
	}
	
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("id", this.id);
		json.put("id usuario", this.idUsuario);
		json.put("id categoria", this.idPregunta);
		json.put("titulo", this.respuesta);
		json.put("creado", this.creado);
		json.put("modificado", this.modificado);
		return json;
	}
	
}
