import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// BOJ #10773 제로
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int sum = 0;
		while(K -- > 0) {
			int tmp = Integer.parseInt(br.readLine());
			if(tmp != 0) {
				stack.push(tmp);
			}
			else {
				stack.pop();
			}
		}
		int length = stack.size();
		for(int i = 0 ; i < length ; i ++) {
			sum += stack.pop();
		}
		System.out.println(sum);
	}
}
