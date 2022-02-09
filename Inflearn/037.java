package codingTest_037;

import java.util.Scanner;

public class Main {

	public String solution(String str) {
		
		String answer = "";
		
		int lt = 0, rt = 0;
		
		for(char x : str.toCharArray()) {
			if(x == '(') {
				lt ++;
			}
			else if (x == ')') {
				rt ++;
			}

			if(lt - rt == 0 && x != '(' && x != ')') {
				answer += x;
			}
			
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc =  new Scanner(System.in);
		
		String str = sc.next();
		
		System.out.println(T.solution(str));
		
	}
}
