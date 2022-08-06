import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1182 부분수열의 합
public class Main {
	static int answer = 0, N, S;
	static int arr[];
	static void DFS(int L, int sum) {
		if(L == N ) {
			if(sum == S) answer ++;
			return ;
		}
		else {
			sum += arr[L];
			DFS(L + 1, sum);
			sum -= arr[L];
			DFS(L + 1, sum);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N;  i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		T.DFS(0, 0);
		if(S == 0) answer --;
		System.out.println(answer);
	}
}
