package codingTest_021;

import java.util.Scanner;

public class Main {

	public int result(int num, int[][] arr) {

		int answer = 0, tmp2 = 0;
		for (int i = 0; i < num; i++) {
			for (int t = 0; t < num; t++) {
				tmp2 += arr[i][t];
			}
			
			if (tmp2 > answer) {
				answer = tmp2;
				tmp2 = 0;
			} else
				tmp2 = 0;

		}
		for (int i = 0; i < num; i++) {
			for (int t = 0; t < num; t++) {
				tmp2 += arr[t][i];
			}
			
			if (tmp2 > answer) {
				answer = tmp2;
				tmp2 = 0;
			} else
				tmp2 = 0;

		}
		for (int i = 0; i < num; i++) {
			tmp2 += arr[i][i];
		}
		if (tmp2 > answer) {
			answer = tmp2;
			tmp2 = 0;
		} else
			tmp2 = 0;

		for (int i = 0; i < num; i++) {
			tmp2 += arr[num - 1 - i][i];

		}
		
		
		if (tmp2 > answer) {
			answer = tmp2;
			tmp2 = 0;
		} else
			tmp2 = 0;

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
