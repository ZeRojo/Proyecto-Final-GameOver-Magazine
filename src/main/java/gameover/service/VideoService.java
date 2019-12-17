package gameover.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameover.dao.iface.IntVideoDAO;
import gameover.models.Video;
import gameover.service.iface.IntVideoService;

@Service("videoService")
public class VideoService implements IntVideoService {

	@Autowired
	private IntVideoDAO videoDAO;
	
	@Override
	@Transactional
	public Video getVideo(int idvideo) {
		return videoDAO.getVideo(idvideo);
	}

	@Override
	@Transactional
	public List<Video> getVideos() {
		return videoDAO.getVideos();
	}

	@Override
	@Transactional
	public List<Video> getLastXVideos(int lastX) {
		return videoDAO.getLastXVideos(lastX);
	}

	@Override
	@Transactional
	public List<Video> getLastVideosRango(int elementoInicial, int numeroElementos) {
		return videoDAO.getLastVideosRango(elementoInicial, numeroElementos);
	}
	
	@Override
	@Transactional
	public int getTotalVideos() {
		return videoDAO.getTotalVideos();
	}
	
	@Override
	@Transactional
	public void saveVideo(Video video) {
		videoDAO.saveVideo(video);		
	}

	@Override
	@Transactional
	public void deleteVideo(Video video) {
		videoDAO.deleteVideo(video);
	}
}