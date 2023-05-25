import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #14888 연산자 끼워넣기 
public class Main {
	static int[] d = new int[4], arr; // + - * / 
	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	
	static void backTracking(int sum, int L) {
		if(L == N - 1) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		if(d[0] > 0) {
			d[0] --;
			backTracking(sum + arr[L + 1], L + 1);
			d[0] ++;
		}
		if(d[1] > 0) {
			d[1] --;
			backTracking(sum - arr[L + 1], L + 1);
			d[1] ++;
		}
		if(d[2] > 0) {
			d[2] --;
			backTracking(sum * arr[L + 1], L + 1);
			d[2] ++;
		}
		if(d[3] > 0) {
			d[3] --;
			backTracking(sum / arr[L + 1], L + 1);
			d[3] ++;
		}
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
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 4 ; i ++) {
			d[i] = Integer.parseInt(st.nextToken());
		}
		T.backTracking(arr[0], 0);
		System.out.println(max + "\n" + min);
	}
}
