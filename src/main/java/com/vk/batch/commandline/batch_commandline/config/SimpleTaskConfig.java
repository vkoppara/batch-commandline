package com.vk.batch.commandline.batch_commandline.config;

import com.vk.batch.commandline.batch_commandline.task.SimpleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class SimpleTaskConfig {

    @Autowired
    private SimpleTask simpleTask;

    private final String SIMPLE_JOB = "SimpleJob";

    @Bean
    public Job simpleTaskJob(JobRepository jobRepository, Step simpleTaskStep){
        return new JobBuilder(SIMPLE_JOB, jobRepository)
                .start(simpleTaskStep)
                .build();
    }

    @Bean
    public Step simpleTaskStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder(SIMPLE_JOB, jobRepository)
                .tasklet(simpleTask, platformTransactionManager)
                .allowStartIfComplete(true)
                .build();
    }
}
