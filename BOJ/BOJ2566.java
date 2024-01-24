import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #2566 최댓값
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = Integer.MIN_VALUE;
		int r = -1, c = -1;
		StringTokenizer st;
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if (cur > max) {
					r = i;
					c = j;
					max = cur;
				}
			}
		}
		System.out.println(max + "\n" + (r + 1) + " " + (c + 1));
	}
}
