package cn.cashbang.core;

public class test {
	public static void main(String[] args) {
		String a = "123"+"\n"+"456";
		System.out.println(a);
		
		String b = "a,b,c,d";
		String [] c = b.split(",");
		System.out.println(c[6]);
	}
}
