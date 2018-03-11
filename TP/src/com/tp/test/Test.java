package com.tp.test;
import java.util.Scanner;
public class Test {
	public static void main(String[] args) {
		String str=";";
		String[] strNum=str.split(";");
		System.out.println(strNum.length);
		for(int i=0;i<strNum.length;i++){
			System.out.println("value="+strNum[i]);
		}
	}	
}
