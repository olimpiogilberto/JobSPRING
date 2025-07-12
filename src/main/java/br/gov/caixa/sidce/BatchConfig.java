package br.gov.caixa.sidce;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    Step imprimeOlaStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, RegistroDataTasklet registroDataTasklet) {
    			return new StepBuilder("imprimeOlaStep", jobRepository)
    			.tasklet(registroDataTasklet, transactionManager)
    			.build();
    			}


    @Bean
    Job imprimeOlaJob(JobRepository jobRepository, Step imprimeOlaStep) {
       return new JobBuilder("imprimeOlaJob", jobRepository)
               .start(imprimeOlaStep)
               .build();
   }


}
