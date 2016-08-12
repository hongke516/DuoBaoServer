package junit;

import com.fozoto.duobao.action.GoodsAction;
import com.fozoto.duobao.core.util.TimeUtil;
import com.fozoto.duobao.model.Detail;
import com.fozoto.duobao.model.Goods;
import com.fozoto.duobao.model.Issue;
import com.fozoto.duobao.model.Shape;
import com.fozoto.duobao.service.IDetailService;
import com.fozoto.duobao.service.IGoodsService;
import com.fozoto.duobao.service.IIssueService;
import com.fozoto.duobao.service.IShapeService;
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
 * Created by qingyan on 16-7-31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/junit/applicationContext.xml")
@Transactional //如果要更新数据库，需要注释这个@Transactional
@Rollback(false)    //是否改变数据库数据:false改变数据库数据;true回滚数据,只生成SQL语句,对数据库无影响
public class GoodsTest {
    private Logger log = Logger.getLogger(GoodsTest.class);

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IShapeService shapeService;

    @Autowired
    private IDetailService detailService;

    @Autowired
    private IIssueService issueService;

    @Test
    public void testAddAllCateGoods() {
        String[] cates = {"优选商品", "手机平板", "电脑办公", "数码影音", "女性时尚", "美食天地", "潮流新品", "网易周边", "其他商品"};
        for (String cate : cates) {
            addGoodsByCate(cate);
        }
    }

    private void addGoodsByCate(String cate) {
        Goods goods = new Goods();
        goods.setImage("https://onegoods.nosdn.127.net/goods/2351/86476111f1d4211c3fdf21e2c92fb4d0.jpg");
        goods.setIntro("奥迪 A3 2016款 Limousine 35 TFSI 领英型");
        goods.setRemind("本商品由北京兴奥晟通提供并发货，颜色随机，含保险，不含购置税及上牌费。参与前请仔细阅读活动说明！");
        goods.setAvailable("新品");
        goods.setCate(cate);
        goods.setExplains("重要说明：\n" +
                "1、本商品由第三方提供；\n" +
                "2、以上详情页面展示信息仅供参考，商品以实物为准；\n" +
                "3、如快递无法配送至获得者提供的送货地址，将默认配送到距离最近的送货地，由获得者本人自提。");
        goods.setPrice(280000);
        goods.setTotal(28000);
        goods.setPer(10);
        goods.setTrait("http://mimg.127.net/p/one/web/lib/img/common/icon/icon_tens_goods.png?render_my=1");
        goods.setTime(TimeUtil.getTime().toString());
        goods.setRetime(TimeUtil.getTime().toString());

        try {
            goodsService.add(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] shapeImages = {"https://onegoods.nosdn.127.net/goods/2351/86476111f1d4211c3fdf21e2c92fb4d0.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/c221b9ffe2eff3ee6135a4eb0457410b.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/4fe650cacb9669cd86eda3f0e3763345.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/025a4e32c7db63be547ca2c2a31299e0.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/2f787b425601e51a3f5a2b295e562ef3.jpg"
        };

        for (String shapeImage : shapeImages) {
            Shape shape = new Shape();
            shape.setImage(shapeImage);
            shape.setGoods(goods);
            try {
                shapeService.add(shape);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String[] detailImages = {
                "https://onegoods.nosdn.127.net/goods/2351/af37c62504227f3c0fb05e8cec8ea1d5.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/1d79055974490cda84dced85d7c044b7.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/39a1a8cf6748e91ed1b0da7a3ae8a5b9.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/55f9de6eccab9becaf2c9007e5d3cff9.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/f531655eee2c0770a251e7d66e4fab29.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/caa39ebdf521071791e340d3c9e4a90f.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/9d4baf6d889bb1b182dd0a92c1b0ab25.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/4580274e67c8c5cd3a8606510f842ddd.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/39df2590863fb390f2ee9d5fedc4b216.jpg",
                "https://onegoods.nosdn.127.net/goods/2351/0249622b05b5a1d2fd6d73e62099d49b.jpg"
        };

        for (String detailImage : detailImages ) {
            Detail detail = new Detail();
            detail.setImage(detailImage);
            detail.setGoods(goods);
            try {
                detailService.add(detail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 商品是新品, 新的一期就开始了.
        Issue issue = new Issue();
        issue.setGoods(goods);
        issue.setOver("false");
        issue.setDone(100);
        issue.setStart(TimeUtil.getTime().toString());

        try {
            issueService.add(issue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("测试通过");
    }

    @Test
    public void testGet() {
        Goods goods = goodsService.get(Goods.class, 2);
        if (goods != null) {
            System.out.println(goods.toString());
        } else {
            System.out.println("goods为null");
        }
    }

    @Test
    public void testDelete() {
        boolean flag = goodsService.delete(Goods.class, 1);
        if (flag) {
            System.out.println("删除成功!");
        } else {
            System.out.println("删除失败!");
        }
    }
}
