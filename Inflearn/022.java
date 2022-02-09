package codingTest_022;

import java.util.Scanner;

public class Main {

	public int result(int num, int[][] arr) {

		int answer = 0;
		int tmp = 0;
		int[][] arrAll = new int[num + 2][num + 2];
		for (int i = 0; i < num + 2; i++) {
			for (int k = 0; k < num + 2; k++) {
				arrAll[i][k] = 0;
			}
		}
		for (int i = 0; i < num; i++) {
			for (int k = 0; k < num; k++) {
				arrAll[i + 1][k + 1] = arr[i][k];
			}
		}
		for (int i = 1; i <= num; i++) {
			for (int k = 1; k <= num; k++) {
				if (arrAll[i][k] <= arrAll[i - 1][k]) {
					tmp = 1;
				} else if (arrAll[i][k] <= arrAll[i + 1][k]) {
					tmp = 1;
				} else if (arrAll[i][k] <= arrAll[i][k - 1]) {
					tmp = 1;
				} else if (arrAll[i][k] <= arrAll[i][k + 1]) {
					tmp = 1;
				}
				if (tmp == 0)
					answer++;
				else
					tmp = 0;
			}
		}

		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int num = sc.nextInt();
		int[][] arr = new int[num][num];

		for (int i = 0; i < num; i++) {
			for (int k = 0; k < num; k++) {
				arr[i][k] = sc.nextInt();
			}
		}

		System.out.println(T.result(num, arr));

	}
}
