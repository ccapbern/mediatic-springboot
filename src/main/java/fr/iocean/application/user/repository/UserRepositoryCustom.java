package fr.iocean.application.user.repository;

import fr.iocean.application.user.model.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findOneByLogin(String login);
}
