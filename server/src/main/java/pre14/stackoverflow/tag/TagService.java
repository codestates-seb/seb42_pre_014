package pre14.stackoverflow.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag createTag(Tag tag){return tagRepository.save(tag);}

    // ** 태그 삭제 **
    public void deleteTag(Long tagId) {
        // 유효한 태그인지 검증
        Tag findTag = findVerifiedTag(tagId);
        // 태그 삭제
        tagRepository.delete(findTag);
    }
    // ** 유효한 태그인지 검증 **
    private Tag findVerifiedTag(Long tagId) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        Tag findTag = optionalTag.orElseThrow(() -> new NoSuchElementException());

        return findTag;
    }
}
