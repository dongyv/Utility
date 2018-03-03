package ioc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 构造依赖注入，从外部注入实现类 当前类为应用类
 */
public class Application {
	private IAdd _IAdd = null;

	public Application(IAdd IAdd) {
		this._IAdd = IAdd;
	}

	public void Run() {
		float first = 0, second = 0;

		System.out.println("请输入第一个数字");

		try {
			BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
			first = Cast2Float(strin.readLine());
		} catch (Exception e) {
			System.out.println("您的输入有误，请输入数字,程序退出！");
			return;
		}

		System.out.println("请输入第二个数字：");

		try {
			BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
			second = Cast2Float(strin.readLine());
		} catch (Exception e) {
			System.out.println("您的输入有误，请输入数字,程序退出");
			return;
		}

		// 调用字符串连接函数
		_IAdd.Add(String.valueOf(first), String.valueOf(second));

		// 调用整形求和函数
		_IAdd.Add((int) first, (int) second);

		// 调用浮点求和函数
		_IAdd.Add(first, second);

	}

	// 对输入进行校验转换，如果转换异常抛出到上一层
	private float Cast2Float(String input) throws Exception {
		if (input.length() == 0) {
			throw new Exception();
		}
		return Float.parseFloat(input);
	}
}
