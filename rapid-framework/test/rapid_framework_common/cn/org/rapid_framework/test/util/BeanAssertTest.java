package cn.org.rapid_framework.test.util;

import cn.org.rapid_framework.jdbc.sqlgenerator.metadata.TestBean;
import junit.framework.TestCase;

public class BeanAssertTest extends TestCase {
	
	public void test1() {
		TestBean b = new TestBean();
		try {
		BeanAssert.assertPropertiesNotNull(b);
		fail();
		}catch(Error e) {
			e.getMessage().contains("AssertionFailedError, [TestBean.userName] must be not null");
		}
	}

	public void test2() {
		TestBean b = new TestBean();
		BeanDefaultValueUtils.setBeanProperties(b);
		BeanAssert.assertPropertiesNotNull(b);
	}

	public void test3() {
		TestBean b = new TestBean();
		b.setAge(1);
		b.setPassword("pwd");
		b.setUserName("usr");
		BeanAssert.assertPropertiesNotNull(b);
	}

	public void test3_ignore_properties() {
		TestBean b = new TestBean();
		b.setAge(1);
		BeanAssert.assertPropertiesNotNull(b,new String[]{"password","userName"});
		b.setUserName("usr");
		BeanAssert.assertPropertiesNotNull(b,new String[]{"password"});
		
		try {
			BeanAssert.assertPropertiesNotNull(b);
			fail();
		}catch(Error e) {
			assertTrue(e.getMessage().contains("[TestBean.password] must be not null"));
		}
	}
	
}