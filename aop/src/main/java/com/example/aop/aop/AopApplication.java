package com.example.aop.aop;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@SpringBootApplication
@ServletComponentScan
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
//        ConfigurableListableBeanFactory beanFactory = new ConfigurableListableBeanFactory();
//        beanFactory.setBeanClassLoader();
    }

//    //job调度器
//    @Autowired
//    private JobLauncher jobLauncher;
//    //job构造器工厂
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    //step构造器工厂
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
////    @Bean
////    public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
////        SimpleJobLauncher launcher = new SimpleJobLauncher();
////        launcher.setJobRepository(jobRepository);
////        launcher.setTaskExecutor(new SimpleAsyncTaskExecutor()); // 异步执行器
////        launcher.afterPropertiesSet();
////        return launcher;
////    }
//    //任务-step执行逻辑由tasklet完成
//    @Bean
//    public Tasklet tasklet(){
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("Hello SpringBatch....111111");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
//    @Bean
//    public Tasklet tasklet2(){
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("Hello SpringBatch....22222222");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
//    //作业步骤-不带读/写/处理
//    @Bean
//    public Step step1(){
//        return stepBuilderFactory.get("step1")
//                .tasklet(tasklet())
//                .build();
//    }
//    @Bean
//    public Step step2(){
//        return stepBuilderFactory.get("step2")
//                .tasklet(tasklet2())
//                .build();
//    }
//    //定义作业
//    @Bean
//    public Job job(){
//        return jobBuilderFactory.get("hello-job")
//                .start(step1()).start(step2())
//                .build();
//    }

}
