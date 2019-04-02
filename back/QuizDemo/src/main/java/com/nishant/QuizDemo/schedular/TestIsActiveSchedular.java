package com.nishant.QuizDemo.schedular;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nishant.QuizDemo.model.TestSet;
import com.nishant.QuizDemo.repository.TestSetRepository;
import com.nishant.QuizDemo.utils.Utility;

@Component
public class TestIsActiveSchedular {

	@Autowired
	TestSetRepository testSetRepository;

	@Scheduled(initialDelay = 20000, fixedRate = 15000)
	public void run() {
		System.out.println("Nishant");
		String driveDate = "";
		String todayTime = "";
		String todayDate = "";
		String isActiveStatus = "0";
		SimpleDateFormat df = null;
		List<TestSet> testSetLst = testSetRepository.findAll();
		for (TestSet testSet : testSetLst) {
			driveDate = testSet.getDriveDate();
			df = new SimpleDateFormat("yyyy-MM-dd");
			todayDate = df.format(new Date());
			System.out.println("dri " + driveDate + "  today  " + todayDate);
			if (driveDate.equals(todayDate)) {
				df = new SimpleDateFormat("HH:mm:ss"); // HH for 0-23
				todayTime = df.format(new Date());
				System.out.println("1 "+testSet.getStartTime());
				System.out.println("2 "+todayTime);
				System.out.println("3 "+testSet.getEndTime());
				if (Utility.hhmmssToSec(testSet.getStartTime()) <= Utility.hhmmssToSec(todayTime)
						&& Utility.hhmmssToSec(todayTime) < Utility.hhmmssToSec(testSet.getEndTime())) {
					isActiveStatus = "1";
					System.out.println("yes");
				}
			} else {
				isActiveStatus = "0";
			}
			testSet.setIsActive(isActiveStatus);
			testSetRepository.save(testSet);
		}

	}

}
