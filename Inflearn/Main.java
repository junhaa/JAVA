package codingTest_056;

import java.util.Scanner;

public class Main {
	
	static int N, C, answer = Integer.MIN_VALUE;
	
	public void DFS (int L, int[] arr, int sum) {
		if(sum > C) return;	
		if(L == N) {
			if(sum > answer) answer = sum;
		}
		else {
			sum += arr[L];
			DFS(L+1, arr, sum);
			sum -= arr[L];
			DFS(L+1, arr, sum);
		}
	}

	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		C = sc.nextInt();
		N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N ; i ++) {
			arr[i] = sc.nextInt();
		}
		T.DFS(0, arr, 0);
		System.out.println(answer);
		
		
	}
	
}
