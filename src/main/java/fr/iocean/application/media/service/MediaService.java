package fr.iocean.application.media.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.iocean.application.media.exception.MediaNotFoundException;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.repository.MediaRepository;

@Service
public class MediaService {
	
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	MediaRepository mediaRepository;

	public void save(Media media){
		mediaRepository.save(media);
	}
	
	public List<Media> findAll(){
		return mediaRepository.findAll();
	}
	
	public Media findById(Long id){
		return mediaRepository.findOne(id);
	}
	
	public void update(Long id, Media media){
		if(findById(id) != null){
			mediaRepository.save(media);
		} else {
			throw new MediaNotFoundException();
		}
	}
	
	public void delete(Long id, Media media){
		if(findById(id) != null){
			mediaRepository.delete(media);
		} else {
			throw new MediaNotFoundException();
		}
	}
	
	public Page<Media> findAll(int pageNumber) {
        PageRequest request =
            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "startTime");
        return mediaRepository.findAll(request);
    }
	
}
