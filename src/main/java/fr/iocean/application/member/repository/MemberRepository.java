package fr.iocean.application.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.iocean.application.member.model.Member;

@Repository	
public interface MemberRepository extends JpaRepository<Member, Long>{

    
}
