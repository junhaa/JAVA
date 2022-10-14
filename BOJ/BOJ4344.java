import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

// BOJ #4344 평균은 넘겠지
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			double sum = 0;
			int[] arr = new int[N];
			for(int i = 0 ; i < N ; i ++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum += arr[i];
			}
			sum /= N;
			double student = 0;
			for(int i = 0 ; i < N ; i ++) {
				if(arr[i] > sum) student ++;
			}
			student /= N;
			sb.append(String.format(Locale.getDefault(), "%.3f", student * 100)).append("%" + "\n");			
		}
		System.out.println(sb);
	}
}
