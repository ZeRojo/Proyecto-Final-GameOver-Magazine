package gameover.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameover.dao.iface.IntTipoDAO;
import gameover.models.Tipo;
import gameover.service.iface.IntTipoService;

@Service("tipoService")
public class TipoService implements IntTipoService {

	@Autowired
	private IntTipoDAO tipoDAO;
	
	@Override
	@Transactional
	public Tipo getTipo(int idtipo) {
		return tipoDAO.getTipo(idtipo);
	}
	
	@Override
	@Transactional
	public Tipo getTipoByNombre(String nombre_opt) {
		return tipoDAO.getTipoByNombre(nombre_opt);
	}

	@Override
	@Transactional
	public List<Tipo> getTipos() {
		return tipoDAO.getTipos();
	}

	@Override
	@Transactional
	public void saveTipo(Tipo tipo) {
		tipoDAO.saveTipo(tipo);
	}

	@Override
	@Transactional
	public void deleteTipo(Tipo tipo) {
		tipoDAO.deleteTipo(tipo);
	}
}