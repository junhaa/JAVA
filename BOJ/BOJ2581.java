import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2581 ¼Ò¼ö
public class Main {
	static boolean ch[];

	static void solution(int M, int N) {
		ch[1] = true;
		int min = Integer.MAX_VALUE;
		int sum = 0;
		if (N >= 2) {
			for (int i = 2; i <= (int) Math.sqrt(N); i++) {
				for (int k = i * 2; k <= N; k += i) {
					ch[k] = true;
				}
			}
		}
		for(int i = M ; i <= N ; i ++) {
			if(!ch[i]) {
				if(min == Integer.MAX_VALUE) min = i;
				sum += i;
			}
		}
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else {
			System.out.println(sum);
			System.out.println(min);
		}
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		ch = new boolean[N + 1];
		T.solution(M, N);
	}
}
