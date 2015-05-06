package net.eicp.ganmt.galacommunity.bll.net;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class UploadPicData_Parser {
	/**
	 * HttpClient 请求服务�?
	 * 弃用
	 * 提交参数里有文件的数�?
	 * @param url 服务器地�?
	 * @param param 参数
	 * @return 服务器返回结�?
	 * @throws Exception
	 */
	public static String uploadSubmit(String url, Map<String, String> param,ArrayList<File> arrfile) throws Exception {

		HttpPost post = new HttpPost(url);  
		HttpClient httpClient=new DefaultHttpClient();
		MultipartEntity entity = new MultipartEntity();
		if (param != null && !param.isEmpty()) {
			for (Map.Entry<String, String> entry : param.entrySet()) {     
				if (entry.getValue() != null
						&& entry.getValue().trim().length() > 0) {
					entity.addPart(entry.getKey(),new StringBody(entry.getValue(),
							Charset.forName(org.apache.http.protocol.HTTP.UTF_8)));
				}
			}
		}
		for (int i = 0; i < arrfile.size(); i++) {
			File file = arrfile.get(i);
			// 添加文件参数
			if (file != null && file.exists()) {
				entity.addPart("file"+i, new FileBody(file));
			}
		}
		
		
		post.setEntity(entity);  
		
		HttpResponse response = httpClient.execute(post);
		int stateCode = response.getStatusLine().getStatusCode();
		StringBuffer sb = new StringBuffer();
		if (stateCode == HttpStatus.SC_OK) {
			HttpEntity result = response.getEntity();
			if (result != null) {
				InputStream is = result.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String tempLine;
				while ((tempLine = br.readLine()) != null) {
					sb.append(tempLine);
				}
			}
		}
		post.abort();
		return sb.toString();
	}
}
