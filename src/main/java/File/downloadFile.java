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
	 * ResponseEntity spring的下载组件
	 * 
	 * @param request
	 * @param loanId
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<byte[]> fileDownLoad(HttpServletRequest request, @RequestParam int loanId) throws Exception {

		ServletContext servletContext = request.getServletContext();//当前项目的目录
		ResponseEntity<byte[]> response = null;
		try {
			String fileName = "dongyv.pdf";
			String realPath = servletContext.getRealPath(fileName);// 得到文件所在位置 （可以自定义位置）
			InputStream in = new FileInputStream(new File(realPath));// 将该文件加入到输入流之中
			byte[] body = null;
			body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
			// in.read(body);//读入到输入流里面
			in.read(body, 0, in.available());// 读入到输入流里面
			fileName = new String(fileName.getBytes("gbk"), "iso8859-1");// 防止中文乱码
			HttpHeaders headers = new HttpHeaders();// 设置响应头
			headers.setContentLength(body.length);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment;filename=" + fileName);//文件格式下载到本地
			HttpStatus statusCode = HttpStatus.OK;// 设置响应吗
			response = new ResponseEntity<byte[]>(body, headers, statusCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(response);
		return response;

		// public ResponseEntity（T body，
		// MultiValueMap < String，String > headers，
		// HttpStatus statusCode）
		// HttpEntity使用给定的正文，标题和状态代码创建一个新的。
		// 参数：
		// body - 实体机构
		// headers - 实体头
		// statusCode - 状态码
	}
}
