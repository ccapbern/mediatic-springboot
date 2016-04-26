package fr.iocean.application.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.iocean.application.media.model.Media;


@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
	List<Media> findByAuthor(String author);
	
	List<Media> findByTitle(String title);
}
