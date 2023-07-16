package com.learn.test;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Temptemp {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // 一系列的判断用户密码是否正确
        boolean flag = true; //用户名密码都对，就是true，否则为false
        //什么意思呢，系统先偷偷判断是否密码用户正确，就算错了，也得等你验证码输完才给出结果。
        while (true) {
            System.out.print("请输入验证码：");
            String Captcha = GetCode();
            System.out.println(Captcha);
            System.out.println();
            String input_str = sc.next();
            if (input_str.equalsIgnoreCase(Captcha)) {
                if (flag) {
                    System.out.println("Login successful");
                } else
                    System.out.println("用户名or密码错误");
                break;
            } else
                System.out.println("验证码错误，请重新输入");

        }
    }
    public static String GetCode() {
        Random r = new Random();
        StringBuilder str_Builder_tmp = new StringBuilder();
        String Captcha;

        for (int i = 0; i < 4; i++) {
            int r1 = r.nextInt(52);
            char c;
            if (r1 <= 25)
                c = (char) ('a' + r1);
            else
                c = (char) ('A' + r1 - 26);
            str_Builder_tmp.append(c);
        }
        int r1 = r.nextInt(10);
        char c = (char) (r1 + '0');
        str_Builder_tmp.append(c);
        Captcha = str_Builder_tmp.toString();
        // System.out.println(Captcha);
        char[] charArray = Captcha.toCharArray();
        r1 = r.nextInt(5);
        char temp = charArray[4];
        charArray[4] = charArray[r1];
        charArray[r1] = temp;
        Captcha = new String(charArray);
        return Captcha;
    }

}
