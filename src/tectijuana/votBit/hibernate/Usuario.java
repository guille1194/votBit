package tectijuana.votBit.hibernate;

public class Usuario {

	private long id;
	private String perfil;
	private String nombre;
	private String Edad;
	private String correo;
	private String contrasena;
	private Roles tipoRoles;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Roles getTipoRoles() {
		return tipoRoles;
	}

	public void setTipoRoles(Roles tipoRoles) {
		this.tipoRoles = tipoRoles;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getEdad() {
		return Edad;
	}

	public void setEdad(String edad) {
		Edad = edad;
	}
	
	
}