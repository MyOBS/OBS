package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.struggle.obs.bbs.service.CreditRuleService;

@SuppressWarnings("unused")
public class ServiceTest {
	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	@Test
	/**  勿删:重置帖子数据 1*/
	public void resetTopicData() {
		// TopicService service = (TopicService) ac.getBean("topicServiceImpl");
		// service.resetTopicData();
	}

	@Test
	/** 勿删：重置版块数据 2*/
	public void resetForumData() {
		// ForumService service = (ForumService) ac.getBean("forumServiceImpl");
		// service.resetForumData();
	}

	@Test
	/**  勿删:重置帖子数据 3*/
	public void resetTypeData() {
//		TypeService service = (TypeService) ac.getBean("typeServiceImpl");
//		service.resetTypeData();
	}
	
	/**  勿删:初始化常量表 "评分理由" 数据4 */
	@Test
	public void initConstsData(){
//		ConstsService service = (ConstsService) ac.getBean("constsServiceImpl");
//		Consts c1 = new Consts("score_reaon","1","很给力！","评分理由",1);
//		Consts c2 = new Consts("score_reaon","2","神马都是浮云","评分理由",2);
//		Consts c3 = new Consts("score_reaon","3","赞一个！","评分理由",3);
//		Consts c4 = new Consts("score_reaon","4","山寨","评分理由",4);
//		Consts c5 = new Consts("score_reaon","5","蛋定","评分理由",5);
//		service.save(c1);
//		service.save(c2);
//		service.save(c3);
//		service.save(c4);
//		service.save(c5);
		
//		Consts c6 = new Consts(ConstantDefine.EDU_EDUCATION,"1","博士","学历",1);
//		Consts c7 = new Consts(ConstantDefine.EDU_EDUCATION,"2","硕士","学历",2);
//		Consts c8 = new Consts(ConstantDefine.EDU_EDUCATION,"3","本科","学历",3);
//		Consts c9 = new Consts(ConstantDefine.EDU_EDUCATION,"4","专科","学历",4);
//		Consts c10 = new Consts(ConstantDefine.EDU_EDUCATION,"5","高中","学历",5);
//		Consts c11 = new Consts(ConstantDefine.EDU_EDUCATION,"6","其他","学历",6);
//		service.save(c6);
//		service.save(c7);
//		service.save(c8);
//		service.save(c9);
//		service.save(c10);
		
//		Consts c12 = new Consts(ConstantDefine.EDU_DIRECTION,"1","Java","技术方向",1);
//		Consts c13 = new Consts(ConstantDefine.EDU_DIRECTION,"2","C/C++","技术方向",2);
//		Consts c14 = new Consts(ConstantDefine.EDU_DIRECTION,"3",".Net","技术方向",3);
//		Consts c15 = new Consts(ConstantDefine.EDU_DIRECTION,"4","PHP","技术方向",4);
//		Consts c16 = new Consts(ConstantDefine.EDU_DIRECTION,"5","IOS","技术方向",5);
//		Consts c17 = new Consts(ConstantDefine.EDU_DIRECTION,"6","网页平面","技术方向",6);
//		Consts c18 = new Consts(ConstantDefine.EDU_DIRECTION,"7","其他","技术方向",7);
//		service.save(c12);
//		service.save(c13);
//		service.save(c14);
//		service.save(c15);
//		service.save(c16);
//		service.save(c17);
//		service.save(c18);
	}
	
	/**  勿删:初始化菜单列表 5 */
	@Test
	public void initMenuData(){
//		MenuService service = (MenuService) ac.getBean("menuServiceImpl");
//		service.initMenuData();
	}
	/**  勿删:初始化积分规则表 6 */
	@Test
	public void initCreditRule(){
		CreditRuleService service = (CreditRuleService) ac.getBean("creditRuleServiceImpl");
//		CreditRule c1 = new CreditRule();
//		c1.setActionName("签到");//动作名称
//		c1.setMoney(0L);//金钱
//		c1.setExperience(2L);//经验
//		c1.setGold(0L);//金币
//		c1.setCampaignContribution(0L);//宣传贡献
//		c1.setFrozenGold(0L);//冻结金币
//		c1.setSort(1);//排序
//		c1.setSystemReward(true);//是否系统值
//		c1.setCycleCount("1");//周期内奖励次数
//		c1.setCycleRangeCode("1");//周期范围编码
//		c1.setCycleRangeName("每天");//周期范围名称
//		service.save(c1);
		
//		CreditRule c2 = new CreditRule();
//		c2.setActionName("参与投票");//动作名称
//		c2.setMoney(1L);//金钱
//		c2.setExperience(1L);//经验
//		c2.setSort(2);//排序
//		c2.setSystemReward(true);//是否系统值
//		c2.setCycleCount("10");//周期内奖励次数
//		c2.setCycleRangeCode("1");//周期范围编码
//		c2.setCycleRangeName("每天");//周期范围名称
//		service.save(c2);
		
//		CreditRule c3 = new CreditRule("每天登录","1","每天","1",true, 3);
//		c3.setMoney(5L);//金钱
//		c3.setExperience(0L);//经验
//		c3.setGold(0L);//金币
//		c3.setCampaignContribution(0L);//宣传贡献
//		c3.setFrozenGold(0L);//冻结金币
//		service.save(c3);
//		CreditRule c4 = new CreditRule("设置头像","2","一次性","1",true, 4);
//		c4.setMoney(50L);//金钱
//		c4.setExperience(5L);//经验
//		c4.setGold(1L);//金币
//		c4.setCampaignContribution(0L);//宣传贡献
//		c4.setFrozenGold(0L);//冻结金币
//		service.save(c4);
//		CreditRule c5 = new CreditRule("邮箱验证","2","一次性","1",true, 5);
//		c5.setMoney(5L);//金钱
//		c5.setExperience(5L);//经验
//		c5.setGold(5L);//金币
//		c5.setCampaignContribution(0L);//宣传贡献
//		c5.setFrozenGold(0L);//冻结金币
//		service.save(c5);
//		CreditRule c6 = new CreditRule("注册推广","3","不限周期","max",true, 6);
//		c6.setMoney(0L);//金钱
//		c6.setExperience(0L);//经验
//		c6.setGold(0L);//金币
//		c6.setCampaignContribution(3L);//宣传贡献
//		c6.setFrozenGold(5L);//冻结金币
//		service.save(c6);
//		CreditRule c7 = new CreditRule("访问推广","3","不限周期","max",true, 7);
//		c7.setMoney(0L);//金钱
//		c7.setExperience(0L);//经验
//		c7.setGold(0L);//金币
//		c7.setCampaignContribution(1L);//宣传贡献
//		c7.setFrozenGold(0L);//冻结金币
//		service.save(c7);
//		CreditRule c8 = new CreditRule("上传附件","3","不限周期","max",true, 8);
//		c8.setMoney(0L);//金钱
//		c8.setExperience(1L);//经验
//		c8.setGold(0L);//金币
//		c8.setCampaignContribution(0L);//宣传贡献
//		c8.setFrozenGold(0L);//冻结金币
//		service.save(c8);
//		CreditRule c9 = new CreditRule("加精华","3","不限周期","max",true, 9);
//		c9.setMoney(50L);//金钱
//		c9.setExperience(50L);//经验
//		c9.setGold(5L);//金币
//		c9.setCampaignContribution(0L);//宣传贡献
//		c9.setFrozenGold(0L);//冻结金币
//		service.save(c9);
//		CreditRule c10 = new CreditRule("发表回复","3","不限周期","max",true,10);
//		c10.setMoney(1L);//金钱
//		c10.setExperience(1L);//经验
//		c10.setGold(0L);//金币
//		c10.setCampaignContribution(0L);//宣传贡献
//		c10.setFrozenGold(0L);//冻结金币
//		service.save(c10);
//		CreditRule c11 = new CreditRule("发表帖子","3","不限周期","max",true, 11);
//		c11.setMoney(10L);//金钱
//		c11.setExperience(5L);//经验
//		c11.setGold(0L);//金币
//		c11.setCampaignContribution(0L);//宣传贡献
//		c11.setFrozenGold(0L);//冻结金币
//		service.save(c11);
		
//		CreditRule c11 = new CreditRule("悬赏主题","3","不限周期","max",false, 12);
//		service.save(c11);
//		CreditRule c12 = new CreditRule("最佳答案","3","不限周期","max",false, 13);
//		service.save(c12);
//		CreditRule c13 = new CreditRule("帖子被评分","3","不限周期","max",false, 14);
//		service.save(c13);
//		CreditRule c14 = new CreditRule("任务奖励","3","不限周期","max",false, 15);
//		service.save(c14);
//		CreditRule c15 = new CreditRule("道具随机获取","3","不限周期","max",false, 16);
//		service.save(c15);
//		CreditRule c16 = new CreditRule("购买道具","3","不限周期","max",false, 17);
//		service.save(c16);
//		CreditRule c17 = new CreditRule("转账接收","3","不限周期","max",false, 18);
//		service.save(c17);
//		CreditRule c18 = new CreditRule("转账转出","3","不限周期","max",false, 19);
//		service.save(c18);
//		CreditRule c19 = new CreditRule("积分兑换","3","不限周期","max",false, 20);
//		service.save(c19);
//		CreditRule c20 = new CreditRule("出售附件","3","不限周期","max",false, 21);
//		service.save(c20);
//		CreditRule c21 = new CreditRule("出售主题","3","不限周期","max",false, 22);
//		service.save(c21);
//		CreditRule c22 = new CreditRule("购买主题","3","不限周期","max",false, 23);
//		service.save(c22);
//		CreditRule c23 = new CreditRule("购买积分","3","不限周期","max",false, 24);
//		service.save(c23);
//		CreditRule c24 = new CreditRule("购买角色","3","不限周期","max",false, 25);
//		service.save(c24);
//		CreditRule c25 = new CreditRule("举报奖惩","3","不限周期","max",false, 26);
//		service.save(c25);
//		CreditRule c26 = new CreditRule("参与活动","3","不限周期","max",false, 27);
//		service.save(c26);
//		CreditRule c27 = new CreditRule("回帖奖励","3","不限周期","max",false, 28);
//		service.save(c26);
//		CreditRule c28 = new CreditRule("回帖中奖","3","不限周期","max",false, 29);
//		service.save(c28);
//		CreditRule c29 = new CreditRule("获得红包","3","不限周期","max",false, 30);
//		service.save(c29);
//		CreditRule c30 = new CreditRule("回收红包","3","不限周期","max",false, 31);
//		service.save(c30);
//		CreditRule c31 = new CreditRule("购买勋章","3","不限周期","max",false, 32);
//		service.save(c31);
	}
	

	/**
	 * 2014年8月19日 下午5:16:27
	 */
	@Test
	public void getMaxPosition() {
		// TopicService service = (TopicService) ac.getBean("topicServiceImpl");
		// TopicFrom topicFrom = new TopicFrom();
		// topicFrom.setDigest(4);
		// topicFrom.setOrderBy("newest");
		// topicFrom.setThemeId(1L);
		// topicFrom.setTypeId(1L);
		// topicFrom.setStartDate(new Date(100, 5, 5));
		// topicFrom.setEndDate(new Date(300, 5, 5));
		//
		// Page<Topic> page = service.getPageList(1, 5, 5, topicFrom);
		// System.out.println(page.getResultList());

	}

	@Test
	public void tets2() {
		// ReplyService service = (ReplyService) ac.getBean("replyServiceImpl");
		// Reply reply = new Reply();
		// reply.setContent("你说的是真的吗");
		//
		// ReplyFrom replyFrom = new ReplyFrom();
		// replyFrom.setIp("192.168.10.11");
		// replyFrom.setTopicId(5L);
		// replyFrom.setUserId(4L);
		//
		// service.save(reply, replyFrom);
	}

	@Test
	public void tet333() {
		// ReplyService service = (ReplyService) ac.getBean("replyServiceImpl");
		// Reply r = new Reply();
		// r.setId(9L);
		// service.delete(r);
	}

	@Test
	public void tet3t() {
		// ReplyService service = (ReplyService) ac.getBean("replyServiceImpl");
		// ReplyFrom replyFrom = new ReplyFrom();
		// replyFrom.setTopicId(1L);
		// replyFrom.setUserId(2L);
		// replyFrom.setAsc(false);
		// Page<Reply> page = service.getPageList(1, 5, 5, replyFrom);
		//
		// for(Reply r : page.getResultList()){
		// System.out.println(r.getId());
		// }
		//
	}

	@Test
	public void tet3t22() {
//		CreditRuleService service = (CreditRuleService) ac.getBean("creditRuleServiceImpl");
//		service.findSystemReward();
	}
}
