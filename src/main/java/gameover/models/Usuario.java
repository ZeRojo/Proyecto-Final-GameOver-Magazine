package gameover.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idusuario")
	private int idusuario;

	@Column(name="nombre")
	private String nombre;
	
	@Column(name="password")
	private String password;
	
	@Column(name="activo")
	private boolean activo;
	
	@ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinTable(name="usuarios_rango", joinColumns=@JoinColumn(name="idusuario"), inverseJoinColumns=@JoinColumn(name="idrango"))
	private Set<Rango> rangos;
	
	@OneToMany(mappedBy="usuario",cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.LAZY)
	private List<Articulo> articulos;
	
	@OneToOne(mappedBy="usuario",cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.LAZY)
	private UsuarioDetalles usuarioDetalles;
	
	public Usuario() {}
	
	public Usuario(String nombre, String password, boolean activo) {
		this.nombre=nombre;
		this.password=password;
		this.activo=activo;
	}
	
	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Set<Rango> getRangos() {
		return rangos;
	}

	public void setRangos(Set<Rango> rangos) {
		this.rangos = rangos;
	}

	public void addRango(Rango rango) {
		if (rangos==null) {
			rangos=new HashSet<>();
		}
		rangos.add(rango);
	}
	
	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	public void addArticulo(Articulo articulo) {
		if (articulos==null) {
			articulos=new ArrayList<Articulo>();
		}
		articulos.add(articulo);
		articulo.setUsuario(this);
	}

	public UsuarioDetalles getUsuarioDetalles() {
		return usuarioDetalles;
	}
	
	public void setUsuarioDetalles(UsuarioDetalles usuarioDetalles) {
		this.usuarioDetalles=usuarioDetalles;
	}

	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", nombre=" + nombre + ", password=" + password + ", activo="
				+ activo + "]";
	}
}