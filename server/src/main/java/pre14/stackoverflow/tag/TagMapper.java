package pre14.stackoverflow.tag;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {
    Tag tagPostDtoToTag(TagDto.Post tagPostDto);
    TagDto.Response tagToResponseDto(Tag tag);
}
