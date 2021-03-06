package tectijuana.votBit.hibernate;

import org.json.JSONObject;

public class Pregunta {
	private long id;
	private Usuario idUsuario;
	private Categoria idCategoria;
	private String titulo;
	private String creado;
	private String modificado;
	
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

	public Categoria getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("id", this.id);
		json.put("id usuario", this.idUsuario);
		json.put("id categoria", this.idCategoria);
		json.put("titulo", this.titulo);
		json.put("creado", this.creado);
		json.put("modificado", this.modificado);
		return json;
	}
	
}
