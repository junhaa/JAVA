package codingTest_039;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public int solution(String str) {
		
		int answer = 0 ;
		Stack<Integer> stk = new Stack<>();
		
		int lt = 0;
		int rt = 0;
		
		String tmp = "";
		int tmpInt =0;
		for(char x : str.toCharArray()) {
			if(x >= 48 && x <= 57) {
				tmp += x;
				tmpInt +=Integer.parseInt(tmp);
				stk.push(tmpInt);
				tmp ="";
				tmpInt = 0;
			}
			else {
				rt = stk.pop();
				lt = stk.pop();
				switch(x){
				case '+': 
					stk.push(lt + rt);
					break;
				
				case '-':
					stk.push(lt - rt);
					break;
					
				case '*':
					stk.push(lt * rt);
					break;
					
				case '/' : 
					stk.push(lt / rt);
					break;
					
				}
			}
			
		}
		answer = stk.pop();
		return answer;
	}
	
	public static void main(String[] args) {
		
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		System.out.println(T.solution(str));
		
	}
	
}
