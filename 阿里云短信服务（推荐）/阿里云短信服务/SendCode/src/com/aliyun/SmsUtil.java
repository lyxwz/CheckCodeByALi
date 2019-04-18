package com.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 阿里云短信服务
 *
 *@author jiangwenchen
 */
public class SmsUtil {
	
	//产品名称:云通信短信API产品,开发者无需替换
	static final String product="Dysmsapi";
	//产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";
	// 此处需要替换成开发者自己的AccessKey(在阿里云访问控制台寻找)
	static final String accessKeyId = "LTAILkpbN1cFSGzM";
    static final String accessKeySecret = "sRgZXTbqULFIzDcYHAC6mG6FHvnYtL";
    
    /**
     * 发送验证码短信
     * 
     * @phoneNumber
     *@author jiangwenchen
     */
    public static boolean sendSms(String phoneNumber,String code) throws ClientException{
    	//可自助调整超时时间
    	System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile=DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient=new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request=new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到举个例子
        request.setSignName("姜文晨");
        //必填:短信模板id-可在短信控制台中找到，是id不是名字，举个例子
        request.setTemplateCode("SMS_106965074");
        //可选:模板中的变量替换JSON串,如模板内容	\为"亲爱的${name},您的验证码为${code}"时,此处的值为--必填，与模板相对应
        //request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        request.setTemplateParam("{\"code\":\""+code+"\"}");
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse=acsClient.getAcsResponse(request);
        //System.out.println(sendSmsResponse.getCode());
        if (sendSmsResponse.getCode().equals("OK")) {
			return true;
		}
        return false;
    }
    
    /**
     * 发送通知类短信
     * 
     *@author jiangwenchen
     */
    public static boolean sendSmg(String phoneNumber,String name) throws ClientException{
    	//可自助调整超时时间
    	System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile=DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient=new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request=new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到举个例子
        request.setSignName("姜文晨");
        //必填:短信模板id-可在短信控制台中找到，是id不是名字，举个例子
        request.setTemplateCode("SMS_107055059");
        //可选:模板中的变量替换JSON串,如模板内容	\为"亲爱的${name},您的验证码为${code}"时,此处的值为--必填，与模板相对应
        request.setTemplateParam("{\"name\":\""+name+"\"}");
        //request.setTemplateParam("{\"code\":\""+getRandomSMSCode(6,true)+"\"}");
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse=acsClient.getAcsResponse(request);
        //System.out.println(sendSmsResponse.getCode());
        if (sendSmsResponse.getCode().equals("OK")) {
			return true;
		}
        return false;
    }

    /**
     * 随机生成验证码
     * 
     *@author jiangwenchen
     */
    public static final String getRandomSMSCode(int length, boolean numberCode) {
    	String randomSMSCode="";
        String codeTable = numberCode ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        boolean flag = true;
        do {
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * codeTable.length();
                int intR = (int) Math.floor(dblR);
                char c = codeTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                randomSMSCode += codeTable.charAt(intR);
            }
            if (count >= 2) {
            	flag = false;
            }
        } while (flag);
        return randomSMSCode;
    }
}


