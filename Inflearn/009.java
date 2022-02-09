package codingTest_009;

import java.util.Scanner;

public class Main {

	public int result(String str) {
		String num = "";
		for(char x : str.toCharArray()) {
			
			if(x >= 48 && x <= 57) {
				num += x;
			}
			
		}
		int result = Integer.parseInt(num);
		return result;
		
		
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		String inputStr = sc.nextLine();
		
		
		System.out.println(T.result(inputStr));
			
		
	}
	
}
