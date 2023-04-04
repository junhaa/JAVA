import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #3079 입국심사
public class Main {

	static int[] time;
	static int M, N;
	
	
	static long binarySearch(int min) {
		long lt = 0;
		long rt = M * (long)min;
		while(lt <= rt) {
			long mid = (lt + rt) / 2;
			long cnt = 0;
			for(int i = 0 ; i < N ; i ++) {
				cnt += mid / time[i];
			}
			if(cnt >= M) {
				rt = mid - 1;
			}
			else {
				lt = mid + 1;
			}
		}
		return lt;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		time = new int[N];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			time[i] = Integer.parseInt(br.readLine());
			min = Math.min(min, time[i]);
		}
		System.out.println(T.binarySearch(min));
	}
}
