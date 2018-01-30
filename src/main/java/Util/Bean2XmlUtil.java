package Util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Bean2XmlUtil {
	/**
	 * @Title: convertToXml 
	 * @Description:  JavaBean转换成xml 默认编码UTF-8
	 * @param @param obj
	 * @param @return 
	 * @return String 
	 * @throws 
	 * @author zhangfei_eason@163.com 
	 * @date 2015年9月14日 下午9:34:07
	 */
	public static String bean2Xml(Object obj) {
		return bean2Xml(obj, "UTF-8");
	}

	/** 
	 * @Title: bean2Xml 
	 * @Description:  JavaBean转换成xml
	 * @param @param obj
	 * @param @param encoding
	 * @param @return 
	 * @return String 
	 * @throws 
	 * @author zhangfei_eason@163.com 
	 * @date 2015年9月14日 下午9:36:24 
	 */ 
	public static String bean2Xml(Object obj, String encoding) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			//决定是否在转换成xml时同时进行格式化（即按标签自动换行，否则即是一行的xml）
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			//xml的编码方式
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/** 
	 * @Title: xml2Bean 
	 * @Description:  xml转换成JavaBean
	 * @param @param xml
	 * @param @param c
	 * @param @return 
	 * @return T 
	 * @throws 
	 * @author zhangfei_eason@163.com 
	 * @date 2015年9月14日 下午9:36:39 
	 */ 
	@SuppressWarnings("unchecked")
	public static <T> T xml2Bean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}
}
