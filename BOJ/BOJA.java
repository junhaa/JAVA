import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ A 
public class Main {

	static int solution(int n, int m, int k) {
		int r = 0;
		while(n > 1) {
			n /= 2;
			r ++;
		}
		int a = 0;
		int tmp = k - 1;
		while(tmp >= 1) {
			tmp -= 1;
			tmp /= 2;
			a ++;
		}
		if(a + m > r) {
			return r;
		}
		else return a + m;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(N, M, K));
	}
	
}	
