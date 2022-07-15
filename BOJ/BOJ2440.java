import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #2440 º° Âï±â - 3
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = N ; i >= 0 ; i --) {
			for(int k = 0 ; k < i ; k ++) {
				sb.append('*');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
