package codingTest_024;

import java.util.Scanner;

public class Main {

	public int result(int num1, int num2, int[][] arr) {

		int answer = 0;
		int tmp = 0;
		int tmp2 = 0;

		int[][] arr2 = new int[num2][num1]; // num1 가로 num2 세로

		for (int i = 0; i < num2; i++) {
			for (int k = 0; k < num1; k++) {
				arr2[i][arr[i][k] - 1] = k + 1;
			}
		}
		
		  //for(int i = 0 ; i < num2 ; i ++) { for(int k = 0 ; k < num1 ; k ++) {
		  //System.out.println(arr2[i][k]); } }
		 

		for (int i = 0; i < num1; i++) {
			for (int k = i + 1; k < num1; k++) {
				tmp = 0;
				tmp2 = 0;
				for (int t = 0; t < num2; t++) {
					if (arr2[t][i] > arr2[t][k]) {
						tmp++;
					} else if (arr2[t][k] > arr2[t][i]) {
						tmp2++;
					}
				}
				if (tmp == num2 || tmp2 == num2 ) {
					answer++;
				}

			}
		}
		return answer;
	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);

		int num1 = sc.nextInt();
		int num2 = sc.nextInt();

		int[][] arr = new int[num2][num1];

		for (int i = 0; i < num2; i++) {
			for (int k = 0; k < num1; k++) {
				arr[i][k] = sc.nextInt(); // k num1 가로 i num2 세로 [세로][가로]
			}
		}
		/*
		 * for(int i = 0 ; i < num2 ; i ++ ) { for(int k = 0 ; k < num1 ; k ++ ) {
		 * System.out.println(arr[i][k]); } }
		 */

		System.out.println(T.result(num1, num2, arr));

	}

}
