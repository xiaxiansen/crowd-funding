package com.atguigu.crowd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.atguigu.crowd.constant.CrowdConstant;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

/**
 * 尚筹网项目通用工具方法类
 * @author 吴彦祖
 *
 */
public class CrowdUtil {
	/** * 专门负责上传文件到 OSS 服务器的工具方法
	 * *@param endpoint OSS 参数
	 * *@param accessKeyId OSS 参数
	 * *@param accessKeySecret OSS 参数
	 * *@param inputStream 要上传的文件的输入流
	 * *@param bucketName OSS 参数
	 * *@param bucketDomain OSS 参数
	 * *@param originalName 要上传的文件的原始文件名
	 * *@return 包含上传结果以及上传的文件在 OSS 上的访问路径
	 * */
	public static ResultEntity<String>uploadFileToOss(
					String endpoint,
					String accessKeyId,
					String accessKeySecret,
					InputStream inputStream,
					String bucketName,
					String bucketDomain,
					String originalName){
		// 创建 OSSClient 实例。
		OSS ossClient=new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
		// 生成上传文件的目录
		String folderName=new SimpleDateFormat("yyyyMMdd").format(new Date());
		// 生成上传文件在 OSS 服务器上保存时的文件名
		// 原始文件名：beautfulgirl.jpg
		// 生成文件名：wer234234efwer235346457dfswet346235.jpg
		// 使用 UUID 生成文件主体名称
		String fileMainName= UUID.randomUUID().toString().replace("-","");
		// 从原始文件名中获取文件扩展名
		String extensionName=originalName.substring(originalName.lastIndexOf("."));
		// 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
		String objectName=folderName+"/"+fileMainName+extensionName;
		try{
		// 调用 OSS 客户端对象的方法上传文件并获取响应结果数据
			PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);
		// 从响应结果中获取具体响应消息
			ResponseMessage responseMessage=putObjectResult.getResponse();
		// 根据响应状态码判断请求是否成功
			if(responseMessage==null){
				// 拼接访问刚刚上传的文件的路径
				String ossFileAccessPath=bucketDomain+"/"+objectName;
				// 当前方法返回成功
				return ResultEntity.successWithData(ossFileAccessPath);
			}else{
				// 获取响应状态码
				int statusCode=responseMessage.getStatusCode();
				// 如果请求没有成功，获取错误消息
				String errorMessage=responseMessage.getErrorResponseAsString();
				// 当前方法返回失败
				return ResultEntity.failed(" 当 前 响 应 状 态 码 ="+statusCode+" 错 误 消 息 ="+errorMessage);
			}
		}catch(Exception e){
			e.printStackTrace();
			// 当前方法返回失败
			return ResultEntity.failed(e.getMessage());
		}finally{
			if(ossClient!=null){
				// 关闭 OSSClient。
				 ossClient.shutdown();
			}
		}
	}
	
//	public static void main(String[] args) throws FileNotFoundException {
//		FileInputStream inputStream = new FileInputStream(new File("atcrowdfunding05-common-util/111.jpg"));
//		ResultEntity<String> resultEntity = uploadFileToOss("http://oss-cn-hangzhou.aliyuncs.com", "LTAI4G5Zkoy8s4sGqwcJVapp", "Y6x412T8fN2hWNHGyIl2mWTaVpohFy", inputStream, "atguigu0902", "http://atguigu0902@1664696849414470.onaliyun.com", "111.jpg");
//		System.out.println(resultEntity);
//	}
		
		/**
         * @param host     短信接口调用url地址
         * @param path     具体发送短信地址
         * @param method   请求方法
         * @param phoneNum 手机号
         * @param appCode  应用码
         * @return 		成功返回验证码
         */
	public static ResultEntity<String> sendCodeByShortMessage(
			String host,
			String path,
			String method,
			String phoneNum,
			String appCode){
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appCode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("receive",phoneNum);
		//生成验证码
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 6; i++){
			int random = (int) (Math.random() * 10);
			builder.append(random);
		}
		String tag = builder.toString();
		querys.put("tag", tag);
		querys.put("templateId", "M09DD535F4");
		Map<String, String> bodys = new HashMap<String, String>();
		try {
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			String reasonPhrase = statusLine.getReasonPhrase();
			if(statusCode == 200){
				return ResultEntity.successWithData(tag);
			}
			return ResultEntity.failed(reasonPhrase);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultEntity.failed(e.getMessage());
		}
	}
	/**
	 * 对明文字符串进行MD5加密
	 * @param source 传入的明文字符串
	 * @return 加密结果
	 */
	public static String md5(String source) {
		
		// 1.判断source是否有效
		if(source == null || source.length() == 0) {
		
			// 2.如果不是有效的字符串抛出异常
			throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
		}
		
		try {
			// 3.获取MessageDigest对象
			String algorithm = "md5";
			
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			
			// 4.获取明文字符串对应的字节数组
			byte[] input = source.getBytes();
			
			// 5.执行加密
			byte[] output = messageDigest.digest(input);
			
			// 6.创建BigInteger对象
			int signum = 1;
			BigInteger bigInteger = new BigInteger(signum, output);
			
			// 7.按照16进制将bigInteger的值转换为字符串
			int radix = 16;
			String encoded = bigInteger.toString(radix).toUpperCase();
			
			return encoded;
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 判断当前请求是否为Ajax请求
	 * @param request 请求对象
	 * @return
	 * 		true：当前请求是Ajax请求
	 * 		false：当前请求不是Ajax请求
	 */
	public static boolean judgeRequestType(HttpServletRequest request) {
		
		// 1.获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");
		
		// 2.判断
		return (acceptHeader != null && acceptHeader.contains("application/json"))
				
				||
				
				(xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
	}

}
