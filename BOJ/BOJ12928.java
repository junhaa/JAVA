import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #12928 트리와 경로의 길이
public class Main {

	static int[] combi;
	static boolean[][] mem;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		combi = new int[45]; // 45 C 2 => 1000이 넘음 포함 X
		combi[1] = 1; // 한 칸씩 앞당김
		for (int i = 1; i < 45; i++) {
			combi[i] = (i + 1) * i / 2;
		}
		mem = new boolean[N + 1][S + 1];
		mem[2][0] = true; // S > 0 , base
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= S; j++) {
				if (mem[i][j]) {
					for (int k = 1; k < 45; k++) {
						if (i + k <= N && j + combi[k] <= S) {
							mem[i + k][j + combi[k]] = true;
						}
					}
				}
			}
		}
		System.out.println(mem[N][S] ? 1 : 0);
	}
}
