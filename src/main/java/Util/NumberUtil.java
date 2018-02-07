package Util;

import java.util.Random;

public final class NumberUtil {

    private NumberUtil() {}

    public static String getFixLengthString(int strLength) {
        Random rm = new Random();
        // ��ȡ�����
        double pross = rm.nextDouble();
        // ����ȡ�������ת��Ϊ�ַ���
        String fixLengthString = String.valueOf(pross);
        // ���ع̶����ȵ������
        return fixLengthString.substring(2, strLength + 2);
    }

    /**
   	 * ����ָ�����ȵ������ĸ�������ַ���.
   	 * 
   	 * @param length ����
   	 * @return �����ĸ�����ַ���
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