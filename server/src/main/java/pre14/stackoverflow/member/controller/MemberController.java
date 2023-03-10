package pre14.stackoverflow.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre14.stackoverflow.answer.mapper.AnswerMapper;
import pre14.stackoverflow.globaldto.MultiResponseDto;
import pre14.stackoverflow.globaldto.SingleResponseDto;
import pre14.stackoverflow.member.dto.*;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.member.mapper.MemberMapper;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.mapper.QuestionMapper;
import pre14.stackoverflow.utils.UriCreator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RequestMapping("/members")
@RestController
@Validated
@ToString
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    private final QuestionMapper questionMapper;

    private final AnswerMapper answerMapper;



    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody){
        System.out.println(requestBody.toString());
        Member member=mapper.memberPostToMember(requestBody);
        System.out.println(member.toString());
        Member createdMember=memberService.createMember(member);
        System.out.println(createdMember.toString());
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponse(createdMember)), HttpStatus.CREATED);
    }


    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberDto.Patch requestBody){

        Member member=mapper.memberPatchToMember(requestBody);

        Member updatedMember=memberService.updateMember(member);
        MemberDto.Response response=mapper.memberToMemberResponse(updatedMember);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );

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