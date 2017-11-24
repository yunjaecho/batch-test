package io.spring.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


/**
 * Created by USER on 2017-11-23.
 */
public class JobListener implements JobExecutionListener {

    private JavaMailSender mailSender;

    public JobListener(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();

        SimpleMailMessage mail = getSimpleMailMessage(String.format("%s is starting", jobName),
                String.format("Per your request, we are informing you that %s is starting", jobName));

        mailSender.send(mail);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();

        SimpleMailMessage mail = getSimpleMailMessage(String.format("%s is completed", jobName),
                String.format("Per your request, we are informing you that %s has complete", jobName));

        mailSender.send(mail);
    }

    private SimpleMailMessage getSimpleMailMessage(String subject, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("chotom73@gmail.com");
        mail.setSubject(subject);
        mail.setText(text);
        return mail;
    }
}
