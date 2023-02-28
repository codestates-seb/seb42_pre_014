package pre14.stackoverflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StackoverflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(StackoverflowApplication.class, args);
	}
}

// 전체 조회 가능 할 수 있게 생각 해보기, answer를 볼 때 member만 조회가 가능한지 알 수 있나?  /{answer} -> 참고