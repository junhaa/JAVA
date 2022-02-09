package codingTest_044;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	
	public ArrayList<Integer> solution(int num, int[] arr){	
		ArrayList<Integer> answer = new ArrayList<>();
		
		for(int i = 0 ; i < num ; i++) {
			answer.add(arr[i]);
		}
		Collections.sort(answer);
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
		for(int i = 0 ; i < num ; i ++) {
		
			System.out.print(T.solution(num, arr).get(i));
			System.out.print(" ");
		}
	}
	
}
