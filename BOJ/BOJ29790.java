import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #29790 임스의 메이플컵
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		boolean flag = false;
		int sum = 0;
		if (Integer.parseInt(st.nextToken()) >= 1000) {
			sum++;
			flag = true;
		}
		int U = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		if (U >= 8000 || L >= 260)
			sum++;
		if (sum == 2) {
			System.out.println("Very Good");
		} else if (sum == 1 && flag) {
			System.out.println("Good");
		} else {
			System.out.println("Bad");
		}
	}
}
