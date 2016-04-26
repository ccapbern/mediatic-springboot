package fr.iocean.application.users.repository;

import javax.persistence.EntityManager;

import fr.iocean.application.helpers.DatabaseHelper;
import fr.iocean.application.users.model.Users;

public class UsersRepository {

    private static UsersRepository dao;

    public static UsersRepository instance() {
        if (dao == null) {
            dao = new UsersRepository();
        }
        return dao;
    }

    private UsersRepository() {
    }

    public Users find(Long id) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        Users users = entityManager.find(Users.class, id);
        entityManager.close();
        return users;
    }

    public Users persist(Users users) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        DatabaseHelper.beginTx(entityManager);
        entityManager.persist(users);
        DatabaseHelper.commitTxAndClose(entityManager);
        return users;
    }

    public Users merge(Users users) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        DatabaseHelper.beginTx(entityManager);
        entityManager.merge(users);
        DatabaseHelper.commitTxAndClose(entityManager);
        return users;
    }

    public void remove(Long id) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        DatabaseHelper.beginTx(entityManager);
        entityManager.remove(entityManager.find(Users.class, id));
        DatabaseHelper.commitTxAndClose(entityManager);
    }
}
