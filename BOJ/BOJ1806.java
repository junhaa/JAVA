import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1806 ºÎºÐÇÕ
public class Main {
	static int[] arr;
	public static int solution(int N, int S) {
		int answer = Integer.MAX_VALUE;
		int lt = 0, rt = 0;
		boolean ch = false;
		int sum = arr[rt];
		while(rt < N) {
			if(sum < S) {
				if(rt < N - 1) sum += arr[++rt];
				else break;
			}
			else {
				answer = Math.min(answer, rt - lt + 1);
				ch = true;
				sum -= arr[lt++];
			}
		}
		if(ch) return answer;
		else return 0;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(T.solution(N, S));
	}
}
