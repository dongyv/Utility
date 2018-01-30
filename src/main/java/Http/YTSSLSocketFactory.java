package Http;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * SSL Socket������
 * 
 * @version V1.0
 * @author dujl@lianlian.com
 * @Date 2016��9��19�� ����22:19:48
 * @since JDK 1.6
 */
public class YTSSLSocketFactory extends SSLSocketFactory{

	private SSLContext sslContext = SSLContext.getInstance(TLS);

	/**
	 * ����SSLSocketFactory
	 * 
	 * @param truststore
	 *            ��֤���
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 */
	@SuppressWarnings("deprecation")
	public YTSSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException,
			UnrecoverableKeyException {
		super(truststore);
		TrustManager tm = new X509TrustManager() {

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		this.sslContext.init(null, new TrustManager[] { tm }, null);
		this.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); // ����������������֤
	}

	/**
	 * ����Socket
	 */
	public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
		socket = this.sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
		return socket;
	}

	/**
	 * ����Socket
	 */
	public Socket createSocket() throws IOException {
		Socket socket = this.sslContext.getSocketFactory().createSocket();
		return socket;
	}
}
