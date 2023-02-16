package pre14.stackoverflow.member;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre14.stackoverflow.member.Member;
import pre14.stackoverflow.member.MemberPatchDto;
import pre14.stackoverflow.member.MemberPostDto;
import pre14.stackoverflow.member.MemberService;
import pre14.stackoverflow.member.MemberMapper;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RequestMapping
@RestController
@Validated
public class MemberController {
    private final MemberService memberService;

    public MemberController(){
        this.memberService=new MemberService();
    }
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto){
        Member member=new Member();
        member.setName(memberPostDto.getName());
        member.setEmail(memberPostDto.getEmail());
        member.setPhone(memberPostDto.getPhone());
        return new ResponseEntity<>(memberPostDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestBody MemberPatchDto memberPatchDto){
        memberPatchDto.setMemberId(memberId);

        Member member=new Member();
        member.setMemberId(memberPatchDto.getMemberId());



        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id")@Positive long memberId){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(){

        return new ResponseEntity(memberService.findMembers(),HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id")@Positive long memberId){

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
