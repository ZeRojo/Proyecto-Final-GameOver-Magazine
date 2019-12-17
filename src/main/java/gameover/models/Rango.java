package gameover.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="rangos")
public class Rango {

	@Id
	@Column(name="idrango")
	private int idrango;
	
	@Column(name="rango")
	private String rango; 
	
	@ManyToMany(cascade= {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinTable(name="usuarios_rango", joinColumns=@JoinColumn(name="idrango"), inverseJoinColumns=@JoinColumn(name="idusuario"))
	private List<Usuario> usuarios;

	public int getIdrango() {
		return idrango;
	}

	public void setIdrango(int idrango) {
		this.idrango = idrango;
	}
	
	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void addUsuario(Usuario usuario) {
		if (usuarios == null) {
			usuarios=new ArrayList<Usuario>();
		}
		usuarios.add(usuario);
	}

	@Override
	public String toString() {
		return rango;
	}
}