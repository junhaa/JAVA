package codingTest_028;

import java.util.Scanner;

public class Main {

	public int result(int num1, int num2, int[] arr) {

		int answer = 0;
		int sum = 0;
		int tmp = 0; // ¸Ç ¾Õ i
		int tmp2 = 1;

		sum = arr[0];

		while (true) {

			if (sum > num2) {
				sum -= arr[tmp++];

			} else if (sum == num2) {
				answer++;
				sum -= arr[tmp++];

				// System.out.println(answer);
			} else {
				sum += arr[tmp2++];

			}

			if (tmp2 == num1 && sum < num2)
				break;
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
