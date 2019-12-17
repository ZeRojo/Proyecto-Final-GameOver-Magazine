package gameover.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="videos")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idvideo")
	private int idvideo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="link")
	private String link;
	
	@Column(name="thumbnail")
	private String thumbnail="default.png";

	public Video() {}
	
	public Video(String nombre, String tipo, String link, String thumbnail) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.link = link;
		this.thumbnail = thumbnail;
	}

	public int getIdvideo() {
		return idvideo;
	}

	public void setIdvideo(int idvideo) {
		this.idvideo = idvideo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "Video [idvideo=" + idvideo + ", nombre=" + nombre + ", tipo=" + tipo + ", link=" + link + ", thumbnail="
				+ thumbnail + "]";
	}
}