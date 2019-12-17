package gameover.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipos")
public class Tipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idtipo")
	private int idtipo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="nombre_opt")
	private String nombre_opt;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="tipo",cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<Articulo> articulos;
	
	public Tipo() {}

	public Tipo(String nombre, String nombre_opt) {
		this.nombre = nombre;
		this.nombre_opt = nombre_opt;
	}

	public int getIdtipo() {
		return idtipo;
	}

	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre_opt() {
		return nombre_opt;
	}

	public void setNombre_opt(String nombre_opt) {
		this.nombre_opt = nombre_opt;
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
		articulo.setTipo(this);
	}

	@Override
	public String toString() {
		return "Tipo [idtipo=" + idtipo + ", nombre=" + nombre + ", nombre_opt=" + nombre_opt + "]";
	}
}