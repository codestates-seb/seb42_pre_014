package pre14.stackoverflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class pageInfo {
    private int page;
    private int size;
    private long totalElements;
    private int totalPage;
}
