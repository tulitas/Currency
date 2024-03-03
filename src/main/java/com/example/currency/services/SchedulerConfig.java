package com.example.currency.services;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.text.ParseException;

@ComponentScan
@Configuration
public class SchedulerConfig {

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        return scheduler;
    }

    @Bean
    public JobDetail currencyJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(CurrencyJob.class);
        factoryBean.setDurability(true);
        factoryBean.setName("currencyJob");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public Trigger currencyImmediateTrigger(JobDetail currencyJobDetail) {
        SimpleTriggerFactoryBean triggerFactoryBean = new SimpleTriggerFactoryBean();
        triggerFactoryBean.setJobDetail(currencyJobDetail);
        triggerFactoryBean.setRepeatCount(0); // works 1 time
        triggerFactoryBean.setName("currencyImmediateTrigger");
        triggerFactoryBean.afterPropertiesSet();
        return triggerFactoryBean.getObject();
    }

    @Bean
    public Trigger currencyScheduledTrigger(JobDetail currencyJobDetail) throws ParseException {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setJobDetail(currencyJobDetail);
        triggerFactoryBean.setCronExpression("0 */2 * * * ?"); // Каждые 50 секунд
        triggerFactoryBean.setName("currencyScheduledTrigger");
        triggerFactoryBean.afterPropertiesSet();
        return triggerFactoryBean.getObject();
    }
}
