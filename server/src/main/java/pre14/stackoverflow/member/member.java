package pre14.stackoverflow.member;

import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class member {
    private Long memberId;

    private String email;
    private String name;


}
