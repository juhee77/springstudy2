package practice.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "practice.core",
        //실무에서 제외하지 않는다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes= Configuration.class) //예제를 유지하기 위해서 잠시 제거 필터 유지
)
public class AutoAppConfig {

}
