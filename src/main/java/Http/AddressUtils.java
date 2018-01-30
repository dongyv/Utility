package Http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class AddressUtils {
	public static void main(String[] args) {

		AddressUtils addressUtils = new AddressUtils();

		String ip = "118.213.176.78";

		String address = "";

		try {

			address = addressUtils.getAddress("ip=" + ip, "utf-8");

		} catch (Exception e) {

			e.printStackTrace();
		}

		System.out.println(address);

	}

	/**
	 * ��ȡ��ַ
	 * 
	 * @param params
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public String getAddress(String params, String encoding) throws Exception {

		String path = "http://ip.taobao.com/service/getIpInfo.php";

		String returnStr = this.getRs(path, params, encoding);

		JSONObject json = null;

		if (returnStr != null) {

			json = new JSONObject(returnStr);

			if ("0".equals(json.get("code").toString())) {

				StringBuffer buffer = new StringBuffer();

				// buffer.append(decodeUnicode(json.optJSONObject("data").getString("country")));//����

				// buffer.append(decodeUnicode(json.optJSONObject("data").getString("area")));//����

				buffer.append(decodeUnicode(json.optJSONObject("data")
						.getString("region")));// ʡ��

				buffer.append(decodeUnicode(json.optJSONObject("data")
						.getString("city")));// ����

				buffer.append(decodeUnicode(json.optJSONObject("data")
						.getString("county")));// ����

				buffer.append(decodeUnicode(json.optJSONObject("data")
						.getString("isp")));// ISP��˾

				System.out.println(buffer.toString());

				return buffer.toString();

			} else {

				return "��ȡ��ַʧ��";

			}

		}

		return null;

	}

	/**
	 * ��url��ȡ���
	 * 
	 * @param path
	 * @param params
	 * @param encoding
	 * @return
	 */
	public String getRs(String path, String params, String encoding) {

		URL url = null;

		HttpURLConnection connection = null;
		try {

			url = new URL(path);

			connection = (HttpURLConnection) url.openConnection();// �½�����ʵ��
			connection.setConnectTimeout(2000);// �������ӳ�ʱʱ�䣬��λ����?
			connection.setReadTimeout(2000);// ���ö�ȡ���ݳ�ʱʱ�䣬��λ����?
			connection.setDoInput(true);// �Ƿ������� true|false
			connection.setDoOutput(true);// �Ƿ��������true|false
			connection.setRequestMethod("POST");// �ύ����POST|GET
			connection.setUseCaches(false);// �Ƿ񻺴�true|false
			connection.connect();// �����Ӷ˿�
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());

			out.writeBytes(params);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();

			return buffer.toString();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			connection.disconnect();// �ر�����
		}

		return null;
	}

	/**
	 * �ַ�ת��
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {

		char aChar;

		int len = theString.length();

		StringBuffer buffer = new StringBuffer(len);

		for (int i = 0; i < len;) {

			aChar = theString.charAt(i++);

			if (aChar == '\\') {

				aChar = theString.charAt(i++);

				if (aChar == 'u') {

					int val = 0;

					for (int j = 0; j < 4; j++) {

						aChar = theString.charAt(i++);

						switch (aChar) {

						case '0':

						case '1':

						case '2':

						case '3':

						case '4':

						case '5':

						case '6':

						case '7':

						case '8':

						case '9':

							val = (val << 4) + aChar - '0';

							break;

						case 'a':

						case 'b':

						case 'c':

						case 'd':

						case 'e':

						case 'f':

							val = (val << 4) + 10 + aChar - 'a';

							break;

						case 'A':

						case 'B':

						case 'C':

						case 'D':

						case 'E':

						case 'F':

							val = (val << 4) + 10 + aChar - 'A';

							break;

						default:

							throw new IllegalArgumentException(

							"Malformed      encoding.");
						}
					}

					buffer.append((char) val);

				} else {
					if (aChar == 't') {

						aChar = '\t';
					}
					if (aChar == 'r') {

						aChar = '\r';
					}
					if (aChar == 'n') {

						aChar = '\n';
					}
					if (aChar == 'f') {

						aChar = '\f';

					}
					buffer.append(aChar);
				}
			} else {

				buffer.append(aChar);
			}
		}
		return buffer.toString();
	}
}
