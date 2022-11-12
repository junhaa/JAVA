import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #1989 부분배열 고르기 2
class Number{
	long sum;
	int idx;
	public Number(long sum, int idx) {
		this.sum = sum;
		this.idx = idx;
	}
}
public class Main {

	static int[] h ;
	static long ans = 0, sum = 0;
	static int sIdx = 0, eIdx = 0;
	
	
	static void solution(int N) {
		Stack<Number> s = new Stack<>();
		s.push(new Number(0, 0));
		for (int i = 1; i <= N + 1; i++)
		{
			sum += h[i];
			long tmpSum = -1;
			int tmpIdx = -1;
			while (!s.empty() && h[s.peek().idx] > h[i])
			{
				if(tmpSum == -1) {
					tmpSum = s.peek().sum;
					tmpIdx = s.peek().idx;
				}
				int check = s.peek().idx;
		        	s.pop();
		        if(ans < h[check] * (tmpSum - s.peek().sum)) {
		        	ans = h[check] * (tmpSum - s.peek().sum);
		        	sIdx = s.peek().idx;
		        	eIdx = tmpIdx - 1;
		        }
				ans = Math.max(ans, h[check]*(tmpSum - s.peek().sum));
			}
			s.push(new Number(sum, i));
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = new int[N + 2];
		for(int i = 1 ; i <= N ; i ++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		T.solution(N);
		System.out.println(ans + "\n" + (sIdx + 1) + " " + (eIdx + 1));
	}
}
