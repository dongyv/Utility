package IOCAOP.cglib;

public class ProxyExe {
	public static void main(String[] args) {
		System.out.println(".............CGLIB Proxy....................");
		System.out.println("Proved....................");
		CGLibProxy cproxy = new CGLibProxy();
		ClientBean clientBean = (ClientBean) cproxy.createProxyObject(new ClientBean("Bob Liang"));
		clientBean.addClient();

		System.out.println("NO Proved....................");
		cproxy = new CGLibProxy();
		clientBean = (ClientBean) cproxy.createProxyObject(new ClientBean());
		clientBean.addClient();

	}

}
