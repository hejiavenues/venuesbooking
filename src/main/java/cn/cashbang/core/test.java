package cn.cashbang.core;

import java.util.Random;

public class test {
	public static void main(String[] args) {
		System.out.println(methed(3,5,1,1,1,5));
	}
	public static String methed(int a,int b,int priorityA,int weightA,int priorityB,int weightB) {
		boolean operateA=true;
		boolean operateB=true;
		String resultA = null,resultB = null;
		try {
			resultA = methedA(a,b);
		} catch (Exception e) {
			operateA = false;
		}
		try {
			resultB = methedB(a,b);
		} catch (Exception e) {
			operateB = false;
		}
		if(!operateA) {
			if(operateB) {
				return resultB;
			}else {
				throw new RuntimeException();
			}
		}
		if(!operateB) {
			if(operateA) {
				return resultA;
			}else {
				throw new RuntimeException();
			}
		}
		if(operateA && operateB) {
			if(!resultA.equalsIgnoreCase(resultB)) {
				System.out.println("两方法返回值不同");
			}
			if(priorityA>priorityB) {
				return resultA;
			}else if(priorityA<priorityB) {
				return resultB;
			}else {
				int total = weightA*100+weightB*100;
				Random random = new Random(total);
				if(random.nextInt()<weightA*100) {
					return resultA;
				}else {
					return resultB;
				}
			}
		}
		return "error";
	}
	//模拟serviceA
	public static String methedA(int a,int b) {
		return String.valueOf(a*b).intern();
	}
	//模拟serviceB
	public static String methedB(int a,int b) {
		//return String.valueOf(b*a).intern();
		return String.valueOf(b*a*a).intern();
	}
}
