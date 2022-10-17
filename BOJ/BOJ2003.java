import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2003 수들의 합 2
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int lt = 0;
		int rt = 0;
		int answer = 0;
		int sum = arr[0];
		while(true) {
			if(sum >= M) {
				if(sum == M) {
					answer ++;
				}
				sum -= arr[lt];
				lt ++;
			}
			else {
				if(rt < N - 1) {
					rt ++;
					sum += arr[rt];
				}
				else break;
			}
		}
		System.out.println(answer);
	}
}
