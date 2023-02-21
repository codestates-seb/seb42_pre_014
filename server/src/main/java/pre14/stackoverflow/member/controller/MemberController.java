package pre14.stackoverflow.member.controller;


import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre14.stackoverflow.globaldto.MultiResponseDto;
import pre14.stackoverflow.globaldto.SingleResponseDto;
import pre14.stackoverflow.member.dto.*;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.member.mapper.MemberMapper;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.utils.UriCreator;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RequestMapping("/members")
@RestController
@Validated
@ToString
@Slf4j
public class MemberController {
    private final String MEMBER_DEFAULT_URL="members";

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService,MemberMapper mapper){
        this.memberService=memberService;
        this.mapper=mapper;
    }
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody){
        System.out.println(requestBody.toString());
        Member member=mapper.memberPostToMember(requestBody);
        System.out.println(member.toString());
        Member createdMember=memberService.createMember(member);
        System.out.println(createdMember.toString());
        MemberDto.Response response=mapper.memberToMemberResponse(createdMember);
//        URI location= UriCreator.createUri(MEMBER_DEFAULT_URL, createdMember.getMemberId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);//.created(location).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberDto.Patch requestBody){
        requestBody.setMemberId(memberId);

        Member member=
                memberService.updateMember(mapper.memberPatchToMember(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponse(member)), HttpStatus.OK);

    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id")@Positive long memberId){
        Member member=memberService.findMember(memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponse(member)),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size){
        Page<Member> pageMembers=memberService.findMembers(page-1,size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.membersToMemberResponses(members), pageMembers), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId){
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}