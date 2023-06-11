import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #17127 벚꽃이 정보섬이 피어난 이유
public class Main {

	static int[] prefix, arr;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		prefix = new int[N + 1];
		arr = new int[N];
		int sum = 0;
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			prefix[i + 1] = prefix[i] + tmp;
			arr[i] = tmp;
			sum += tmp;
		}
		int max = Integer.MIN_VALUE;
		int wsum = 1;
		for(int i = 0 ; i < N - 3 ; i ++) {
			wsum *= arr[i]; 
		}
		max = Math.max(sum - (prefix[N - 3] - prefix[0]) + wsum, max);
		
		for(int i = N - 3 ; i < N ; i ++) {
			wsum *= arr[i];
			wsum /= arr[i - (N - 3)];
			max = Math.max(sum - (prefix[i + 1] - prefix[i - (N - 3) + 1]) + wsum, max);
		}
		System.out.println(max);
	}
}
