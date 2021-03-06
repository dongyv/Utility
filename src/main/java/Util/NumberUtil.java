package Util;

import java.util.Random;

public final class NumberUtil {

    private NumberUtil() {}

    public static String getFixLengthString(int strLength) {
        Random rm = new Random();
        // 获取随机数
        double pross = rm.nextDouble();
        // 将获取的随机数转换为字符串
        String fixLengthString = String.valueOf(pross);
        // 返回固定长度的随机数
        return fixLengthString.substring(2, strLength + 2);
    }

    /**
   	 * 生成指定长度的随机字母加数字字符串.
   	 * 
   	 * @param length 长度
   	 * @return 随机字母数字字符串
   	 */
   	public static String randomFixedChar(int length) {
   		StringBuilder sb = new StringBuilder();
   		Random rand = new Random();
   		int data = 0;
   		for (int i = 0; i < length; i++) {
   			int index = rand.nextInt(3);
   			switch (index) {
   			case 0:
   				data = rand.nextInt(10);
   				sb.append(data);
   				break;
   			case 1:
   				data = rand.nextInt(26) + 64;
   				sb.append((char) data);
   				break;
   			case 2:
   				data = rand.nextInt(26) + 97;
   				sb.append((char) data);
   				break;
   			}
   		}

   		return sb.toString();
   	}
   	
}