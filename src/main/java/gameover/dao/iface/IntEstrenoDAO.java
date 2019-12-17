package gameover.dao.iface;

import java.util.List;

import gameover.models.Estreno;

public interface IntEstrenoDAO {

	Estreno getEstreno(int idestreno);
	
	List<Estreno> getEstrenos();
	
	List<Estreno> getNextXEstrenos(int nextX);
	
	List<Estreno> getLastEstrenosRango(int elementoInicial, int numeroElementos);
	
	int getTotalEstrenos();
	
	void saveEstreno(Estreno estreno);
	
	void deleteEstreno(Estreno estreno);
}