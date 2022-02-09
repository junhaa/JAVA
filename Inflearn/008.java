package codingTest_008;

import java.util.Scanner;

public class Main {

	public String result(String str) {
		
		String answer = "YES";
		String str1 = "" ;
		for(char x : str.toCharArray()) {
			if(x >= 65 && x <= 90 || x >= 97 && x <= 122) {
				str1 += x;
			}
		}
		String strUpper = str1.toUpperCase();
		
		int len = strUpper.length();
		for(int i = 0; i <len/2 ; i++ ) {
			if(strUpper.charAt(i) != strUpper.charAt(len - i -1)) {
				answer = "NO" ;
				break;
			}
		}
		
		return answer;
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println(T.result(str));
		
	}
	
	
	
}
