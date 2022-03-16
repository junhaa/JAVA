package codingTest_055;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static String answer = "NO";
	static int n, total = 0;
	boolean flag = false;
	public void DFS(int L , int sum, int[] arr) {
		if(flag == true) return;
		if(sum > total/2) return;
		if(L == n) {
			if(total - sum == sum) {
				flag = true;
				answer = "YES";
			}
		}
		else {
			sum += arr[L];
			DFS(L+1, sum, arr);
			sum -= arr[L];
			DFS(L+1, sum, arr);
		}
		
		
	}
	
	public static void main(String[] args) {
	
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n ; i ++) {
			arr[i] = sc.nextInt();
			total += arr[i];
		}
		T.DFS(0,0, arr);
		System.out.println(answer);
	}
}
