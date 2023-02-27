package pre14.stackoverflow.tag;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-23T13:42:21+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag tagPostDtoToTag(TagDto.Post tagPostDto) {
        if ( tagPostDto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setTagName( tagPostDto.getTagName() );

        return tag;
    }

    @Override
    public TagDto.Response tagToResponseDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto.Response response = new TagDto.Response();

        response.setTagId( tag.getTagId() );
        response.setTagName( tag.getTagName() );

        return response;
    }
}
