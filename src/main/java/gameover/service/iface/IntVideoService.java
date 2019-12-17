package gameover.service.iface;

import java.util.List;

import gameover.models.Video;

public interface IntVideoService {
	
	Video getVideo(int idvideo);
	
	List<Video> getVideos();
	
	List<Video> getLastXVideos(int lastX);
	
	List<Video> getLastVideosRango(int elementoInicial, int numeroElementos);
	
	int getTotalVideos();
	
	void saveVideo(Video video);
	
	void deleteVideo(Video video);
}