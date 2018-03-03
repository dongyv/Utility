package ioc;

/**
 * IOC容器，为应用类Application注入业务接口实现
 */
public class IOCContainer {
	public static void main(String[] args) {
		// 实例化IAdd接口的实现
		Bll bll = new Bll();
		// 为应用类注入实现
		Application app = new Application(bll);
		// 调用应用类
		app.Run();
	}
}
