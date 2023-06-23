import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #17952 과제는 끝나지 않아!
class Homework{
	int d, s;
	public Homework(int s, int d){
		this.d = d;
		this.s = s;
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Homework> stack = new Stack<>();
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(st.countTokens() != 1) {
				st.nextToken();
				stack.add(new Homework(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			if(!stack.isEmpty()) {
				Homework tmp = stack.pop();
				if(--tmp.d == 0) {
					answer += tmp.s;
				}
				else stack.add(tmp);
			}
		}
		System.out.println(answer);
	}
}	
