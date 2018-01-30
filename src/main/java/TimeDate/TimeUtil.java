package TimeDate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	/**
	 * ����ָ�����ھ�������
	 * @param times ָ������
	 * @return ��
	 */
	public static int getTime(String times){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(new Date());
		String t1 = time.replace('-','/'); 
		String t2 = times.replace('-','/'); 

		@SuppressWarnings("deprecation")
		Date dt1= new Date(t1); 
		@SuppressWarnings("deprecation")
		Date dt2= new Date(t2); 
		long i= (dt1.getTime() - dt2.getTime())/(1000*60*60*24); 
		return (int) (i/365);
	}
	
	/**
	 * ���ص�ǰʱ��
	 * @return
	 */
	public static String getNowTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(new Date());
		return time;
	}
	
	/**
	 * ���ص�ǰʱ��
	 * @return
	 */
	public static String getNowTime(String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		String time=sdf.format(new Date());
		return time;
	}
}
