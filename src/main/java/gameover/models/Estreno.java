package gameover.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import gameover.resources.ValidacionFecha;

@Entity
@Table(name="estrenos")
public class Estreno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idestreno")
	private int idestreno;
	
	@NotBlank(message="Este campo es obligatorio.")
	@Size(min=3,message="El tamaño mínimo es de 3 caracteres.")
	@Column(name="nombre")
	private String nombre;
	
	@NotNull(message="Este campo es obligatorio.")
	@Min(value=1,message="No es una temporada válida.")
	@Max(value=100,message="No es una temporada válida.")
	@Column(name="temporada")
	private int temporada;
	
	@NotBlank(message="Este campo es obligatorio.")
	@Size(min=3,message="El tamaño mínimo es de 3 caracteres.")
	@Column(name="plataforma")
	private String plataforma;
	
	@Column(name="thumbnail")
	private String thumbnail="default.png";
	
	@NotNull(message="Este campo es obligatorio.")
	@ValidacionFecha
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