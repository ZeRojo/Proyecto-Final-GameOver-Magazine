package gameover.models;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="articulos")
public class Articulo {

	@Id
	@Column(name="idarticulo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idarticulo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="nombre_opt")
	private String nombre_opt;
	
	@Column(name="producto")
	private String producto;
	
	@Column(name="texto", columnDefinition = "LONGTEXT")
	private String texto;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="idcategoria")
	private Categoria categoria;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="idtipo")
	private Tipo tipo;
	
	@ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinTable(name="art_tags", joinColumns=@JoinColumn(name="idarticulo"), inverseJoinColumns=@JoinColumn(name="idtag"))
	private Set<Tag> tags;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="idusuario")
	private Usuario usuario;
	
	@Column(name="imagen")
	private String imagen;
	
	@Column(name="publicar")
	private boolean publicar;
	
	@Column(name="fecha")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fecha;
	
	public Articulo() {}

	public Articulo(String nombre, String nombre_opt, String producto, String texto, Categoria categoria, Tipo tipo,
			Set<Tag> tags, Usuario usuario, String imagen, boolean publicar, Date fecha) {
		super();
		this.nombre = nombre;
		this.nombre_opt = nombre_opt;
		this.producto = producto;
		this.texto = texto;
		this.categoria = categoria;
		this.tipo = tipo;
		this.tags = tags;
		this.usuario = usuario;
		this.imagen = imagen;
		this.publicar = publicar;
		this.fecha = fecha;
	}

	public int getIdarticulo() {
		return idarticulo;
	}

	public void setIdarticulo(int idarticulo) {
		this.idarticulo = idarticulo;
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

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void addTag(Tag tag) {
		if (tags==null) {
			tags=new HashSet<Tag>();
		}
		tags.add(tag);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public boolean isPublicar() {
		return publicar;
	}

	public void setPublicar(boolean publicar) {
		this.publicar = publicar;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Articulo [idarticulo=" + idarticulo + ", nombre=" + nombre + ", nombre_opt=" + nombre_opt
				+ ", producto=" + producto + ", texto=" + texto + ", imagen=" + imagen + ", publicar=" + publicar
				+ ", fecha=" + fecha + "]";
	}
}