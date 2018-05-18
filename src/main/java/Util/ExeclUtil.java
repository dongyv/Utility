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
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attachment; filename=testRed.xls");// 设定输出文件头
		response.setContentType("application/msexcel");// 定义输出类型

		WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
		String tmptitle = "测试数据"; // 标题
		WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称

		// 设置excel标题
		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfFC = new WritableCellFormat(wfont);
		wcfFC.setBackground(Colour.AQUA);
		wsheet.addCell(new Label(1, 0, tmptitle, wcfFC));
		wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		wcfFC = new WritableCellFormat(wfont);

		// 开始生成主体内容
		wsheet.addCell(new Label(0, 2, "姓名"));
		wsheet.addCell(new Label(1, 2, "邮箱"));
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

		// 主体内容生成结束
		wbook.write(); // 写入文件
		wbook.close();
		os.close(); // 关闭流
	}

	/**
	 * 生成Excel文件  动态设置每行值 可实现通用
	 */
	public static void createExcel(Class<T> clazz,List<Map<String,Object>> datas){
		
	}
	
	static String[] title = { "用户编号", "订单编号", "用户在该笔订单前的下款次数", "该用户在该笔订单前的总申请次数","还款状态 ","申请时间","教育水平","婚姻状况","子女个数"
			,"职业","地址信息","联系关系1","联系关系1手机号","联系关系2","联系关系手机号","手机设备","注册和申请时间间隔","注册时间与申请之间登录次数","工作年限"
			,"发薪日期","号码注册至今几个月","当前余额","每月平均消费","互通电话的号码数量","夜间活动百分比","距当前月1月主叫次数","距当前月2月主叫次数","距当前月3月主叫次数"
			,"距当前月4月主叫次数","距当前月5月主叫次数","距当前月6月主叫次数","距当前月7月主叫次数","距当前月8月主叫次数","距当前月1月主叫时长","距当前月2月主叫时长","距当前月3月主叫时长","距当前月4月主叫时长"
			,"距当前月5月主叫时长","距当前月6月主叫时长","距当前月7月主叫时长","距当前月8月主叫时长","朋友圈地区联系占比数","p2p统计 ","催收号码统计","贷款号码统计","赌博号码统计"
			,"套现号码统计","银行号码统计","风控","风控详情"};
	static final int init_length = 50;
	static String[] files = {"userId","borrowInfoId","repayNumber","loanNumber","repayStatus","applyTime","education","marriage","child",
			"positonName","companyAddress","cognateRelation","cognateMobile","socialRelation","socialMobile","regType","timeInfo","loginNumber","workingTime"
			,"payDate","regtime","curBala","avgCon","callCnt","nightActivity","m1Num","m2Num","m3Num"
			,"m4Num","m5Num","m6Num","m7Num","m8Num","m1Secd","m2Secd","m3Secd","m4Secd"
			,"m5Secd","m6Secd","m7Secd","m8Secd","pyq","p2pCount","csCount","dkCount","dbCount"
			,"txCount","yhCount","riskResponse","riskRecord"};
	
	/**
	 * 生成Excel文件
	 */
	public static void createExcel(List<Map<String,Object>> lists) {
		/* Method[] methods = sts.getClass().getMethods();
	        for (int i = 0; i < methods.length; i++) {
	            Method method = methods[i];
	            if (method.getName().startsWith("get")) {
	                System.out.print("类中的get方法:" + method.getName() + "\t");
//	                System.out.println("get方法的值:" + method.invoke());
	            }
	        }*/
//		int length = clazz.getDeclaredFields().length;//实体类中的属性
//		int fileslength = Statistics.class.getDeclaredFields().length;//实体类中的属性
		int length = 50;
		int length1 = 97;
		int m=0,n=0;
		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建一个工作表sheet
		HSSFSheet sheet = workbook.createSheet();
		// 创建爱你第一行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = null;
		// 插入第一行数据id、name、sex
//		for (int i = 0; i < title.length; i++) {
//			cell = row.createCell(i);
//			cell.setCellValue(title[i]);
//
//		}
		// 追加数据
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
							System.out.println("riskMap长度:"+riskMap.keySet().size());
							System.out.println("riskMap第一次");
							resize(riskMap.keySet().size(),init_length);
							for(String ss : riskMap.keySet()){
								files[length] = ss;
								title[length] = ss;
								length++;
							}
							System.out.println("扩容后准备赋值操作,数组的大小为:"+title.length+"，当前init_length："+init_length);
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
							System.out.println("recordMap长度:"+recordMap.keySet().size());
							System.out.println("riskMap第一次");
							resize(recordMap.keySet().size(),97);//第二次扩容
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

		System.out.println(title.length+"扩容后的长度");
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);

		}
		
		// 创建一个文件
		File file = new File("C:/Users/Administrator/Desktop/demo1.xls"); // 在这里填写存放路径
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
	 * 返回 map的key == value
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
			System.out.println("timestamp类型数据:"+s);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			//方法一:优势在于可以灵活的设置字符串的形式。
			String tsStr = sdf.format(s);
			System.out.println("时间:"+tsStr);  // 2017-01-15 21:17:04
			cell2.setCellValue(tsStr);
		}
		
	}
	
	/**
	 * 数组扩容
	 */
	private static void resize(int sizelength,int size){
		String[] b=new String[title.length+sizelength];//新数组  
		String[] c=new String[files.length+sizelength];//新数组  
        System.arraycopy(title, 0, b, 0, size);//将a数组内容复制新数组b  
        System.arraycopy(files, 0, c, 0, size);//将a数组内容复制新数组b  
        title=b;//改变引用  
        files=c;//改变引用  
        System.out.println("扩容后数组a容量为为："+b.length+"  数组a内容：");  
        System.out.println("扩容后数组a容量为为："+c.length+"  数组a内容：");  
	}
	
	/**
	 * 解析excel
	 * 
	 */
	public static void readExcel() {
		File file = new File("C:/Users/Administrator/Desktop/demo1.xls");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
			// 获取第一个工作表workbook.getSheet("Sheet0");
			// HSSFSheet sheet = workbook.getSheet("Sheet0");
			// 读取默认第一个工作表sheet
			HSSFSheet sheet = workbook.getSheetAt(0);
			int firstRowNum = 0;
			// 获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			for (int i = firstRowNum; i <= lastRowNum; i++) {
				HSSFRow row = sheet.getRow(i);
				// 获取当前行最后单元格列号
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
		Field[] declaredFields = Statistics.class.getDeclaredFields();//实体类中的个数
		int length = Statistics.class.getDeclaredFields().length;//实体类中的个数
		System.out.println(declaredFields[0]);
		System.out.println(declaredFields[1]);
		System.out.println(length);
//		createExcel(new Statistics(), length);
		
	}
}
