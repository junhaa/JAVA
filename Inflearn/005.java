package codingTest_005;

import java.util.Scanner;

public class Main {

	public String result(String str) {
		String answer;
	char[] x = str.toCharArray();
		int lt = 0, rt = x.length -1 ;
		while (lt<rt) {
			if(!Character.isAlphabetic(x[lt])) lt ++;
			else if (!Character.isAlphabetic(x[rt])) rt --;
			else {
				char tmp = x[lt];
				x[lt] = x[rt];
				x[rt] = tmp;
				lt ++;
				rt --;
				
			}
		}
		 answer = String.valueOf(x);
		
		 return answer;
	
		
	
	}

	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();

		System.out.println(T.result(inputString));

	}

}
