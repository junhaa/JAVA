import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #30999 민주주의
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int answer = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			int sum = 0;
			for (int j = 0; j < M; j++) {
				if (input.charAt(j) == 'O') {
					sum++;
				}
			}
			if (sum >= (double)M / 2) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}
