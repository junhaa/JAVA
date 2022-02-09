package codingTest_020;

import java.util.Scanner;

public class Main {

	public String result(int num, int[] arr) {

		int[] answer = new int[num];
		String ansStr = "";
		int tmp = 1;
		for (int i = 0; i < num; i++) {

			answer[i] = 1;

			for (int k = 0; k < num; k++) {

				if (arr[k] > arr[i] && i != k) {
					answer[i]++;

				}
			}
		}

		for (int i = 0; i < num; i++) {
			ansStr += answer[i];
			ansStr += " ";
		}

		return ansStr;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[] arr = new int[num];

		for (int i = 0; i < num; i++) {
			arr[i] = sc.nextInt();
		}

		System.out.println(T.result(num, arr));

	}

}
