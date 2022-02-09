package codingTest_035;

import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

	public int solution(int num1 , int num2 , int[] arr) {
		
		int answer = -1;
		TreeSet<Integer> Tset = new TreeSet<>(Collections.reverseOrder());
		
		for(int i = 0 ; i< num1 ; i++) {
			for(int k= i+1 ; k < num1 ; k ++) {
				for(int t = k+1 ; t < num1 ; t++) {
					Tset.add(arr[i] + arr[k] + arr[t]);
				}
			}
		}
	
		
		int cnt = 1;
		for(int x : Tset) {
			if(cnt == num2) {
				return x;
			}
			cnt++;
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int[] arr = new int[num1];
		for(int i = 0 ; i < num1 ; i ++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(T.solution(num1, num2, arr));
		
		
		
	}
}
