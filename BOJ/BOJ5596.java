import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #5596 시험 점수
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s = 0;
		int t = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 4 ; i ++) {
			s += Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 4; i ++) {
			t += Integer.parseInt(st.nextToken());
		}
		System.out.println(Math.max(s, t));
	}
}
