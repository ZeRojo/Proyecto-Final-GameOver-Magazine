package gameover.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameover.dao.iface.IntEstrenoDAO;
import gameover.models.Estreno;
import gameover.service.iface.IntEstrenoService;

@Service("estrenoService")
public class EstrenoService implements IntEstrenoService {

	@Autowired
	private IntEstrenoDAO estrenoDAO;
	
	@Override
	@Transactional
	public Estreno getEstreno(int idestreno) {
		return estrenoDAO.getEstreno(idestreno);
	}

	@Override
	@Transactional
	public List<Estreno> getEstrenos() {
		return estrenoDAO.getEstrenos();
	}

	@Override
	@Transactional
	public List<Estreno> getNextXEstrenos(int nextX) {
		return estrenoDAO.getNextXEstrenos(nextX);
	}
	
	@Override
	@Transactional
	public List<Estreno> getLastEstrenosRango(int elementoInicial, int numeroElementos) {
		return estrenoDAO.getLastEstrenosRango(elementoInicial, numeroElementos);
	}
	
	@Override
	@Transactional
	public int getTotalEstrenos() {
		return estrenoDAO.getTotalEstrenos();
	}

	@Override
	@Transactional
	public void saveEstreno(Estreno estreno) {
		estrenoDAO.saveEstreno(estreno);
	}

	@Override
	@Transactional
	public void deleteEstreno(Estreno estreno) {
		estrenoDAO.deleteEstreno(estreno);
	}
}