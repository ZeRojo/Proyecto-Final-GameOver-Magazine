package gameover.dao.iface;

import java.util.List;
import java.util.Map;

import gameover.models.Rango;

public interface IntRangoDAO {

	List<Rango> getRangos();
	
	Map<String,String> getRangosMap();
	
	Rango getRangoByName(String rango);
}