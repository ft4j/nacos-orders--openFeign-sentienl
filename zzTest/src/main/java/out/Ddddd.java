package out;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ddddd {
    @Bean
    public OutBean getOutBean(){
        return new OutBean();
    }
}
