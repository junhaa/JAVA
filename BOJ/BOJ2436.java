import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2436 °ø¾à¼ö
public class Main {
	static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
	
	static StringBuilder solution(int num1, int num2) {
		StringBuilder sb = new StringBuilder();
		int tmp = num2 / num1;
		int num = (int)Math.sqrt(tmp);
		while(true) {
			if(tmp % num == 0 && gcd(num, tmp / num) == 1) {
				sb.append(num * num1 + " " + (tmp / num) * num1);
				return sb;
			}
			num --;
		}
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(num1, num2));
	}
}
