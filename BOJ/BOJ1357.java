import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1357 뒤집힌 덧셈
public class Main {

	static int rev(String input) {
		String tmp = "";
		for(int i = input.length() - 1 ; i >= 0 ; i --) {
			tmp += input.charAt(i);
		}
		return Integer.valueOf(tmp);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String x = st.nextToken();
		String y = st.nextToken();
		int sum = T.rev(x) + T.rev(y);
		System.out.println(T.rev(String.valueOf(sum)));
	}
}
