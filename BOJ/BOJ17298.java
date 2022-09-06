import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #17298 ¿ÀÅ«¼ö
class Onum{
	int idx;
	int num;
	public Onum(int idx, int num) {
		this.idx = idx;
		this.num = num;
	}
}
public class Main { 
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Onum> stack = new Stack<>();
		for(int i = 0 ; i < N ; i ++) {
			Onum tmp = new Onum(i, Integer.parseInt(st.nextToken()));
			if(stack.isEmpty() || stack.peek().num > tmp.num) {
				stack.push(tmp);
			}
			else {
				while(stack.peek().num < tmp.num) {
					Onum num = stack.pop();
					arr[num.idx] = tmp.num;
					if(stack.isEmpty()) break;
				}
				stack.push(tmp);
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			int tmp = arr[i];
			if(tmp == 0) sb.append("-1 ");
			else sb.append(arr[i] + " ");
		}
		System.out.println(sb);
	}
}
