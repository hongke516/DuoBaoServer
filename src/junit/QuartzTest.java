package junit;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.fozoto.duobao.core.util.TimeUtil;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

/**
 * Created by qingyan on 16-7-29.
 */

public class QuartzTest {
    private Logger log = Logger.getLogger(QuartzTest.class);

    public static void main(String[] args) {

        System.out.println("测试");

        try {


            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("now", TimeUtil.getTime().toString())
                    .usingJobData("money", 1000f)
                    .build();

            JobDetail mergeJob = JobBuilder.newJob(MergeJob.class)
                    .withIdentity("job_merge", "group1")
                    .usingJobData("now", TimeUtil.getTime().toString())
                    .usingJobData("money", 1000f)
                    .build();

            JobDetail mergeInjectJob = JobBuilder.newJob(MergeInjectJob.class)
                    .withIdentity("job_merge_inject", "group1")
                    .usingJobData("now", TimeUtil.getTime().toString())
                    .usingJobData("money", 1000f)
                    .build();


            SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder
                    .simpleSchedule()
                    .withIntervalInSeconds(4)
                    .repeatForever();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow().withSchedule(simpleSchedule)
                    .build();


            HolidayCalendar cal = new HolidayCalendar();
            cal.addExcludedDate(new Date());



            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //scheduler.scheduleJob(job, trigger);
//            scheduler.scheduleJob(mergeJob, trigger);
            // scheduler.scheduleJob(mergeInjectJob, trigger);


            scheduler.addCalendar("myHolidays", cal, false, true);

            Trigger t1 = TriggerBuilder.newTrigger()
                    .forJob(mergeInjectJob)
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(10, 48))
                    .modifiedByCalendar("myHolidays")
                    .build();

            Trigger t2 = TriggerBuilder.newTrigger()
                    .forJob(mergeInjectJob)
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(10, 49))
                    .modifiedByCalendar("myHolidays")
                    .build();

            Set<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(t1);
            triggerSet.add(t2);

            scheduler.scheduleJob(mergeInjectJob, triggerSet, false);


            scheduler.start();


            try {
                TimeUnit.SECONDS.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            scheduler.shutdown();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}