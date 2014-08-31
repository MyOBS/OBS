package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.struggle.obs.bbs.service.ConstsService;
import com.struggle.obs.bean.base.Consts;

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

}
