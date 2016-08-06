package junit;

import org.quartz.*;

/**
 * Created by qingyan on 16-7-30.
 */
public class MergeInjectJob implements Job {

    private String now;
    private float money;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        System.err.println("Instance "+key+" of MergeInjectJob says: "+"我在"+now+"赚了"+money+"人民币");
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setNow(String now) {
        this.now = now;
    }
}
