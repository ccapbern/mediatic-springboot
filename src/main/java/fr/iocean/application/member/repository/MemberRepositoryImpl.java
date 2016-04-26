package fr.iocean.application.member.repository;

import fr.iocean.application.member.model.Member;
import fr.iocean.application.repository.AbstractJpaRepository;

public class MemberRepositoryImpl extends AbstractJpaRepository<Member> implements MemberRepositoryCustom {


    @Override
    protected Class<Member> getEntityClass() {
        return Member.class;
    }
}
