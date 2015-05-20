/**
 * This class is responsible for scheduling of the data generation, and the data parsing
 * After a given interval, that is every hour, we generate the data,and store it temporarily in a folder
 * and then 15minutes later, the data is parsed and saved into a database, and the files are moved from 
 * the folder where they are temporarily stored to avoid the data being parsed again.
 * 
 * The job scheduling is based on a java library, http://www.quartz-scheduler.org
 *  */
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
 
public class TriggerCarDataGeneration {
	public static void main(String[] args) throws Exception {
		
		JobDetail job = JobBuilder.newJob(GenerateCarData.class)//we create a job, which is the data generation class
			.withIdentity("dataExtraction", "extraction").build();
 
        //then we trigger the job to run every after 60minutes forever,because the stations will be reading data on a daily basis.     
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