package codingTest_014;

import java.util.Scanner;

public class Main {

	public int result(int num, String str) {
		
		String[] splNum = str.split(" ");
		int[] inputNum = new int[num];
		int tmp = Integer.MIN_VALUE;
		int answer = 0;
		
		
		for(int i = 0 ; i < num ; i ++) {
			inputNum[i] = Integer.parseInt(splNum[i]);
		}
		
		for(int i = 0 ; i < num ; i ++ ) {
			if(inputNum[i] > tmp) {
			
				tmp = inputNum[i];
				answer ++;
			
			}
				
				
			}
		
		return answer;
		
	}
	
	
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		String numStr = sc.nextLine();
		String str = sc.nextLine();
		int num = Integer.parseInt(numStr);
		
		System.out.println(T.result(num, str));
		
	}
	
}
