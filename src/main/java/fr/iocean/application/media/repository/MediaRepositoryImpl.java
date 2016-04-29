package fr.iocean.application.media.repository;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.repository.AbstractJpaRepository;

public class MediaRepositoryImpl extends AbstractJpaRepository<Media> implements MediaRepositoryCustom {
	
	@Override
    protected Class<Media> getEntityClass() {
        return Media.class;
    }
}
