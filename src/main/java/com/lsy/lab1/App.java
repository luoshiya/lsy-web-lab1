package com.lsy.lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;
public class App {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入帐号：");
        String account=in.nextLine();
        System.out.println("请输入密码：");
        String pw=in.nextLine();
        //读取登录文件
        BufferedReader br=null;
        try {
			br=new BufferedReader(new FileReader("login.txt"));
			String line=br.readLine();
			boolean flag=false;//找到标志
			while(line!=null) {
				String[] result=line.split("=");//将获取的字符串以"="分割，获取帐号和密码
				if(account.equals(result[0])) {
					if(sha256hex(pw).equals(result[1])) {//帐号和密码均相等
						System.out.println("登录成功");
						flag=true;
						break;
					}
				}
				line=br.readLine();
			}
			if(!flag) {//没找到匹配
				System.out.println("登录失败");
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
       
    }
    public static String sha256hex(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
