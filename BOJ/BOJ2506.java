import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2506 점수계산
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int seq = 0;
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (Integer.parseInt(st.nextToken()) == 1) {
				answer += ++seq;
			} else
				seq = 0;
		}
		System.out.println(answer);
	}
}
