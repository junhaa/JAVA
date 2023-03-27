import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #17435 합성함수와 쿼리
public class Main {

	static final int LOG = 19; // 500_000
	static int parent[][];
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		parent = new int[m + 1][LOG + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i <= m ; i ++) {
			Arrays.fill(parent[i], -1);
		}
		for(int i = 1 ; i <= m ; i ++) {
			parent[i][0] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i < LOG ; i ++) {
			for(int j = 1 ; j <= m ; j ++) {
				if(parent[j][i] != -1) {
					parent[j][i + 1] = parent[parent[j][i]][i];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int tmp = parent[Integer.parseInt(st.nextToken())][0];
			for(int j = 0 ; a != 0 ; j ++) {
				if(a % 2 == 1) tmp = parent[tmp][j];
				a /= 2;
			}
			sb.append(tmp + "\n");
		}
		System.out.println(sb);
	}
}
