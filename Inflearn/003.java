package codingTest_003;

import java.util.Scanner;

public class Main {

	public String result(String str1) {

		String answer = "";
		int num = Integer.MIN_VALUE;
		String[] splitStr = str1.split(" ");
		for (String x : splitStr) {
			int len = x.length();
			if (len > num) {
				num = len;
				answer = x;

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
