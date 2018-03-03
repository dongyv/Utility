package proxy;

import java.lang.reflect.Proxy;

import aop.UserBean;
import aop.UserBeanImpl;

public class ProxyExe {

	public static void main(String[] args) {
		System.out.println("Proved.............");
		UserBeanImpl targetObject = new UserBeanImpl("Bob Liang");
		UserBeanProxy proxy = new UserBeanProxy(targetObject);
		// ���ɴ������
		UserBean object = (UserBean) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
				targetObject.getClass().getInterfaces(), proxy);
		object.addUser();

		System.out.println("NO Proved.............");
		targetObject = new UserBeanImpl();
		proxy = new UserBeanProxy(targetObject);
		// ���ɴ������
		object = (UserBean) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
				targetObject.getClass().getInterfaces(), proxy);
		System.out.println("object:"+object);
		object.addUser();

	}

}
