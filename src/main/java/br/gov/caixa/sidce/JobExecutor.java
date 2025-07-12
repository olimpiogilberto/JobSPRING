package br.gov.caixa.sidce;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobExecutor implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job job;

    public JobExecutor(JobLauncher jobLauncher, @Qualifier("imprimeOlaJob") Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(
            job,
            new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis()) // garante que seja um job novo
                .toJobParameters()
        );
    }
}