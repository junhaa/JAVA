import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// BOJ #9012 °ýÈ£
public class Main {

	static boolean solution(String input) {
		Stack<Boolean> stack = new Stack<>();
		for(int i = 0 ; i < input.length() ; i ++) {
			if(input.charAt(i) == '(') stack.push(true);
			else {
				if(stack.isEmpty()) return false;
				stack.pop();
			}
		}
		if(stack.isEmpty()) return true;
		else return false;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(test -- > 0) {
			String input = br.readLine();
			boolean ch = T.solution(input);
			if(ch) sb.append("YES").append('\n');
			else sb.append("NO").append('\n');
		}
		System.out.println(sb);
	}
}
