package codingTest_016;

import java.util.Scanner;

public class Main {

	public String result(int num) {

		String answer = "";
		int arr[] = new int[num];
		for (int i = 0; i < num; i++) {
			if (i == 0 || i == 1) {
				answer += "1";
				answer += " ";
				arr[i] = 1;
			} else {

				answer += arr[i - 1] + arr[i - 2];
				answer += " ";
				arr[i] = arr[i - 1] + arr[i - 2];

			}

		}

		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		String inputNum = sc.nextLine();
		int num = Integer.parseInt(inputNum);

		System.out.println(T.result(num));

	}

}
