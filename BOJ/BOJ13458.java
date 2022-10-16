import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #13458 시험 감독
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long answer = 0;
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] -= B;
		}
		answer += N;
		for(int i = 0 ; i < N ; i ++) {
			if(arr[i] > 0) {
				int tmp = arr[i];
				answer += tmp / C;
				if(tmp % C != 0) answer ++;
			}
		}
		System.out.println(answer);
	}
}
