package fr.iocean.application.members.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.iocean.application.helpers.DatabaseHelper;
import fr.iocean.application.medias.model.Medias;
import fr.iocean.application.members.model.Members;

public class MembersRepository {

    private static MembersRepository dao;

    private MembersRepository() {
    }

    public static MembersRepository instance() {
        if (dao == null) {
            dao = new MembersRepository();
        }
        return dao;
    }

    public Members find(Long id) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        Members member = entityManager.find(Members.class, id);
        entityManager.close();
        return member;
    }

    public Members persist(Members member) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        DatabaseHelper.beginTx(entityManager);
        entityManager.persist(member);
        DatabaseHelper.commitTxAndClose(entityManager);
        return member;
    }

    public Members merge(Members member) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        DatabaseHelper.beginTx(entityManager);
        entityManager.merge(member);
        DatabaseHelper.commitTxAndClose(entityManager);
        return member;
    }

    public void remove(Long id) {
        EntityManager entityManager = DatabaseHelper.createEntityManager();
        DatabaseHelper.beginTx(entityManager);
        entityManager.remove(entityManager.find(Members.class, id));
        DatabaseHelper.commitTxAndClose(entityManager);
    }

    public List<Members> getMembersById(Long id) {
        String sql = "select m "
                + "from Members m "
                + "where m.id like :id";
        EntityManager entityManager = DatabaseHelper.createEntityManager();

        TypedQuery query = entityManager.createQuery(sql, Members.class);
        query.setParameter("id", id + '%');

        return query.getResultList();
    }

    public List<Members> getMembersByName(String name) {
        String sql = "select m "
                + "from Members m "
                + "where m.lastname like :name";
        EntityManager entityManager = DatabaseHelper.createEntityManager();

        TypedQuery query = entityManager.createQuery(sql, Members.class);
        query.setParameter("name", "%" + name.toUpperCase() + "%");

        return query.getResultList();
    }

    public List<Medias> getAllMedias(Members member) {
        String sql = "select me "
                + "from Members m "
                + "join m.borrow b "
                + "join  b.media me "
                + "where m.id=:id";
        EntityManager entityManager = DatabaseHelper.createEntityManager();

        TypedQuery query = entityManager.createQuery(sql, Medias.class);
        query.setParameter("id", member.getId());

        return query.getResultList();
    }
}
