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
@Table(name="categorias")
public class Categoria {

	@Id
	@Column(name="idcategoria")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcategoria;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="nombre_opt")
	private String nombre_opt;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="categoria",cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<Articulo> articulos;

	public Categoria() {}
	
	public Categoria(String nombre, String nombre_opt) {
		this.nombre = nombre;
		this.nombre_opt = nombre_opt;
	}

	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
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
		if (articulos == null) {
			articulos=new ArrayList<Articulo>();
		}
		articulos.add(articulo);
		articulo.setCategoria(this);
	}

	@Override
	public String toString() {
		return "Categoria [idcategoria=" + idcategoria + ", nombre=" + nombre + ", nombre_opt=" + nombre_opt + "]";
	}
}