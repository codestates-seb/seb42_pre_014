package pre14.stackoverflow.member.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre14.stackoverflow.member.dto.MemberPatchDto;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.member.mapper.MemberMapper;
import pre14.stackoverflow.member.dto.MemberPostDto;
import pre14.stackoverflow.member.repository.SingleResponseDto;
import pre14.stackoverflow.member.response.MultiResponseDto;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.member.utils.UriCreator;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RequestMapping("/members")
@RestController
@Validated
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
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto){
        Member member=memberService.createMember(mapper.memberPostDtoToMember(memberDto));
        URI location= UriCreator.createUri(MEMBER_DEFAULT_URL, member.getMemberId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberPatchDto memberPatchDto){
        memberPatchDto.setMemberId(memberId);

        Member member=memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)), HttpStatus.OK);

    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id")@Positive long memberId){
        Member member=memberService.findMember(memberId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size){
        Page<Member> pageMembers=memberService.findMembers(page-1,size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.membersToMemberResponseDto(members), pageMembers), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId){
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
