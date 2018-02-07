# 工具类
***
1. 文件
2. 网络
3. 时间
4. 格式

### 文件下载
***

<pre><code>
HttpHeaders headers = new HttpHeaders();// 设置响应头
headers.setContentLength(body.length);
headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
headers.add("Content-Disposition", "attachment;filename=" + fileName);//文件格式下载到本地
HttpStatus statusCode = HttpStatus.OK;// 设置响应吗
ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
</code></pre>
<pre><code>
 <dependency>
 <groupId>org.springframework</groupId>
 <artifactId>spring-webmvc</artifactId>
 <version>${spring.version}</version>
 </dependency>
</code></pre>

