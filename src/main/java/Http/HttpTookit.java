package Http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpTookit {
	private static Log log = LogFactory.getLog(HttpTookit.class);

	public static String doGet(String url, String queryString) {
		String response = null;
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		try {
			if (StringUtils.isNotBlank(queryString))
				method.setQueryString(URIUtil.encodeQuery(queryString));
			client.executeMethod(method);
			if (method.getStatusCode() == 200)
				response = method.getResponseBodyAsString();
		} catch (URIException e) {
			log.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
		} catch (IOException e) {
			log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}
		return response;
	}

	public static String doPost(String url, Map<String, String> params) {
		String response = null;
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		Iterator<Entry<String, String>> iter = params.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter
					.next();
			method.addParameter(entry.getKey(), entry.getValue());
		}
		method.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");

		try {
			client.executeMethod(method);
			if (method.getStatusCode() == 200)
				response = method.getResponseBodyAsString();
		} catch (IOException e) {
			log.error("执行HTTP Post请求" + url + "时，发生异常！", e);
		} finally {
			method.releaseConnection();
		}

		return response;
	}

	public static String sendGet(String url) {
		String msg = "";
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(
					url).openConnection();
			msg = creatConnection(url, httpURLConnection);
		} catch (IOException io) {
			log.error("http close" + io);
		}
		System.out.println("信息发送情况1：" + msg);
		return msg;
	}

	private static String creatConnection(String url,
			HttpURLConnection httpURLConnection) {
		String msg = "";
		try {
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
			httpURLConnection = (HttpURLConnection) new URL(url)
					.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("Content-Type",
					"text/html;charset=utf-8");
			msg = receiveMessage(httpURLConnection);
		} catch (IOException io) {
			io.printStackTrace();
			log.error("Http Connect to :" + url + " " + "IOFail!");
		} catch (Exception ex) {
			log.error("Http Connect to :" + url + " " + "Failed" + ex);
		} finally {
			closeConnection(httpURLConnection);
		}
		return msg;
	}

	private static void closeConnection(HttpURLConnection httpURLConnection) {
		try {
			if (httpURLConnection != null)
				httpURLConnection.disconnect();
		} catch (Exception localException) {
		}
	}

	private static String receiveMessage(HttpURLConnection httpURLConnection) {
		String responseBody = null;
		try {
			InputStream httpIn = httpURLConnection.getInputStream();
			if (httpIn != null) {
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				byte tempByte;
				while (-1 != (tempByte = (byte) httpIn.read())) {
					byte tempByte1 = 0;
					byteOut.write(tempByte1);
				}
				responseBody = new String(byteOut.toByteArray(), "utf-8");
			}
		} catch (IOException ioe) {
			log.error("Http Connect tosss :" + ioe.getLocalizedMessage() + " "
					+ "IOEFail!");
			return null;
		}
		return responseBody;
	}

	/**
	 * 获取真实IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	}
	
	public static String getDomain(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String domain = url.delete(url.length() - request.getRequestURI().length(), url.length())
				.append(request.getContextPath()).toString();
		return domain;
	}
	
	/**
	 * 构建soap参数
	 * @param methodName 调用webservice的方法名
	 * @param namespace webservice命名空间
	 * @param paramMap 提交的参数
	 * @return
	 */
	private static String buildSoapRequestData(String methodName, String namespace, Map<String, String> paramMap) {
		StringBuffer soapRequestData = new StringBuffer();
		soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		soapRequestData.append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">");
		soapRequestData.append("<soap12:Body><");
		soapRequestData.append(methodName);
		soapRequestData.append(" xmlns=\"");
		soapRequestData.append(namespace);
		soapRequestData.append("\">");
		Set<String> nameSet = paramMap.keySet();
		for (String name : nameSet) {
			soapRequestData.append("<");
			soapRequestData.append(name);
			soapRequestData.append(">");
			soapRequestData.append(paramMap.get(name));
			soapRequestData.append("</");
			soapRequestData.append(name);
			soapRequestData.append(">");
		}
		soapRequestData.append("</");
		soapRequestData.append(methodName);
		soapRequestData.append(">");
		soapRequestData.append("</soap12:Body>");
		soapRequestData.append("</soap12:Envelope>");
		return soapRequestData.toString();

	}

	/**
	 * httpclient提交soap
	 * @param methodName 调用webservice的方法名
	 * @param namespace webservice命名空间
	 * @param paramMap 提交的参数
	 * @param wsdlLocation 提交地址
	 * @return
	 */
	public static String doSoap(String methodName, String namespace, TreeMap<String, String> paramMap,
			String wsdlLocation) {
		PostMethod postMethod = new PostMethod(wsdlLocation);
		String soapRequestData = buildSoapRequestData(methodName, namespace, paramMap);
		try {
			byte[] bytes = soapRequestData.getBytes("utf-8");
			InputStream inputStream = new ByteArrayInputStream(bytes, 0, bytes.length);
			RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, bytes.length, "application/soap+xml; charset=utf-8");
			postMethod.setRequestEntity(requestEntity);

			HttpClient httpClient = new HttpClient();
			httpClient.executeMethod(postMethod);
			return postMethod.getResponseBodyAsString();
		} catch (UnsupportedEncodingException e) {
			log.error("执行HTTP doSoap请求" + wsdlLocation + "时，发生异常！", e);
		} catch (HttpException e) {
			log.error("执行HTTP doSoap请求" + wsdlLocation + "时，发生异常！", e);
		} catch (IOException e) {
			log.error("执行HTTP doSoap请求" + wsdlLocation + "时，发生异常！", e);
		} finally {
			postMethod.releaseConnection();
		}
		return null;
	}
}
