package junit;

import org.quartz.*;

/**
 * Created by qingyan on 16-7-30.
 */
public class MergeJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("测试Quartz");
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
//        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String time = dataMap.getString("now");
        float money = dataMap.getFloat("money");
        System.err.println("Instance "+jobKey+" of MergeJob says: "+"我在"+time+"赚了"+money+"人民币");
    }
}
