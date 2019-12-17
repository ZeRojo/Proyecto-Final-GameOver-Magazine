package gameover.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="estrenos")
public class Estreno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idestreno")
	private int idestreno;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="temporada")
	private int temporada;
	
	@Column(name="plataforma")
	private String plataforma;
	
	@Column(name="thumbnail")
	private String thumbnail="default.png";
	
	@Column(name="fecha")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fecha;
	
	public Estreno() {}

	public Estreno(String nombre, int temporada, String plataforma, String thumbnail, Date fecha) {
		this.nombre = nombre;
		this.temporada = temporada;
		this.plataforma = plataforma;
		this.thumbnail = thumbnail;
		this.fecha = fecha;
	}

	public int getIdestreno() {
		return idestreno;
	}

	public void setIdestreno(int idestreno) {
		this.idestreno = idestreno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Estreno [idestreno=" + idestreno + ", nombre=" + nombre + ", plataforma=" + plataforma + ", thumbnail="
				+ thumbnail + ", fecha=" + fecha + "]";
	}
}