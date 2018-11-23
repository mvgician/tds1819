package application.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	// ATRIBUTOS
	private int codigo; // Necesario para rescatar un usuario del servidor de persistencia
	private String login;
	private String password;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNac;
	private String email;
	private RolPremium premium;
	private List<ListaVideos> listas;
	private List<ListaVideos> listaRecientes;
	
	
	// CONSTRUCTORES
	public Usuario(String login, String password, String nombre, String apellidos, LocalDate fechaNac, String email) {
		codigo = 0;
		this.login = login;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
		this.email = email;
		this.premium = null;
		listas = new LinkedList<ListaVideos>();
		listaRecientes = new LinkedList<ListaVideos>();
	}
	
	public Usuario(String login, String password) {
		this(login, password, "", "", null, "");
	}
	
	
	// M�TODOS DE CONSULTA Y MODIFICACION
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RolPremium getPremium() {
		return premium;
	}

	public void setPremium(RolPremium premium) {
		this.premium = premium;
	}

	public String getLogin() {
		return login;
	}
	
	
	
	public List<ListaVideos> getListas() {
		return Collections.unmodifiableList(listas);
	}

	public List<ListaVideos> getListaRecientes() {
		return Collections.unmodifiableList(listaRecientes);
	}

	// FUNCIONALIDAD
	public void obtenerPremium() {
		premium = new RolPremium();
	}
	
	public boolean addListaVideos(ListaVideos listaVideos) {
		return listas.add(listaVideos);
	}
	
	public boolean removeListaVideos(ListaVideos listaVideos) {
		return listas.remove(listaVideos);
	}
	
	public boolean addListaVideosReciente(ListaVideos listaVideos) {
		return listaRecientes.add(listaVideos);
	}
	
	public boolean removeListaVideosReciente(ListaVideos listaVideos) {
		return listaRecientes.remove(listaVideos);
	}
}
