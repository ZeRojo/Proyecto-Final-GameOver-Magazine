package gameover.dao.iface;

import java.util.List;

import gameover.models.Tag;

public interface IntTagDAO {

	Tag getTag(int idtag);
	
	Tag getTagByNombre(String nombre_opt);
	
	List<Tag> getTags();
	
	List<Tag> getLastXTags(int lastX);
	
	void saveTag(Tag tag);
	
	Tag saveAndRetrieveTag(Tag tag);
	
	void deleteTag(Tag tag);
}