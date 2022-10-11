import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 1850 최대공약수
public class Main {

	static int gcd(long a, long b) {
		long r;
		while(b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return (int)a;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		int num = T.gcd(A, B);
		for(int i = 0 ; i < num ; i ++) {
			sb.append(1);
		}
		System.out.println(sb);
	}
}
