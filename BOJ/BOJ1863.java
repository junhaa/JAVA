import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #1863 스카이라인 쉬운거
public class Main {

	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int answer = 0;
		for(int i = 0 ; i <= N ; i ++) {
			int tmp;
			if(i == N) {
				tmp = 0;
			}
			else {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				tmp = Integer.parseInt(st.nextToken());
			}
			if(stack.isEmpty() || stack.peek() < tmp) {
				stack.add(tmp);
			}
			if(stack.peek() > tmp) {
				while(!stack.isEmpty() && stack.peek() > tmp) {
					stack.pop();
					answer ++;
				}
				if(stack.isEmpty() || stack.peek() != tmp) {
					stack.add(tmp);
				}
			}
//			if(stack.isEmpty()) {
//				answer ++;
//				stack.add(tmp);
//			}
//			else if(stack.peek() > tmp) {
//				if(tmp == 0) {
//					stack.clear();
//				}
//				else {
//					while(true) {
//						if(stack.isEmpty() || stack.peek() <= tmp) break;
//						stack.pop();
//					}
//					if(stack.isEmpty()) { 
//						stack.add(tmp);
//						answer ++;
//					}
//					else {
//						if(stack.peek() != tmp) {
//							stack.add(tmp);
//							answer ++;
//						}
//					}
//				}
//			}
//			else if(stack.peek() < tmp) {
//				stack.add(tmp);
//				answer ++;
//			}
		}
		System.out.println(answer);
	}
}
