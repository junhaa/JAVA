package codingTest_051;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public int solution(int N, int M, int[] arr) {
		
		Arrays.sort(arr);
		int answer = 0;
		int mid = N /2 ;
		int cnt = 0;
		while(true) {
		if(arr[mid] > M) {
			mid --;
		}
		else if (arr[mid] == M) return mid;
		
		else mid ++;
		}
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = sc.nextInt();
			
		}
		System.out.println(T.solution(N, M, arr)+ 1);
	}
	
}
