package junit;

import org.apache.log4j.Logger;
import org.quartz.*;

/**
 * Created by qingyan on 16-7-29.
 */
public class HelloJob implements Job{
    private Logger log = Logger.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("测试Quartz");
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String time = jobDataMap.getString("now");
        float money = jobDataMap.getFloat("money");
        log.debug("Instance "+jobKey+" of HelloJob says: "+"我在"+time+"赚了"+money+"人民币");
        System.err.println("Instance "+jobKey+" of HelloJob says: "+"我在"+time+"赚了"+money+"人民币");
    }
}
