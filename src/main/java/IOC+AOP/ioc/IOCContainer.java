package ioc;

/**
 * IOC������ΪӦ����Applicationע��ҵ��ӿ�ʵ��
 */
public class IOCContainer {
	public static void main(String[] args) {
		// ʵ����IAdd�ӿڵ�ʵ��
		Bll bll = new Bll();
		// ΪӦ����ע��ʵ��
		Application app = new Application(bll);
		// ����Ӧ����
		app.Run();
	}
}
