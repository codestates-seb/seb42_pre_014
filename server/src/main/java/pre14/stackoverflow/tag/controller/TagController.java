package pre14.stackoverflow.tag.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pre14.stackoverflow.globaldto.SingleResponseDto;
import pre14.stackoverflow.tag.entity.Tag;
import pre14.stackoverflow.tag.mapper.TagMapper;
import pre14.stackoverflow.tag.service.TagService;
import pre14.stackoverflow.tag.dto.TagDto.TagResponseDto;
import pre14.stackoverflow.tag.dto.TagDto.TagPostDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions")
@Validated
public class TagController {

    //private final QuestionService questionService;
    private final TagService tagService;
    private final TagMapper mapper;

    public TagController(TagService tagService, TagMapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }

    //태그 등록
    @PostMapping("/{question-id}/tags")
    public ResponseEntity postTag(@PathVariable("question-id") @Positive Long questionId,
                                  @Valid @RequestBody TagPostDto tagPostDto) {
        tagPostDto.updateQuestionId(questionId); // PostDto에 QuestionId 설정해주기

        Tag tag = tagService.createTag(mapper.tagPostDtoToTag(tagPostDto));
        TagResponseDto response = mapper.tagToTagResponseDto(tag);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

    //태그 전체 조회(질문 상세페이지에서 사용)
    @GetMapping("/{questions-id}/tags")
    public ResponseEntity getTags(){
        List<Tag> tagList = tagService.findTags();

        return new ResponseEntity<>(mapper.tagToTagDtos(tagList), HttpStatus.OK);
    }

    //태그 삭제
    @DeleteMapping("/{questions-id}/tags/{id}")
    public ResponseEntity deleteTag(@PathVariable("question-id") @Positive Long questionId,
                                    @PathVariable("id") @Positive Long id){
        tagService.deleteTag(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 태그 수정 기능은 존재하지 않음
}
