package fr.iocean.application.borrow.repository;

import fr.iocean.application.borrow.model.Borrow;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.repository.AbstractJpaRepository;

import java.util.List;

public class BorrowRepositoryImpl extends AbstractJpaRepository<Borrow> implements BorrowRepositoryCustom {


    @Override
    protected Class<Borrow> getEntityClass() {
        return Borrow.class;
    }

    @Override
    public List<Member> getMemberWithBorrow() {
        return null;
    }
}
