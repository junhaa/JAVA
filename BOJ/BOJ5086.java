import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #5086 배수와 약수
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 0 && b == 0)
				break;
			boolean flag = Math.max(a, b) % Math.min(a, b) == 0;
			if (flag) {
				if (a > b) {
					sb.append("multiple\n");
				} else {
					sb.append("factor\n");
				}
			} else {
				sb.append("neither\n");
			}
		}
		System.out.println(sb);
	}
}
