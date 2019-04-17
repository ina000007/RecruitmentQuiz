package com.nishant.QuizDemo.schedular;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestJobScheduler {

	@Autowired
	private Scheduler scheduler;

	public void scheduleJob(String id,String date,String start, String end) throws SchedulerException, ParseException {
		Date time = new Date();
		String t = date+" "+start;
		System.out.println("t=>  "+t);
        DateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date strtDateTime = simple.parse(t);
        Date endDateTime  = simple.parse(date+" "+end);
		
		JobDetail startJobDetail = buildJobDetail(id, "1");
		Trigger startJobTrigger = buildJobTrigger(startJobDetail, strtDateTime);
		scheduler.scheduleJob(startJobDetail, startJobTrigger);
		
		JobDetail endJobDetail = buildJobDetail(id, "0");
		Trigger endJobTrigger = buildJobTrigger(endJobDetail,endDateTime);
		scheduler.scheduleJob(endJobDetail, endJobTrigger);
		System.out.println("Schedule the job");
	}

	private JobDetail buildJobDetail(String id, String status) {
		JobDataMap jobDataMap = new JobDataMap();

		jobDataMap.put("testId", id);
		jobDataMap.put("isActive", status);
		String jobWork = (status.equals("1"))?"Creating Job For START Test Time":"Creating Job For END Test Time"; 
		return JobBuilder.newJob(ScheduleTestJob.class).withIdentity(UUID.randomUUID().toString(), "Test Schedular-job- "+jobWork)
				.withDescription("Created the JOB for- "+jobWork).usingJobData(jobDataMap).storeDurably().build();
	}

	private Trigger buildJobTrigger(JobDetail jobDetail, Date time) {
		return TriggerBuilder.newTrigger().forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "Test Schedular").withDescription("Created the trigger for test start")
				.startAt(time)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow()).build();
	}

}
