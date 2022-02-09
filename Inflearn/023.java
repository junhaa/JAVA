package codingTest_023;

import java.util.Scanner;

public class Main {

	public int result(int num, int[][] arr) {

		int answer = 0;
		int[] student = new int[num + 1];
		int min = Integer.MIN_VALUE;
		
		for (int i = 0; i <= num ; i++) {
			int tmp2 = 0;
			for (int t =  1; t <= num; t++){
				for (int k = 1; k <= 5; k++) { 
					if (arr[i][k] == arr[t][k]) {
						tmp2 ++;
						break;
					}
				}
				
					
				}
			if(tmp2 > min) {
				min = tmp2;
				answer = i;
				
			}
		}
		/*
		for (int i = 1; i < num + 1; i++) {
			if (student[i] > tmp) {
				answer = i;
				tmp = student[i];
			}
		}
		*/
		return answer;

	}

	public static void main(String[] args) {

		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[][] arr = new int[num+1][6];

		for (int i = 1; i <= num; i++) {
			for (int k = 1; k <= 5; k++) {
				arr[i][k] = sc.nextInt();

			}
		}
		System.out.println(T.result(num, arr));

	}
}
