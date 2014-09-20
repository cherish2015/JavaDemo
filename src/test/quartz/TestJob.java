package test.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestJob implements Job {
	
	private static Logger log = LoggerFactory.getLogger(TestJob.class);
	
	public static boolean ok = true;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			log.info("TestJob");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			try {
				context.getScheduler().shutdown(true);
			} catch (SchedulerException e1) {
				log.error(e1.getMessage(), e1);
			}
		}
	}

}
