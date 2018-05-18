package Util;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.aiqianqian.common.bean.RiskRecord;
import net.aiqianqian.common.bean.RiskResponse;
import net.aiqianqian.common.bean.Statistics;
import net.aiqianqian.common.utils.Utils;

public class ExeclUtil {
	public static void writeExcel(HttpServletResponse response)
			throws RowsExceededException, WriteException, IOException {
		OutputStream os = response.getOutputStream();// ȡ�������
		response.reset();// ��������
		response.setHeader("Content-disposition", "attachment; filename=testRed.xls");// �趨����ļ�ͷ
		response.setContentType("application/msexcel");// �����������

		WritableWorkbook wbook = Workbook.createWorkbook(os); // ����excel�ļ�
		String tmptitle = "��������"; // ����
		WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet����

		// ����excel����
		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfFC = new WritableCellFormat(wfont);
		wcfFC.setBackground(Colour.AQUA);
		wsheet.addCell(new Label(1, 0, tmptitle, wcfFC));
		wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		wcfFC = new WritableCellFormat(wfont);

		// ��ʼ������������
		wsheet.addCell(new Label(0, 2, "����"));
		wsheet.addCell(new Label(1, 2, "����"));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Map<String, String> map = new HashMap<String, String>();
		map.put("Red1", "it_red@sina.com");
		map.put("Red2", "it_red@sohu.com");
		map.put("Red3", "it_red@163.com");
		int count = 0;
		for (String key : map.keySet()) {
			wsheet.addCell(new Label(0, count + 3, key));
			wsheet.addCell(new Label(1, count + 3, map.get(key)));
			count++;
		}

		// �����������ɽ���
		wbook.write(); // д���ļ�
		wbook.close();
		os.close(); // �ر���
	}

	/**
	 * ����Excel�ļ�  ��̬����ÿ��ֵ ��ʵ��ͨ��
	 */
	public static void createExcel(Class<T> clazz,List<Map<String,Object>> datas){
		
	}
	
	static String[] title = { "�û����", "�������", "�û��ڸñʶ���ǰ���¿����", "���û��ڸñʶ���ǰ�����������","����״̬ ","����ʱ��","����ˮƽ","����״��","��Ů����"
			,"ְҵ","��ַ��Ϣ","��ϵ��ϵ1","��ϵ��ϵ1�ֻ���","��ϵ��ϵ2","��ϵ��ϵ�ֻ���","�ֻ��豸","ע�������ʱ����","ע��ʱ��������֮���¼����","��������"
			,"��н����","����ע�����񼸸���","��ǰ���","ÿ��ƽ������","��ͨ�绰�ĺ�������","ҹ���ٷֱ�","�൱ǰ��1�����д���","�൱ǰ��2�����д���","�൱ǰ��3�����д���"
			,"�൱ǰ��4�����д���","�൱ǰ��5�����д���","�൱ǰ��6�����д���","�൱ǰ��7�����д���","�൱ǰ��8�����д���","�൱ǰ��1������ʱ��","�൱ǰ��2������ʱ��","�൱ǰ��3������ʱ��","�൱ǰ��4������ʱ��"
			,"�൱ǰ��5������ʱ��","�൱ǰ��6������ʱ��","�൱ǰ��7������ʱ��","�൱ǰ��8������ʱ��","����Ȧ������ϵռ����","p2pͳ�� ","���պ���ͳ��","�������ͳ��","�Ĳ�����ͳ��"
			,"���ֺ���ͳ��","���к���ͳ��","���","�������"};
	static final int init_length = 50;
	static String[] files = {"userId","borrowInfoId","repayNumber","loanNumber","repayStatus","applyTime","education","marriage","child",
			"positonName","companyAddress","cognateRelation","cognateMobile","socialRelation","socialMobile","regType","timeInfo","loginNumber","workingTime"
			,"payDate","regtime","curBala","avgCon","callCnt","nightActivity","m1Num","m2Num","m3Num"
			,"m4Num","m5Num","m6Num","m7Num","m8Num","m1Secd","m2Secd","m3Secd","m4Secd"
			,"m5Secd","m6Secd","m7Secd","m8Secd","pyq","p2pCount","csCount","dkCount","dbCount"
			,"txCount","yhCount","riskResponse","riskRecord"};
	
	/**
	 * ����Excel�ļ�
	 */
	public static void createExcel(List<Map<String,Object>> lists) {
		/* Method[] methods = sts.getClass().getMethods();
	        for (int i = 0; i < methods.length; i++) {
	            Method method = methods[i];
	            if (method.getName().startsWith("get")) {
	                System.out.print("���е�get����:" + method.getName() + "\t");
//	                System.out.println("get������ֵ:" + method.invoke());
	            }
	        }*/
//		int length = clazz.getDeclaredFields().length;//ʵ�����е�����
//		int fileslength = Statistics.class.getDeclaredFields().length;//ʵ�����е�����
		int length = 50;
		int length1 = 97;
		int m=0,n=0;
		// ����������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ����һ��������sheet
		HSSFSheet sheet = workbook.createSheet();
		// ���������һ��
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = null;
		// �����һ������id��name��sex
//		for (int i = 0; i < title.length; i++) {
//			cell = row.createCell(i);
//			cell.setCellValue(title[i]);
//
//		}
		// ׷������
		for (int i = 1; i < lists.size()+1; i++) {
			Map<String,Object> riskMap = new HashMap<>();
			Map<String,Object> recordMap = new HashMap<>();
			Map<String,Object> sts = lists.get(i-1);
			HSSFRow nextrow = sheet.createRow(i);
			HSSFCell cell2 = null;
			for(int j=0;j<title.length;j++){
				cell2 = nextrow.createCell(j);
				Object value = sts.get(files[j]);
				if(j>=50&&j<97){
					Object o = riskMap.get(title[j]);
					getDataObject(o,cell2);
				}
				if(j>=97){
					Object o = recordMap.get(title[j]);
					getDataObject(o,cell2);
				}
				getDataObject(value,cell2);
				if(value instanceof RiskResponse){
					RiskResponse risk = (RiskResponse) value;
					if(n==0){
						try {
							
							riskMap = Utils.bean2Map(risk);
							System.out.println("riskMap����:"+riskMap.keySet().size());
							System.out.println("riskMap��һ��");
							resize(riskMap.keySet().size(),init_length);
							for(String ss : riskMap.keySet()){
								files[length] = ss;
								title[length] = ss;
								length++;
							}
							System.out.println("���ݺ�׼����ֵ����,����Ĵ�СΪ:"+title.length+"����ǰinit_length��"+init_length);
							n=1;	
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IntrospectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						try {
							riskMap = Utils.bean2Map(risk);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IntrospectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(value instanceof RiskRecord){
					RiskRecord risk = (RiskRecord) value;
					if(m==0){
						try {
							recordMap = Utils.bean2Map(risk);
							System.out.println("recordMap����:"+recordMap.keySet().size());
							System.out.println("riskMap��һ��");
							resize(recordMap.keySet().size(),97);//�ڶ�������
							for(String ss : recordMap.keySet()){
								files[length1] = ss;
								title[length1] = ss;
								length1++;
							}
							m=1;
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IntrospectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						try {
							recordMap = Utils.bean2Map(risk);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IntrospectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
			
//			cell2 = nextrow.createCell(1);
//			cell2.setCellValue(sts.getBorrowInfoId());
		}

		System.out.println(title.length+"���ݺ�ĳ���");
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);

		}
		
		// ����һ���ļ�
		File file = new File("C:/Users/Administrator/Desktop/demo1.xls"); // ��������д���·��
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ���� map��key == value
	 */
	private static void getDataObject(Object o,HSSFCell cell2){
		if(o instanceof String){
			String s = (String) o;
			cell2.setCellValue(s);
		}
		if(o instanceof Integer){
			int s = (int) o;
			cell2.setCellValue(s);
		}
		if(o instanceof Float){
			float s = (float) o;
			cell2.setCellValue(s);
		}
		if(o instanceof Boolean){
			boolean s = (boolean) o;
			cell2.setCellValue(s);
		}
		if(o instanceof Double){
			Double s = (Double) o;
			cell2.setCellValue(s);
		}
		if(o instanceof Timestamp){
			Timestamp s = (Timestamp) o;
			System.out.println("timestamp��������:"+s);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			//����һ:�������ڿ������������ַ�������ʽ��
			String tsStr = sdf.format(s);
			System.out.println("ʱ��:"+tsStr);  // 2017-01-15 21:17:04
			cell2.setCellValue(tsStr);
		}
		
	}
	
	/**
	 * ��������
	 */
	private static void resize(int sizelength,int size){
		String[] b=new String[title.length+sizelength];//������  
		String[] c=new String[files.length+sizelength];//������  
        System.arraycopy(title, 0, b, 0, size);//��a�������ݸ���������b  
        System.arraycopy(files, 0, c, 0, size);//��a�������ݸ���������b  
        title=b;//�ı�����  
        files=c;//�ı�����  
        System.out.println("���ݺ�����a����ΪΪ��"+b.length+"  ����a���ݣ�");  
        System.out.println("���ݺ�����a����ΪΪ��"+c.length+"  ����a���ݣ�");  
	}
	
	/**
	 * ����excel
	 * 
	 */
	public static void readExcel() {
		File file = new File("C:/Users/Administrator/Desktop/demo1.xls");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
			// ��ȡ��һ��������workbook.getSheet("Sheet0");
			// HSSFSheet sheet = workbook.getSheet("Sheet0");
			// ��ȡĬ�ϵ�һ��������sheet
			HSSFSheet sheet = workbook.getSheetAt(0);
			int firstRowNum = 0;
			// ��ȡsheet�����һ���к�
			int lastRowNum = sheet.getLastRowNum();
			for (int i = firstRowNum; i <= lastRowNum; i++) {
				HSSFRow row = sheet.getRow(i);
				// ��ȡ��ǰ�����Ԫ���к�
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					HSSFCell cell = row.getCell(j);
					String value = cell.getStringCellValue();
					System.out.print(value + "  ");
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Field[] declaredFields = Statistics.class.getDeclaredFields();//ʵ�����еĸ���
		int length = Statistics.class.getDeclaredFields().length;//ʵ�����еĸ���
		System.out.println(declaredFields[0]);
		System.out.println(declaredFields[1]);
		System.out.println(length);
//		createExcel(new Statistics(), length);
		
	}
}
