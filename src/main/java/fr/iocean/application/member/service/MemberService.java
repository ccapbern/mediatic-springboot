package fr.iocean.application.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memRep;
	
	public void save(Member member){
		memRep.save(member);
	}
	
	public Member findOne(Long Id){
		return memRep.findOne(Id);
	}

	public List<Member> findAll(){
		return memRep.findAll();
	}
	
	public void delete(Member member){
		memRep.delete(member);
	}

	public void update(Member member){
		memRep.save(member);
	}

}
