package fr.iocean.application.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.iocean.application.exception.EntityNotFoundException;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.member.service.MemberService;
import fr.iocean.application.pojo.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@Transactional
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping( method = RequestMethod.GET)
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @RequestMapping(value = "size", method = RequestMethod.GET)
    public String findPages() throws JsonProcessingException {
        Pages pages = new Pages();
        pages.setPages(5);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(pages);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Member findById(@PathVariable Long id) {
        Member member =  memberService.findOne(id);
        if (member == null) {
            throw new EntityNotFoundException();
        }
        return member;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Member member) {
        memberService.save(member);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Long id, @RequestBody @Valid Member member)  {
        member.setId(id);
        memberService.save(member);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        Member member = memberService.findOne(id);
        memberService.delete(member);
    }


}
