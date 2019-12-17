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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tags")
public class Tag {
	
	@Id
	@Column(name="idtag")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtag;

	@Column(name="nombre")
	private String nombre;
	
	@Column(name="nombre_opt")
	private String nombre_opt;
	
	@ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinTable(name="art_tags", joinColumns=@JoinColumn(name="idtag"), inverseJoinColumns=@JoinColumn(name="idarticulo"))
	private List<Articulo> articulos;

	public Tag() {}
	
	public Tag(String nombre, String nombre_opt) {
		this.nombre = nombre;
		this.nombre_opt = nombre_opt;
	}

	public int getIdtag() {
		return idtag;
	}

	public void setIdtag(int idtag) {
		this.idtag = idtag;
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
	}

	@Override
	public String toString() {
		return "Tag [idtag=" + idtag + ", nombre=" + nombre + ", nombre_opt=" + nombre_opt + "]";
	}	
}