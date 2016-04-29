package fr.iocean.application.borrow.repository;

import fr.iocean.application.member.model.Member;

import java.util.List;

public interface BorrowRepositoryCustom {
    List<Member> getMemberWithBorrow();
}
