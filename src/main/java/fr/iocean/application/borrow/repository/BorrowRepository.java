package fr.iocean.application.borrow.repository;

import fr.iocean.application.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository	
public interface BorrowRepository extends JpaRepository<Member, Long>, BorrowRepositoryCustom {

}
