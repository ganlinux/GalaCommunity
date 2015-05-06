package net.eicp.ganmt.galacommunity.bll.net;

import net.eicp.ganmt.galacommunity.tools.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;



public class UrlConnection_Request_PD {
	/**
	 * 采用HttpUrlConnection上传图片和文�?
	 */
public static String uploadSubmit(String url, Map<String, String> param,ArrayList<File> arrfile) throws Exception {
			StringBuilder sb =new StringBuilder();
			
			String BOUNDARY ="--------------yichems";//数据分隔�?
			String MULTIPART_FORM_DATA ="Multipart/form-data";  
	        try {
				URL url_ =new URL(url);
				HttpURLConnection conn = (HttpURLConnection) url_.openConnection(); 
				conn.setConnectTimeout(40000);
				conn.setReadTimeout(40000);
				conn.setDoInput(true);//允许输入
				conn.setDoOutput(true);//允许输出
				conn.setUseCaches(false);//不使用Cache
				conn.setChunkedStreamingMode(0);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("connection", "close");
				conn.setRequestProperty("Charset","UTF-8");
				conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA +";boundary="+ BOUNDARY);
				if (param != null && !param.isEmpty()) {
					for (Map.Entry<String, String> entry : param.entrySet()) {
						 sb.append("--");
				            sb.append(BOUNDARY);
				            sb.append("\r\n");
				            sb.append("Content-Disposition: form-data; name=\""+ entry.getKey() +"\"\r\n\r\n");
				            sb.append(entry.getValue());
				            sb.append("\r\n");
					}
				}
				DataOutputStream outStream =new DataOutputStream(conn.getOutputStream());
				outStream.write(sb.toString().getBytes());//发�?�表单字段数�?

				for (int i = 0; i < arrfile.size(); i++) {
					File file = arrfile.get(i);
					 byte[] content = readFileImage(file);
					 StringBuilder split =new StringBuilder();
				        split.append("--");
				        split.append(BOUNDARY);
				        split.append("\r\n");
				        split.append("Content-Disposition: form-data;name=\"pic\";filename=\"temp.jpg\"\r\n");
				        split.append("Content-Type: image/jpg\r\n\r\n");
				        outStream.write(split.toString().getBytes());
				        outStream.write(content,0, content.length);
				        outStream.write("\r\n".getBytes());   
				}
				byte[] end_data = ("--"+ BOUNDARY +"--\r\n").getBytes();//数据结束标志
				outStream.write(end_data);
				 	outStream.flush();
				 	outStream.close();
				    int cah = conn.getResponseCode();
					InputStream is = conn.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					String tempLine;
					StringBuffer sb1 = new StringBuffer();
					while ((tempLine = br.readLine()) != null) {
						sb1.append(tempLine);
					}
					is.close();
					is = null;
					br.close();
					br = null;
				return sb1.toString();
			}catch(SocketTimeoutException e){
				return Utils.LOCALERRO_NETTIMEROUT;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return null;
	}
	public static byte[] readFileImage(File filename)throws IOException {
		FileInputStream  fstream = new FileInputStream(filename);
		    BufferedInputStream bufferedInputStream =new BufferedInputStream(fstream);
		    int len = bufferedInputStream.available();
		    byte[] bytes =new byte[len];
		    int r = bufferedInputStream.read(bytes);
		    if(len != r) {
		        bytes =null;
		        throw new IOException("file Error");
		    }
		    fstream.close();
		    bufferedInputStream.close();
		    fstream = null;
		    bufferedInputStream = null;
		    return bytes;
	}

}
