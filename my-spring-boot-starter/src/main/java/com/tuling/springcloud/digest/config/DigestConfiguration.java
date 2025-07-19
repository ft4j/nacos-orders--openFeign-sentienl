package com.tuling.springcloud.digest.config;

import com.tuling.springcloud.digest.Digest;
import com.tuling.springcloud.digest.impl.Md5Digest;
import com.tuling.springcloud.digest.impl.ShaDigest;
import com.tuling.springcloud.digest.setting.Setting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
@EnableConfigurationProperties(Setting.class)
public class DigestConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "digest",name = "type",havingValue = "md5")
    public Digest md5Digest(){
        System.out.println("加载md5");
        return new Md5Digest();
    }

    @Bean
    @ConditionalOnProperty(prefix = "digest",name = "type",havingValue = "sha")
    public Digest shaDigest(){
        System.out.println("加载sha");
        return new ShaDigest();
    }
}
