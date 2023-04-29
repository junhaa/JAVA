import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #15658 연산자 끼워넣기 (2)
public class Main {

	static int N, arr[], max[] = new int[4], opr[], curOpr[] = new int[4], mn = Integer.MAX_VALUE, mx = Integer.MIN_VALUE; // + - * /
	
	static void backTracking(int L, int sum) {
		if(L == N) {
			mx = Math.max(sum, mx);
			mn = Math.min(sum, mn);
			return;
		}
		if(curOpr[0] < max[0]) {
			curOpr[0] ++;
			backTracking(L + 1, sum + arr[L]);
			curOpr[0] --;
		}
		if(curOpr[1] < max[1]) {
			curOpr[1] ++;
			backTracking(L + 1, sum - arr[L]);
			curOpr[1] --;
		}
		if(curOpr[2] < max[2]) {
			curOpr[2] ++;
			backTracking(L + 1, sum * arr[L]);
			curOpr[2] --;
		}
		if(curOpr[3] < max[3]) {
			curOpr[3] ++;
			backTracking(L + 1, sum / arr[L]);
			curOpr[3] --;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		opr = new int[N - 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 4 ; i ++) {
			max[i] = Integer.parseInt(st.nextToken());
		}
		T.backTracking(1, arr[0]);
		System.out.println(mx + "\n" + mn);
	}
}
