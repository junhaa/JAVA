import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2960 에라토스테네스의 체
public class Main {

	static int cnt = 0;
	static boolean ch[];
	
	static int solution(int N, int K) {
		for(int i = 2 ; i <= N ; i ++) {
			for(int k = i ; k <= N ; k += i) {
				if(!ch[k]) {
					if(++cnt == K) return k;
					ch[k] = true;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ch = new boolean[N + 1];
		System.out.println(T.solution(N, K));
	}
}
