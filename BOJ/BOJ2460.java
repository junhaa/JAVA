import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2460 지능형 기차 2
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = 0;
		int cur = 0;
		StringTokenizer st;
		for(int i = 0 ; i < 10 ; i ++) {
			st = new StringTokenizer(br.readLine());
			cur -= Integer.parseInt(st.nextToken());
			cur += Integer.parseInt(st.nextToken());
			max = Math.max(max, cur);
		}
		System.out.println(max);
	}
}
