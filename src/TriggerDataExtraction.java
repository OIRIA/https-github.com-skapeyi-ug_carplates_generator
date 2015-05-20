/**
 * This class is responsible for scheduling of the data extraction
 * It extracts data every hour that goes by, from the time it is started
 * The job scheduling is based on a java library, http://www.quartz-scheduler.org
 *  */
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
 
public class TriggerDataExtraction {
	public  static  void main(String[] args) throws Exception {
		
		JobDetail job = JobBuilder.newJob(XmlExtraction.class)//we create a job, which is the data generation class
			.withIdentity("dataExtraction", "extraction").build();
 
        //then we trigger the job to run every after 60minutes forever,because the stations will be reading data on a daily basis.     
		//You can change this section of the code to 1min, to test the looping..
		Trigger trigger = TriggerBuilder
			.newTrigger()
			.withIdentity("dataExtraction", "extraction")
			.withSchedule(
				SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3600).repeatForever()).build();
 
		// then we start the scheduler.
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
		
 
	}
}