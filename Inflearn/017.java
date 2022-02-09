package codingTest_017;

import java.util.Scanner;

public class Main {

	public String result(int num, String str) {

		String answer = "";
		String tmp = "";
		String[] splStr = str.split(" ");
		int tmpInt = 0;
		for (int i = 0; i < num; i++) {
			tmp += new StringBuilder(splStr[i]).reverse().toString();
			tmp += " ";
		}
		String revStr[] = tmp.split(" ");
		int revInt[] = new int[num];
		for (int i = 0; i < num; i++) {
			revInt[i] = Integer.parseInt(revStr[i]);

		}
		/*
		 * for(int i = 0; i < num ; i ++) {
		 * 
		 * System.out.println(revInt[i]); }
		 */
		for (int i = 0; i < num; i++) {
			for (int k = 2; k < revInt[i]; k++) {
							
				if (revInt[i] != 1 && revInt[i] % k == 0) {
					tmpInt = 1;
				}

				
			}
			if(revInt[i] == 1) {
				tmpInt = 1;
			}
			if (revInt[i] == 2) {
				tmpInt = 0;
			}
			if (tmpInt == 0) {
				answer += revInt[i];
				answer += " ";
			} else
				tmpInt = 0;
		}

		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		String inputNum = sc.nextLine();
		int num = Integer.parseInt(inputNum);
		String str = sc.nextLine();

		System.out.println(T.result(num, str));

	}
}
