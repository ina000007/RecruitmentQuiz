package com.nishant.QuizDemo.schedular;

import java.util.Optional;

import org.quartz.*;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.nishant.QuizDemo.model.TestSet;
import com.nishant.QuizDemo.repository.TestSetRepository;

public class ScheduleTestJob extends QuartzJobBean {

	@Autowired
	TestSetRepository testSetRepository;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String testId = jobDataMap.getString("testId");
        String isActive = jobDataMap.getString("isActive");
        
		System.out.println("testId -> "+testId+" isActive "+isActive);
		
		Optional<TestSet> opt = testSetRepository.findById(Long.parseLong(testId));
		
		if(opt.isPresent()) {
			TestSet testSet = opt.get();
		    testSet.setIsActive(isActive);
		    testSetRepository.save(testSet);
		}
		
	}

}
