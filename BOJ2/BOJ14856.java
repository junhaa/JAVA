import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
// BOJ #14856 조금 똑똑한 뢰벗과 조금 잘생긴 사냐
public class Main {
	static long fibo[] = new long[88];

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Long N = Long.parseLong(br.readLine());
		main.solve(N);
	}

	private void init(){
		fibo[0] = 1;
		fibo[1] = 1;
		for(int i = 2 ; i < 88 ; i ++){
			fibo[i] = fibo[i - 1] + fibo[i - 2];
		}
	}

	private void solve(long N){

		StringBuilder sb = new StringBuilder();
		Stack<Long> stack = new Stack<>();
		for(int i = 87 ; i > 0 ; i --){
			if(N >= fibo[i]) {
				N -= fibo[i];
				stack.push(fibo[i]);
			}
		}
		int len = stack.size();
		sb.append(len + "\n");
		if(N != 0){
			sb.append("-1\n");
			return;
		}

		for(int i = 0 ; i < len ; i ++){
			sb.append(stack.pop() + " ");
		}
		System.out.println(sb);
	}
}
