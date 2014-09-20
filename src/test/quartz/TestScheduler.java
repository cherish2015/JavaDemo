package test.quartz;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class TestScheduler {

	public void start() throws SchedulerException{
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		Date startTime = DateBuilder.futureDate(2, IntervalUnit.SECOND);
		JobDetail job = JobBuilder.newJob(TestJob.class).build();
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.startAt(startTime)
				.withSchedule(
						SimpleScheduleBuilder.repeatHourlyForever())
						//SimpleScheduleBuilder.repeatMinutelyForever())
				.build();
		sched.scheduleJob(job, trigger);
		
		sched.start();
	}

}
