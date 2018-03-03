package ioc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ��������ע�룬���ⲿע��ʵ���� ��ǰ��ΪӦ����
 */
public class Application {
	private IAdd _IAdd = null;

	public Application(IAdd IAdd) {
		this._IAdd = IAdd;
	}

	public void Run() {
		float first = 0, second = 0;

		System.out.println("�������һ������");

		try {
			BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
			first = Cast2Float(strin.readLine());
		} catch (Exception e) {
			System.out.println("����������������������,�����˳���");
			return;
		}

		System.out.println("������ڶ������֣�");

		try {
			BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
			second = Cast2Float(strin.readLine());
		} catch (Exception e) {
			System.out.println("����������������������,�����˳�");
			return;
		}

		// �����ַ������Ӻ���
		_IAdd.Add(String.valueOf(first), String.valueOf(second));

		// ����������ͺ���
		_IAdd.Add((int) first, (int) second);

		// ���ø�����ͺ���
		_IAdd.Add(first, second);

	}

	// ���������У��ת�������ת���쳣�׳�����һ��
	private float Cast2Float(String input) throws Exception {
		if (input.length() == 0) {
			throw new Exception();
		}
		return Float.parseFloat(input);
	}
}
