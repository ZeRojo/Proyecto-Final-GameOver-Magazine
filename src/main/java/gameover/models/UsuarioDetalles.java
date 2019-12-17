package gameover.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios_det")
public class UsuarioDetalles {

	@Id
	@Column(name="idusuario_det")
	private int idusuario;

	@Column(name="nombre")
	private String nombre;
	
	@Column(name="avatar")
	private String avatar="default.png";
	
	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.LAZY)
	@MapsId
	@JoinColumn(name="idusuario_det")
	private Usuario usuario;
	
	public UsuarioDetalles() {}
	
	public UsuarioDetalles(String nombre,Usuario usuario) {
		this.nombre=nombre;
		this.usuario=usuario;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "UsuarioDetalles [idusuario=" + idusuario + ", nombre=" + nombre + ", avatar=" + avatar + "]";
	}
}