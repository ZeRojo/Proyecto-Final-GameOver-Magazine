package gameover.service.iface;

import java.util.List;

import gameover.models.Tipo;

public interface IntTipoService {

	Tipo getTipo(int idtipo);
	
	Tipo getTipoByNombre(String nombre_opt);
	
	List<Tipo> getTipos();
	
	void saveTipo(Tipo tipo);
	
	void deleteTipo(Tipo tipo);
}