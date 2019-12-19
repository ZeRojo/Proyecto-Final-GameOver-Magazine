package gameover.resources;

import org.springframework.stereotype.Service;

@Service
public class ValidationErrors {
	
	private boolean errors=false;
	private String nombreError;
	private String passwordError;
	private String nicknameError;
	private String rangoError;
	private String nombreArticuloError;
	private String imagenError;
	private String nombreCategoriaError;
	private String selectCategoriaError;
	private String nombreTipoError;
	private String selectTipoError;
	private String nombreVideoError;
	private String linkVideoError;
	private String tipoVideoError;
	private String nombreEstrenoError;
	private String temporadaEstrenoError;
	private String plataformaEstrenoError;
	private String fechaEstrenoError;
	
	ValidationErrors() {}
	
	public void reset() {
		this.errors=false;
		if (nombreError!=null) nombreError=null;
		if (passwordError!=null) passwordError=null;
		if (nicknameError!=null) nicknameError=null;
		if (rangoError!=null) rangoError=null;
		if (nombreArticuloError!=null) nombreArticuloError=null;
		if (imagenError!=null) imagenError=null;
		if (nombreCategoriaError!=null) nombreCategoriaError=null;
		if (nombreTipoError!=null) nombreTipoError=null;
		if (selectCategoriaError!=null) selectCategoriaError=null;
		if (selectTipoError!=null) selectTipoError=null;
		if (nombreVideoError!=null) nombreVideoError=null;
		if (linkVideoError!=null) linkVideoError=null;
		if (tipoVideoError!=null) tipoVideoError=null;
		if (nombreEstrenoError!=null) nombreEstrenoError=null;
		if (temporadaEstrenoError!=null) temporadaEstrenoError=null;
		if (plataformaEstrenoError!=null) plataformaEstrenoError=null;
		if (fechaEstrenoError!=null) fechaEstrenoError=null;
	}
	
	public boolean hasErrors() {
		return errors;
	}
	public void setErrors(boolean errors) {
		this.errors = errors;
	}
	public String getNombreError() {
		return nombreError;
	}
	public void setNombreError(String nombreError) {
		this.nombreError = nombreError;
	}
	public String getPasswordError() {
		return passwordError;
	}
	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}
	public String getNicknameError() {
		return nicknameError;
	}
	public void setNicknameError(String nicknameError) {
		this.nicknameError = nicknameError;
	}
	public String getRangoError() {
		return rangoError;
	}
	public void setRangoError(String rangoError) {
		this.rangoError = rangoError;
	}
	
	public String getNombreArticuloError() {
		return nombreArticuloError;
	}

	public void setNombreArticuloError(String nombreArticuloError) {
		this.nombreArticuloError = nombreArticuloError;
	}

	public String getImagenError() {
		return imagenError;
	}

	public void setImagenError(String imagenError) {
		this.imagenError = imagenError;
	}

	public String getNombreCategoriaError() {
		return nombreCategoriaError;
	}

	public void setNombreCategoriaError(String nombreCategoriaError) {
		this.nombreCategoriaError = nombreCategoriaError;
	}

	public String getNombreTipoError() {
		return nombreTipoError;
	}

	public void setNombreTipoError(String nombreTipoError) {
		this.nombreTipoError = nombreTipoError;
	}

	public String getSelectCategoriaError() {
		return selectCategoriaError;
	}

	public void setSelectCategoriaError(String selectCategoriaError) {
		this.selectCategoriaError = selectCategoriaError;
	}

	public String getSelectTipoError() {
		return selectTipoError;
	}

	public void setSelectTipoError(String selectTipoError) {
		this.selectTipoError = selectTipoError;
	}

	public String getNombreVideoError() {
		return nombreVideoError;
	}

	public void setNombreVideoError(String nombreVideoError) {
		this.nombreVideoError = nombreVideoError;
	}

	public String getLinkVideoError() {
		return linkVideoError;
	}

	public void setLinkVideoError(String linkVideoError) {
		this.linkVideoError = linkVideoError;
	}

	public String getTipoVideoError() {
		return tipoVideoError;
	}

	public void setTipoVideoError(String tipoVideoError) {
		this.tipoVideoError = tipoVideoError;
	}

	public String getNombreEstrenoError() {
		return nombreEstrenoError;
	}

	public void setNombreEstrenoError(String nombreEstrenoError) {
		this.nombreEstrenoError = nombreEstrenoError;
	}

	public String getTemporadaEstrenoError() {
		return temporadaEstrenoError;
	}

	public void setTemporadaEstrenoError(String temporadaEstrenoError) {
		this.temporadaEstrenoError = temporadaEstrenoError;
	}

	public String getPlataformaEstrenoError() {
		return plataformaEstrenoError;
	}

	public void setPlataformaEstrenoError(String plataformaEstrenoError) {
		this.plataformaEstrenoError = plataformaEstrenoError;
	}

	public String getFechaEstrenoError() {
		return fechaEstrenoError;
	}

	public void setFechaEstrenoError(String fechaEstrenoError) {
		this.fechaEstrenoError = fechaEstrenoError;
	}

	@Override
	public String toString() {
		return "ValidationErrors [errors=" + errors + ", nombreError=" + nombreError + ", passwordError="
				+ passwordError + ", nicknameError=" + nicknameError + ", rangoError=" + rangoError
				+ ", nombreArticuloError=" + nombreArticuloError + ", imagenError=" + imagenError
				+ ", nombreCategoriaError=" + nombreCategoriaError + ", selectCategoriaError=" + selectCategoriaError
				+ ", nombreTipoError=" + nombreTipoError + ", selectTipoError=" + selectTipoError
				+ ", nombreVideoError=" + nombreVideoError + ", linkVideoError=" + linkVideoError + ", tipoVideoError="
				+ tipoVideoError + ", nombreEstrenoError=" + nombreEstrenoError + ", temporadaEstrenoError="
				+ temporadaEstrenoError + ", plataformaEstrenoError=" + plataformaEstrenoError + ", fechaEstrenoError="
				+ fechaEstrenoError + "]";
	}
}
