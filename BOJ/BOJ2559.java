import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2559 수열
public class Main {

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		int[] arr = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < K ; i ++) {
			sum += arr[i];
		}
		max = Math.max(sum, max);
		for(int i = 0 ; i < N - K ; i ++) {
			sum -= arr[i];
			sum += arr[i + K];
			max = Math.max(sum, max);
		}
		System.out.println(max);
	}
}
