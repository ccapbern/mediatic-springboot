package fr.iocean.application.media.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.service.MediaService;

@RestController
@RequestMapping("/api/medias")
public class MediaController {
	@Autowired
	private MediaService mediaService;

	@RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
	public Page<Media> getPageMedias(@PathVariable Integer pageNumber) {
		System.out.println("\n\n\n");
		System.out.println("pageNumber : " + pageNumber);
		System.out.println("\n\n\n");
		return mediaService.getPageMedias(pageNumber);
//	    Page<Media> page = mediaService.getPageMedias(pageNumber);
//
//	    int current = page.getNumber() + 1;
//	    int begin = Math.max(1, current - 5);
//	    int end = Math.min(begin + 10, page.getTotalPages());
//
//	    return "deploymentLog";
	}
	
	@RequestMapping(value = "/nbpages", method = RequestMethod.GET)
	public int getNbPageMedias() {
		return mediaService.getNbPageMedias();
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Media findById(@PathVariable Long id) {
		return mediaService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Media> findAll() {
		return mediaService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Media resource) {
		mediaService.save(resource);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public void update(@PathVariable Long id, @RequestBody @Valid Media resource) {
		Media media = mediaService.findById(id);
		if(media != null)
			mediaService.update(id, resource);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id, @RequestBody @Valid Media resource) {
		Media media = mediaService.findById(id);
		if(media != null)
			mediaService.delete(id, resource);
	}
	
}
