import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// BOJ #3986 좋은 단어
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Character> s = new Stack<>();
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int j = 0 ; j < input.length() ; j ++) {
				if(s.isEmpty() || s.peek() != input.charAt(j)) {
					s.push(input.charAt(j));
				}
				else if(!s.isEmpty() && s.peek() == input.charAt(j)){
					s.pop();
				}
			}
			if(s.isEmpty()) {
				answer ++;
			}
			else {
				s.clear();
			}
		}
		System.out.println(answer);
	}
}
