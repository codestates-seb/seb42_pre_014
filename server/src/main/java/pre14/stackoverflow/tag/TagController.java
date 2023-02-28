package pre14.stackoverflow.tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.service.QuestionService;

@RestController
@RequestMapping("/questions/tags")
@RequiredArgsConstructor
@Slf4j
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity TagPost(@RequestBody TagDto.Post tagPostDto){
        Tag tag = tagMapper.tagPostDtoToTag(tagPostDto);
        Question question = questionService.findQuestion(tagPostDto.getQuestionId());// 해당 게시글 번호를저장해주는
        tag.setQuestion(question); // 서비스로 옮기려했지만 실패
        Tag createTag = tagService.createTag(tag);
        TagDto.Response response = tagMapper.tagToResponseDto(createTag);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
