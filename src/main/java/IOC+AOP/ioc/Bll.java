package ioc;

/**
 * IAdd�ӿڵ�ʵ�֣�����ҵ����
 */
public class Bll implements IAdd {

	public void Add(String a, String b) {
		System.out.println("����ִ���ַ�������");
		System.out.println(a + b);
	}

	public void Add(int a, int b) {
		System.out.println("����ִ���������");
		System.out.println(a + b);
	}

	public void Add(float a, float b) {
		System.out.println("����ִ�и������");
		System.out.println(a + b);
	}
}
