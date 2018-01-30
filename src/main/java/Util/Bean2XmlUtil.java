package Util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Bean2XmlUtil {
	/**
	 * @Title: convertToXml 
	 * @Description:  JavaBeanת����xml Ĭ�ϱ���UTF-8
	 * @param @param obj
	 * @param @return 
	 * @return String 
	 * @throws 
	 * @author zhangfei_eason@163.com 
	 * @date 2015��9��14�� ����9:34:07
	 */
	public static String bean2Xml(Object obj) {
		return bean2Xml(obj, "UTF-8");
	}

	/** 
	 * @Title: bean2Xml 
	 * @Description:  JavaBeanת����xml
	 * @param @param obj
	 * @param @param encoding
	 * @param @return 
	 * @return String 
	 * @throws 
	 * @author zhangfei_eason@163.com 
	 * @date 2015��9��14�� ����9:36:24 
	 */ 
	public static String bean2Xml(Object obj, String encoding) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			//�����Ƿ���ת����xmlʱͬʱ���и�ʽ����������ǩ�Զ����У�������һ�е�xml��
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			//xml�ı��뷽ʽ
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
	 * @Description:  xmlת����JavaBean
	 * @param @param xml
	 * @param @param c
	 * @param @return 
	 * @return T 
	 * @throws 
	 * @author zhangfei_eason@163.com 
	 * @date 2015��9��14�� ����9:36:39 
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
