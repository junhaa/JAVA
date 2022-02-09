package codingTest_019;

import java.util.Scanner;

public class Main {

	
	public int result (int num, int[] arr) {
		int answer = 0; 
		int tmp = 0;
		for(int i =0 ; i < num ; i ++) {
			if(arr[i] == 1) {
				tmp++;
				answer+= tmp;
			}
			else tmp = 0 ;
		}
		
		
		return answer;
		
	}
	
	
	
	public static void main(String[] args) {
	
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		int[] arr = new int[num];
		
		for(int i = 0 ; i < num ; i ++) {
			arr[i] = sc.nextInt(); 
		}
		
		System.out.println(T.result(num, arr));
		
	}


}
