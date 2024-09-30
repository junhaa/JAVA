import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * p_0 * A^0 => 0 ~ M - 1 까지 한번씩 나옴
 * 이후의 P_1 ~ P_(n-1) 까지는 mod 연산 때문에 반복
 */
// BOJ #26008 해시 해킹
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		br.readLine();

		long mul = 1;
		for(int i = 0 ; i < N - 1 ; i ++){
			mul *= M;
			mul %= 1000000007;
		}
		System.out.println(mul);
	}
}
