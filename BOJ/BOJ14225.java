import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #14225 부분수열의 합
public class Main {

	static int N, arr[];
	static HashSet<Integer> set = new HashSet<>();
	
	static void backTracking(int L, int sum) {
		if(L == N) {
			set.add(sum);
			return;
		}
		sum += arr[L];
		backTracking(L + 1, sum);
		sum -= arr[L];
		backTracking(L + 1, sum);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		T.backTracking(0, 0);
		int cur = 1;
		while(true) {
			if(!set.contains(cur)) {
				System.out.println(cur);
				return;
			}
			cur ++;
		}
	}
}
