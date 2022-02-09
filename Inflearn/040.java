package codingTest_040;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public int solution(String str) {
		int answer = 0;
		Stack<Character> stk = new Stack<>();
		char tmp = ' ';
		int num = 0;
		for(char x : str.toCharArray()) {
			
			
			if(x == ')') {
				if(tmp == '(') {
					num--;
					tmp = x;
					answer += num ;
				}
				else {
					num --;
					answer ++;
					
				}

			}
			else {
				stk.push(x);
				tmp = x;
				num ++;
				
			}
		}
		
		
		
		return answer; 
	}
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		System.out.println(T.solution(str));
	}
	
}
