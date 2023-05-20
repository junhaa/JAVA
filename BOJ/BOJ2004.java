import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2004 조합 0의 개수
public class Main {

	static int getP5(int k) {
		int p5 = 0;
		for(long i = 5 ; i <= k ; i *= 5) {
			p5 += k / i;
		}
		return p5;
	}
	static int getP2(int k) {
		int p2 = 0;
		for(long i = 2 ; i <= k ; i *= 2) {
			p2 += k / i;
		}
		return p2;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		System.out.println(Math.min(T.getP2(n) - T.getP2(n - m) - T.getP2(m), T.getP5(n) - T.getP5(n - m) - T.getP5(m)));
	}
}
