package pre14.stackoverflow.tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@Slf4j
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    public ResponseEntity TagPost(@RequestBody TagDto.Post tagPostDto){
        Tag tag = tagMapper.tagPostDtoToTag(tagPostDto);
        Tag createTag = tagService.createTag(tag);
        TagDto.Response response = tagMapper.tagToResponseDto(createTag);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
