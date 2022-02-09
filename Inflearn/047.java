package codingTest_047;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public int[] solution(int num1, int num2, int[] arr) {
		
		int[] arr2 = new int[num1];
		for(int i =0 ; i< num1; i++) {
			arr2[i] = 0;
		}
		int cnt = 0;
		int idx = 0;
		for(int i = 0 ; i < num2; i ++) {
				cnt = 0;
				idx = 0;
			for(int k = 0 ; k < num1; k ++) {
				if(arr[i] == arr2[k]) {
					cnt++;
					break;
				}
				idx ++;
				
				
			}
			if(cnt == 0) {
				for(int t = num1 - 1; t >= 1 ; t --) {
					arr2[t] = arr2[t-1];
				}
				arr2[0] = arr[i]; 	
			}
			else {
				for(int e = idx; e >= 1; e-- ) {
					arr2[e] = arr2[e-1];
				}
			
				arr2[0] = arr[i];
			}
		}
			
			
		return arr2;
		
	}
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		
		int[] arr = new int[num2];
		for(int i = 0 ; i < num2 ; i ++) {
			arr[i] = sc.nextInt();
		}
		for(int i = 0 ; i < num1; i ++) {
		System.out.print(T.solution(num1, num2, arr)[i] + " ");
		}
	}
	
}
