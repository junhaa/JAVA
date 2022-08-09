import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// BOJ #1918 후위 표기식
class Operator{
	char oper;
	int opD;
	int degree;
	public Operator(char oper, int opD, int degree) {
		this.oper = oper;
		this.opD = opD;
		this.degree = degree;
	}
}
public class Main {
	static StringBuilder sb = new StringBuilder();
	static Stack<Operator> stack = new Stack<>();
	static Queue<Character> Q = new LinkedList<>();
	static void solution(String str) {
		int opD = 0;
		int degree = 0;
		for(int i = 0 ; i < str.length() ; i ++) {
			char tmpChar = str.charAt(i);
			if(Character.isAlphabetic(tmpChar)) {
				Q.offer(tmpChar);
			}
			else if(tmpChar == '(') {
				degree ++;
				opD = 0;
			}
			else if(tmpChar == ')') {
				while(!stack.isEmpty()) {
					Operator pTmp = stack.pop();
					while(!Q.isEmpty()) {
						sb.append(Q.poll());
					}
					if(pTmp.degree == degree) sb.append(pTmp.oper);
					else {
						opD = pTmp.opD; 
						stack.push(pTmp);
						break;
					}
				}
				degree --;
			}
			else {
				switch (tmpChar) {
				case '-':
				case '+':{
					if(opD != 0) { // 높은 차수
						while(!Q.isEmpty()) {
							sb.append(Q.poll());
						}
						while(!stack.isEmpty()) {
							Operator pTmp = stack.pop();
							if(pTmp.degree == degree) sb.append(pTmp.oper);
							else {
								stack.push(pTmp);
								break;
							}
						}
						stack.push(new Operator(tmpChar, 1, degree));
						opD = 1;
					}
					else {
						stack.push(new Operator(tmpChar, 1, degree));
						opD = 1;
					}
					break;
				}
				case '*':
				case '/':{
					if(opD == 2) { // 같은 차수
						while(!Q.isEmpty()) {
							sb.append(Q.poll());
						}
						Operator ptmp = stack.pop();
						if(ptmp.degree == degree && ptmp.opD == 2) sb.append(ptmp.oper);
						else stack.push(ptmp);
						stack.push(new Operator(tmpChar, 2, degree));
						opD = 2;
					}
					else if(opD == 1) { // 낮은 차수
						stack.push(new Operator(tmpChar, 2, degree));
						opD = 2;
					}
					else {
						stack.push(new Operator(tmpChar, 2, degree));
						opD = 2;
					}
					break;
				}
				}
			}
		}
		while(!Q.isEmpty()) sb.append(Q.poll());
		while(!stack.isEmpty()) sb.append(stack.pop().oper);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		T.solution(str);
		System.out.println(sb);
	}
}
