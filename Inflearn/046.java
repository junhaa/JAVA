package codingTest_046;

import java.util.Scanner;

public class Main {

	public int[] solution(int num, int[] arr) {
		
		int tmp; 
		int k;
		for(int i = 1; i < num ; i ++) {
			tmp = arr[i];
			for(k = i - 1 ; k >=0 ; k -- ) {
				if(arr[k] > tmp) {
					arr[k+1] = arr[k];
				}
				else break;
			}
			arr[k+1] = tmp;
		}
		return arr;
	}
	
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		int[] arr = new int[num];
		for(int i = 0 ; i < num ; i ++) {
			arr[i] = sc.nextInt();
		}
		for(int i = 0 ; i < num ; i ++) {
			System.out.print(T.solution(num, arr)[i] + " ");
		}
		
		
	}
}
