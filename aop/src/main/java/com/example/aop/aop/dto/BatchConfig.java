package com.example.demo.config;

import com.example.aop.aop.dto.entity.Deletetesttable;
import com.example.aop.aop.dto.repository.DeletetesttableRepositoryImpl;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DeletetesttableRepositoryImpl service;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       DeletetesttableRepositoryImpl service) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.service = service;
    }

    // 使用 MyBatis-Plus 读取数据库数据
    @Bean
    public ListItemReader<Deletetesttable> reader() {
        List<Deletetesttable> users = service.list();
        return new ListItemReader<>(users);
    }

    // 处理器：转换数据
    @Bean
    public ItemProcessor<Deletetesttable, Deletetesttable> processor() {
        return user -> {
            user.setName(user.getName().toUpperCase()); // 姓名转大写
            return user;
        };
    }

    // 写入 CSV 文件
    @Bean
    public ItemWriter<Deletetesttable> writer() {
        return items -> {
            for (Deletetesttable user : items) {
                System.out.println("处理后数据: " + user);
                // 实际项目中可使用 FlatFileItemWriter 写入文件
            }
        };
    }

    // 定义 Step
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Deletetesttable, Deletetesttable>chunk(10)//定义每次处理几个
                .reader(reader())//只要给定一个查询sql 它就会直接按照chunk的数量读取和落库
                .processor(processor())
                .writer(writer())//插入sql是需要自己写的
                .build();
    }

    // 定义 Job
    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .flow(step1())//可以设置多个step
                .end()
                .build();
    }

}