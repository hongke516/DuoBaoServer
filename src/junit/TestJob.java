package junit;

import com.fozoto.duobao.core.util.TimeUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

/**
 * Created by qingyan on 16-7-30.
 */
public class TestJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();

        System.err.println("Instance "+key+" of TestJob says: "+ TimeUtil.getTime().toString());
    }
}
