package junit;

import com.fozoto.duobao.model.Issue;
import com.fozoto.duobao.service.IIssueService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by qingyan on 16-8-2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/junit/applicationContext.xml")
@Transactional //如果要更新数据库，需要注释这个@Transactional
@Rollback(true)    //是否改变数据库数据:false改变数据库数据;true回滚数据,只生成SQL语句,对数据库无影响
public class IssueTest {
    private final static Logger log = Logger.getLogger(IssueTest.class);

    @Autowired
    private IIssueService issueService;

    @Test
    public void testInfo() {
        Issue issue = issueService.getByGoods(Issue.class, 2, 1);
        if (issue!=null) {
            System.out.println(issue.getFinish());
            System.out.println(issue.toString());
        } else {
            System.out.println("issue为null");
        }

    }
}
