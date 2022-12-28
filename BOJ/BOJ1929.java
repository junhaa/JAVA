import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1929 소수 구하기 
public class Main {

	static void solution(int M, int N) {
		boolean[] ch = new boolean[N + 1];
		StringBuilder sb = new StringBuilder();
		ch[1] = true;
		for(int i = 2 ; i <= (int)Math.sqrt(N) ; i ++) {
			for(int k = i * 2 ; k <= N ; k += i) {
				ch[k] = true;
			}
		}
		for(int i = M ; i <= N ; i ++) {
			if(!ch[i]) sb.append(i).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		T.solution(M, N);
	}
}
