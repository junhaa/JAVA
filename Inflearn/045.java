package codingTest_045;

import java.util.Scanner;

public class Main {

	public int[] solution(int num, int[] arr) {


		int lt = 0; 
		
		for(int i = num-1 ; i > 0 ; i --) {
			lt = 0 ;
			for(int k = 0 ; k < i ; k++) {
				
				if(arr[lt] > arr[lt + 1]) {
					int tmp = arr[lt + 1];
					arr[lt + 1] = arr[lt];
					arr[lt] = tmp;
				}
				lt++;
			}
			
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
		for(int i = 0 ; i < num ; i++) {
			System.out.print(T.solution(num, arr)[i] + " ");
		}
		
		
	}
	
}
