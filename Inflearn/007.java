package codingTest_007;

import java.util.Scanner;

public class Main {

	public String result(String str) {
			
		String strUpper = str.toUpperCase();
		String answer = "YES";
		int len = strUpper.length();
		for(int i = 0 ; i < len /2 ; i ++) {
			if(strUpper.charAt(i) != strUpper.charAt(len-1-i)) {
				answer = "NO";
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
