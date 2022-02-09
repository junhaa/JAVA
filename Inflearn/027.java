package codingTest_027;

import java.util.Scanner;

public class Main {

	public int result(int num1, int num2, int[] arr) {

		int sum = 0, answer = 0;

		for (int i = 0; i < num2; i++) {
			sum += arr[i];
		}

		answer = sum;

		for (int i = 1; i <= num1 - num2; i++) {
			sum -= arr[i - 1];
			sum += arr[i + (num2 - 1)];

			if (sum > answer) {
				answer = sum;
			}
		}
		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int[] arr = new int[num1];

		for (int i = 0; i < num1; i++) {
			arr[i] = sc.nextInt();
		}

		System.out.println(T.result(num1, num2, arr));

	}
}
