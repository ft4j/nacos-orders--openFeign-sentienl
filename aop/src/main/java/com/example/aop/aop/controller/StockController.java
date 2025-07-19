package com.example.aop.aop.controller;

import com.example.aop.aop.aop.IHelloWorldService;
import com.example.aop.aop.dto.entity.Deletetesttable;
import com.example.aop.aop.dto.repository.DeletetesttableRepositoryImpl;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock")
public class StockController {
//    private final JobLauncher jobLauncher;
//    private final Job importUserJob;
//
//    public StockController(JobLauncher jobLauncher, Job importUserJob) {
//        this.jobLauncher = jobLauncher;
//        this.importUserJob = importUserJob;
//    }

    @RequestMapping("declineStock")
    public String declineStock() {
        System.out.println("库存-1");
        return "库存-1";
    }
    @Autowired
    IHelloWorldService iHelloWorldService;
    @RequestMapping("aop")
    public String aop() {
        iHelloWorldService.sayHello();
        return "说完了";
    }
    @Autowired
    DeletetesttableRepositoryImpl deletetesttableRepository;

    @RequestMapping("ddd")
            public String cccc(){
        Deletetesttable dtt = new Deletetesttable();
        dtt.setId(3);
        deletetesttableRepository.save(dtt);
        return "success";
    }

//    @RequestMapping("/run")
//    public String runJob() throws Exception {
//        JobParameters params = new JobParametersBuilder()
//                .addString("jobId", String.valueOf(System.currentTimeMillis()))
//                .toJobParameters();
//        jobLauncher.run(importUserJob, params);
//        return "Job started successfully!";
//    }


}
