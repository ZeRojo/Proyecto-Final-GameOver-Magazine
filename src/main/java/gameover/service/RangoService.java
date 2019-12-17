package gameover.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameover.dao.iface.IntRangoDAO;
import gameover.models.Rango;
import gameover.service.iface.IntRangoService;


@Service("rangoService")
public class RangoService implements IntRangoService {
	
	@Autowired
	private IntRangoDAO rangoDAO;
	
	@Override
	@Transactional
	public List<Rango> getRangos() {
		return rangoDAO.getRangos();
	}

	@Override
	@Transactional
	public Map<String,String> getRangosMap() {
		return rangoDAO.getRangosMap();
	}
	
	@Override
	@Transactional
	public Rango getRangoByName(String rango) {
		return rangoDAO.getRangoByName(rango);
	}
}