package codingTest_048;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {

	public char solution(int num , int[] arr) {
		
		char answer = 'U';
		TreeSet<Integer> Tset = new TreeSet<>();
		
		for(int x : arr) {
			Tset.add(x);
		}
		
		if(Tset.size() != num) {
			return 'D';	
		}
		
		return answer;
	}
	
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		int[] arr = new int[num];
		for(int i = 0 ; i < num; i ++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(T.solution(num, arr));
	}
	
	
}