import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #9093 단어 뒤집기
public class Main {

	static StringBuilder sb = new StringBuilder();
	
	static String reverse(String input) {
		String tmp = "";
		for(int i = input.length() - 1 ; i >= 0 ; i --) {
			tmp += input.charAt(i);
		}
		return tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int length = st.countTokens();
			for(int i = 0 ; i < length ; i ++) {
				sb.append(T.reverse(st.nextToken()) + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
