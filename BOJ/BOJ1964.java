import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1964 오각형,오각형,오각형...
public class Main {
	public static long solution(int N) {
		long sum = 1;
		for(int i = 1 ; i <= N ; i ++) {
			sum += 4 + 3 * (i - 1);
		}
		return sum % 45678;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(T.solution(N));
	}
}
