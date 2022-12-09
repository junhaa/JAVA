import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a%b);
	}
	
	static String ring(int first, int tmp) {
		int g = gcd(first, tmp);
		return String.format("%d/%d", first/g, tmp/g );
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N - 1 ; i ++) {
			sb.append(ring(first, Integer.parseInt(st.nextToken())) + "\n");
		}
		System.out.println(sb);
	}
}
