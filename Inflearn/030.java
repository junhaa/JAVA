package codingTest_030;

import java.util.Scanner;

public class Main {

	public int result(int num1, int num2, int[] arr) {

		int answer = 0;
		int lt = 0;
		int rt = 0;
		int cnt = 0;
		int num = Integer.MIN_VALUE;
		while (rt < num1) {

			if (arr[rt] == 0) {
				cnt++;
			}

			if (cnt > num2) {
				while (true) {
					if (arr[lt] == 0) {

						cnt--;
						arr[lt] = 1;

						break;

					} else if (arr[lt] == 1) {
						lt++;
					}
				}
			}

			answer = rt - lt;

			if (answer > num) {
				num = answer;
			}
			rt++;

		}

		return num;

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
