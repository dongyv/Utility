package IOCAOP.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy; 
/**
 * ������
 * ����һ�����������ڵ���Ŀ�귽��ʱ��CGLib��ص�MethodInterceptor�ӿڷ������أ���ʵ�����Լ��Ĵ����߼���������JDK�е�InvocationHandler�ӿڡ�
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
     * ��д���������ڷ���ǰ�ͷ��������ҵ�� 
     * Object objΪĿ����� 
     * Method methodΪĿ�귽�� 
     * Object[] params Ϊ������ 
     * MethodProxy proxy CGlib����������� 
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
