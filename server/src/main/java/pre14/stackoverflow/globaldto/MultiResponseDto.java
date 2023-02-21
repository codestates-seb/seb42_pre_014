package pre14.stackoverflow.globaldto;

import lombok.Getter;
import org.springframework.data.domain.Page;
import pre14.stackoverflow.globaldto.PageInfo;

import java.util.List;

@Getter
public class MultiResponseDto<T>{
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() +1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
