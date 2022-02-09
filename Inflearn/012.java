package codingTest_012;

import java.util.Scanner;

public class Main {

	public String result(String num, String str) {

		String answer = "";

		int num1 = Integer.parseInt(num);

		String[] strArr = new String[num1];

		char[] arr = new char[num1];

		char[] inputStr = str.toCharArray();

		for (int i = 0; i < num1; i++) {
			strArr[i] = "";
		}

		for (int i = 0; i < num1; i++) {
			for (int k = 0; k < 7; k++) {
				if (inputStr[i * 7 + k] == '#') {
					strArr[i] += "1";
				} else if (inputStr[i * 7 + k] == '*') {
					strArr[i] += "0";
				}
			}
		}
		for (int i = 0; i < num1; i++) {
			for (int k = 0; k < 7; k++) {
				if (strArr[i].charAt(k) == '1') {
					arr[i] += (char) Math.pow(2, 6 - k);

				}
			}
		}

		for (int i = 0; i < num1; i++) {
			answer += arr[i];
		}

		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		String num = sc.nextLine();
		String str = sc.nextLine();

		System.out.println(T.result(num, str));

	}

}
