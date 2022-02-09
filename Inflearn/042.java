package codingTest_042;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public String solution(String str1, String str2) {
		
		String answer="NO";
		Queue<Character> Q = new LinkedList<>();
		
		for(int i = 0 ; i < str1.length(); i++) {
			Q.offer(str1.charAt(i));
		}
		
		
		for(int k = 0 ; k < str2.length(); k++) {
			if(!Q.isEmpty() && str2.charAt(k) == Q.peek()) {
				Q.poll();
			}
		}
		
		if(Q.isEmpty()) {
			return "YES";
		}
			
		
		
	return answer;	
	}
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		String str1 = sc.next();
		String str2 = sc.next();
		
		System.out.println(T.solution(str1, str2));
	}
}
