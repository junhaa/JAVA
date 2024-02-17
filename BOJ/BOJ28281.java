import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #28281 선물
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int last = Integer.parseInt(st.nextToken());
		Long min = Long.MAX_VALUE;
		for (int i = 0; i < N - 1; i++) {
			int cur = Integer.parseInt(st.nextToken());
			min = Math.min(min, last + cur);
			last = cur;
		}
		System.out.println(min * X);
	}
}
