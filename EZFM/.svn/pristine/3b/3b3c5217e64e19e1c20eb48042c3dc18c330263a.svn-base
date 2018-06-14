package com.shareworx.ezfm.webservice.eba.out;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.shareworx.platform.util.StringUtils;

/**
 * webService客户端业务操作
 * 预警消除客户端测试
 * 通过UrlConnection调用webService服务
 * 
 * @author jin.li
 *
 */
public class EbaClient {
	public static void main(String[] args) {
		String reportId = "3182f073-ecdd-4000-b364-a47093265335";
		EbaWarningEliminateService service = new EbaWarningEliminateServiceImpl();
		System.out.println(service.EbaWarningEliminate(reportId));
	}

	public static Map<String, Object> EbaWarningEliminate(String reportId) {
		Map<String, Object> map = null;
		try {
			URL url = new URL("http://222.180.250.162:9755/AlarmService.asmx");
			// 建立连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 标识可从URL读取数据
			connection.setDoInput(true);
			// 标识可从URL写入数据
			connection.setDoOutput(true);
			// 设置URL请求方式
			connection.setRequestMethod("POST");
			// 设置不缓存
			connection.setUseCaches(false);
			// 设置请求相关属性
			connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			connection.setRequestProperty("SOAPAction", "http://gnkj.com/WarningEliminate");
			// 获取输出流
			OutputStream os = connection.getOutputStream();
			// 生成请求体
			String soap = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" + " <soap:Body>" + "<WarningEliminate xmlns=\"http://gnkj.com/\">" + "<reportId>" + reportId + "</reportId>" + "</WarningEliminate>" + " </soap:Body>" + "</soap:Envelope>";
			// 写出请求
			os.write(soap.getBytes());
			os.flush();
			os.close();
			// 获取输入流
			InputStream is = connection.getInputStream();
			byte[] bs = new byte[1024];
			int len = 0;
			String warningEliminateResult = "";
			// 读取响应信息
			while ((len = is.read(bs)) != -1) {
				warningEliminateResult += new String(bs, 0, len, "UTF-8");
			}
			// 释放资源
			is.close();
			connection.disconnect();
			if (!StringUtils.isEmpty(warningEliminateResult)) {
				// dom4j解析xml
				Document document = DocumentHelper.parseText(warningEliminateResult);
				Element root = document.getRootElement();
				Element element = root.element("Body").element("WarningEliminateResponse").element("WarningEliminateResult");
				String string = element.getText();
				map = (Map<String, Object>) JSON.parse(string);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
