package gameover.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gameover.dao.iface.IntVideoDAO;
import gameover.models.Video;

@Repository
public class VideoDAO implements IntVideoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Video getVideo(int idvideo) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.get(Video.class, idvideo);
	}

	@Override
	public List<Video> getVideos() {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Video",Video.class).getResultList();
	}

	@Override
	public List<Video> getLastXVideos(int lastX) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Video v order by v.idvideo desc",Video.class).setMaxResults(lastX).getResultList();
	}
	
	@Override
	public List<Video> getLastVideosRango(int elementoInicial, int numeroElementos) {
		Session sesion=sessionFactory.getCurrentSession();
		return sesion.createQuery("from Video ORDER BY idvideo DESC",Video.class).setFirstResult(elementoInicial).setMaxResults(numeroElementos).getResultList();
	}

	@Override
	public int getTotalVideos() {
		Session sesion=sessionFactory.getCurrentSession();
		return ((Long) sesion.createQuery("Select count(*) from Video").uniqueResult()).intValue();
	}
	
	@Override
	public void saveVideo(Video video) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(video);
	}

	@Override
	public void deleteVideo(Video video) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.delete(video);
	}
}