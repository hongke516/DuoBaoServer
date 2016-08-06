package junit;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qingyan on 16-7-30.
 */
public class QuartzTest2 {

    public static void main(String[] args) {
        JobDetail job = JobBuilder.newJob(TestJob.class)
                .withIdentity("job_test", "group1")
                .build();

        Trigger tenTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger_ten", "group1")
                .startAt(DateBuilder.dateOf(10, 1, 40))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(10)
                        .repeatForever())
                .endAt(DateBuilder.dateOf(22, 1, 40))
                .build();

        Trigger fiveTrigger1 = TriggerBuilder.newTrigger()
                .withIdentity("trigger_five_1", "group1")
                .startAt(DateBuilder.dateOf(22, 6, 40))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(5)
                        .repeatForever())
                .endAt(DateBuilder.dateOf(23, 56, 40))
                .build();

        Trigger fiveTrigger2 = TriggerBuilder.newTrigger()
                .withIdentity("trigger_five_2", "group1")
                .startAt(DateBuilder.dateOf(0, 1, 40))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(5)
                        .repeatForever())
                .endAt(DateBuilder.dateOf(1, 56, 40))
                .build();

        Set<Trigger> triggers = new HashSet<>();
        triggers.add(tenTrigger);
        triggers.add(fiveTrigger1);
        triggers.add(fiveTrigger2);

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(job, triggers, true);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
