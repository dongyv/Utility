package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class downloadFile {
	/**
	 * ResponseEntity spring���������
	 * 
	 * @param request
	 * @param loanId
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<byte[]> fileDownLoad(HttpServletRequest request, @RequestParam int loanId) throws Exception {

		ServletContext servletContext = request.getServletContext();//��ǰ��Ŀ��Ŀ¼
		ResponseEntity<byte[]> response = null;
		try {
			String fileName = "dongyv.pdf";
			String realPath = servletContext.getRealPath(fileName);// �õ��ļ�����λ�� �������Զ���λ�ã�
			InputStream in = new FileInputStream(new File(realPath));// �����ļ����뵽������֮��
			byte[] body = null;
			body = new byte[in.available()];// ������һ�ζԴ����������õķ������Բ��������شӴ���������ȡ�����������Ĺ���ʣ���ֽ���
			// in.read(body);//���뵽����������
			in.read(body, 0, in.available());// ���뵽����������
			fileName = new String(fileName.getBytes("gbk"), "iso8859-1");// ��ֹ��������
			HttpHeaders headers = new HttpHeaders();// ������Ӧͷ
			headers.setContentLength(body.length);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment;filename=" + fileName);//�ļ���ʽ���ص�����
			HttpStatus statusCode = HttpStatus.OK;// ������Ӧ��
			response = new ResponseEntity<byte[]>(body, headers, statusCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(response);
		return response;

		// public ResponseEntity��T body��
		// MultiValueMap < String��String > headers��
		// HttpStatus statusCode��
		// HttpEntityʹ�ø��������ģ������״̬���봴��һ���µġ�
		// ������
		// body - ʵ�����
		// headers - ʵ��ͷ
		// statusCode - ״̬��
	}
}
