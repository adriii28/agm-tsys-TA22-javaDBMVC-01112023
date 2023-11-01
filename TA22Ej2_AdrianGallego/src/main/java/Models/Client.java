package Models;

public class Client {
	private int id;
	private String nombre;
	private String apellidos;
	private String direccion;
	private int dni;
	private String fecha;
	
	
	public Client(int id, String nombre, String apellidos, String direccion, int dni, String fecha) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.dni = dni;
		this.fecha = fecha;
	}
	
	public Client() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "ID: " + id + " | Nombre: " + nombre + " | Apellidos: " + apellidos + " | Direccion: " + direccion
				+ " | DNI: " + dni + " | Fecha: " + fecha;
	}
	
	
	
	
	

}
