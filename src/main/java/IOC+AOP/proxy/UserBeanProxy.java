package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import aop.UserBeanImpl;

public class UserBeanProxy implements InvocationHandler {
	private Object targetObject;

	public UserBeanProxy(Object targetObject) {
		this.targetObject = targetObject;
	}

	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		System.out.println("targetObject:" + targetObject);
		UserBeanImpl userBean = (UserBeanImpl) targetObject;
		System.out.println("method:" + method);
		String userName = userBean.getUserName();
		Object result = null;

		// х╗очеп╤о
		if (userName != null && !"".equals(userName)) {
			result = method.invoke(targetObject, params);
		}

		return result;
	}

}
