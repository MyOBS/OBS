package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("unused")
public class SpringTest {
	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	@Test
	public void testSpring() {
		try {
//			UserService s = (UserService) ac.getBean("userServiceImpl");
//			System.out.println(s.getUserById(1L));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
