import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// BOJ #3015 오아시스 재결합
class Length{
	int len;
	int num;
	public Length(int len) {
		this.len = len;
		num = 1;
	}
}
public class Main {
	static Stack<Length> stack = new Stack<>();
	static long answer = 0;
	
	static void solution(Length input) {
		Length tmp = stack.peek();
		while(true) {
			if(tmp.len == input.len) {
				answer += tmp.num ++;
				input = stack.pop();
				if(!stack.isEmpty()) { 
					tmp = stack.peek();
				}
				else {
					stack.push(tmp);
					return;
				}
			}
			else if(tmp.len < input.len) {
				answer += tmp.num;
				stack.pop();
				if(!stack.isEmpty()) tmp = stack.peek();
				else {
					stack.push(input);
					return;
				}
			}
			else {
				answer ++;
				stack.push(input);
				return;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		stack.push(new Length(Integer.parseInt(br.readLine())));
		for(int i = 1 ; i < N ; i ++) {
			int tmp = Integer.parseInt(br.readLine());
			T.solution(new Length(tmp));
		}
		System.out.println(answer);
	}
}
