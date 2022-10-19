import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1546 평균
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		double[] arr2 = new double[N];
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		for(int i = 0 ; i < N ; i ++) {
			arr2[i] = (double)arr[i] / max * 100;
		}
		double sum = 0;
		for(int i = 0 ; i < N ; i ++) {
			sum += arr2[i];
		}
		System.out.println(sum / N);
	}
}
