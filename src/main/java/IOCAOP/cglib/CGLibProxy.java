package IOCAOP.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy; 
/**
 * 拦截器
 * 定义一个拦截器。在调用目标方法时，CGLib会回调MethodInterceptor接口方法拦截，来实现你自己的代理逻辑，类似于JDK中的InvocationHandler接口。
 * @author asus1
 *
 */
public class CGLibProxy implements MethodInterceptor {
	private Object targetObject;

	
	public Object createProxyObject(Object targetObject) {
		this.targetObject = targetObject;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.targetObject.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}
	/** 
     * 重写方法拦截在方法前和方法后加入业务 
     * Object obj为目标对象 
     * Method method为目标方法 
     * Object[] params 为参数， 
     * MethodProxy proxy CGlib方法代理对象 
     */ 
	public Object intercept(Object proxy, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
		ClientBean clientBean = (ClientBean) targetObject;
		String userName = clientBean.getClientName();
		Object result = null;

		if (userName != null && !"".equals(userName)) {
			result = method.invoke(targetObject, params);
		}
		return result;
	}
}
