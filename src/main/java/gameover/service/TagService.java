package gameover.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameover.dao.iface.IntTagDAO;
import gameover.models.Tag;
import gameover.service.iface.IntTagService;

@Service("tagService")
public class TagService implements IntTagService {

	@Autowired
	private IntTagDAO tagDAO;
	
	@Override
	@Transactional
	public Tag getTag(int idtag) {
		return tagDAO.getTag(idtag);
	}

	@Override
	@Transactional
	public Tag getTagByNombre(String nombre_opt) {
		return tagDAO.getTagByNombre(nombre_opt);
	}
	
	@Override
	@Transactional
	public List<Tag> getTags() {
		return tagDAO.getTags();
	}

	@Override
	@Transactional
	public List<Tag> getLastXTags(int lastX) {
		return tagDAO.getLastXTags(lastX);
	}
	
	@Override
	@Transactional
	public void saveTag(Tag tag) {
		tagDAO.saveTag(tag);
	}
	
	@Override
	@Transactional
	public Tag saveAndRetrieveTag(Tag tag) {
		return tagDAO.saveAndRetrieveTag(tag);
	}
	
	@Override
	@Transactional
	public void deleteTag(Tag tag) {
		tagDAO.deleteTag(tag);
	}
}