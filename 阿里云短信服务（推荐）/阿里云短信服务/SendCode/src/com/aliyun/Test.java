package com.aliyun;

import java.util.Scanner;

import com.aliyuncs.exceptions.ClientException;

/**
 *测试类
 *
 *@author jiangwenchen
 */
public class Test {
	public static void main(String[] args) throws ClientException {
		sendSms("15011438024");
		//sendSmg("15011438024","张三");
	}
	@SuppressWarnings("resource")
	public static void sendSms(String phoneNumber) throws ClientException {
		String randomSMSCode = SmsUtil.getRandomSMSCode(5,true);
		if (SmsUtil.sendSms(phoneNumber,randomSMSCode)) {
			System.out.println("短信发送成功");
			Scanner scanner= new Scanner(System.in);
			System.out.println("请输入您收到的验证码：");
			String code=scanner.next();
			if (randomSMSCode.equals(code)) {
				System.out.println("验证成功");
			}
		}else {
			System.out.println("失败");
		}
	}
	public static void sendSmg(String phoneNumber,String name) throws ClientException {
		if (SmsUtil.sendSmg(phoneNumber, name)) {
			System.out.println("通知短信已经发送");
		}else {
			System.out.println("发送失败");
		}
	}
}
