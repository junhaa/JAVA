import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #22862 가장 긴 짝수 연속한 부분 수열 (large)
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int lt = 0;
		int rt = -1;
		int cnt = 0;
		int answer = Integer.MIN_VALUE;
		while(rt < N) {
			if(cnt <= K) {
				if(rt == N - 1) break;
				if(arr[++rt] % 2 == 1) {
					cnt ++;
				}
			}
			else {
				if(arr[lt++] % 2 == 1) {
					cnt --;
				}
			}
			if(cnt <= K) {
				answer = Math.max(answer, rt - lt + 1 - cnt);
			}
		}
		System.out.println(answer);
	}
}
