package codingTest_004;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public ArrayList<String> result(int a, String str[]) {
		ArrayList<String> answer = new ArrayList<>();
		for(String x : str) {
			String tmp = new StringBuilder(x).reverse().toString();
			answer.add(tmp);
		}	
		return answer;
	}
	
	public static void main(String[] args) {
		
	Main T = new Main();
	
	Scanner sc = new Scanner(System.in);
	
	String inputString[];
	int num = sc.nextInt();
	
	String[] str = new String[num];
	
	for (int i = 0 ; i < num ; i++) {
		str[i] = sc.next();
	}
	 
	for(String x : T.result(num, str)){
		System.out.println(x);
	
	
	}
	
		
	}

}
