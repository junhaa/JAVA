import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// BOJ #1874 스택 수열
public class Main {
	static int[] arr;
	static StringBuilder solution(int n) {
		boolean flag = true;
		int idx = 0;
		int num = 1;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		stack.push(num ++);
		sb.append('+').append('\n');
		while(idx < n) {
			int tmp = arr[idx];
			int pop = stack.pop();
			if(tmp == pop) {
				sb.append('-').append('\n');
				idx ++;
				if(stack.isEmpty() && idx < n) { 
					stack.push(num ++);
					sb.append('+').append('\n');
				}
			}
			else if(tmp > pop) {
				sb.append('+').append('\n');
				stack.push(pop);
				stack.push(num++);
			}
			else {
				flag = false;
				break;
			}
			
		}
		if(!flag) return new StringBuilder("NO");
		else return sb;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		for(int i = 0 ; i < n ; i ++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(T.solution(n));
	}
}
