package fr.iocean.application.user.repository;

import fr.iocean.application.repository.AbstractJpaRepository;
import fr.iocean.application.user.model.User;

public class UserRepositoryImpl extends AbstractJpaRepository<User> implements UserRepositoryCustom {


    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
