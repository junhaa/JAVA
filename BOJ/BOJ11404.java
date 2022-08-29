import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11404 플로이드
public class Main {

	static int[][] d;
	
	static void floydWarshall(int n) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < n ; i ++) {
			for(int k = 0 ; k < n ; k ++) {
				for(int j = 0 ; j < n ; j ++) {
					if(d[k][j] > d[k][i] + d[i][j]) d[k][j] = d[k][i] + d[i][j];
				}
			}
		}
		for(int i = 0 ; i < n ; i ++) {
			for(int k = 0 ; k < n ; k ++) {
				if(d[i][k] == 100000000) sb.append('0' + " ");
				else sb.append(d[i][k] + " ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		d = new int[n][n];
		for(int i = 0 ; i < n ; i ++) {
			for(int k = 0 ; k < n ; k ++) {
				if(i == k) d[i][k] = 0;
				else d[i][k] = 100000000;
			}
		}
		for(int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if(d[start - 1][end - 1] > cost) d[start - 1][end - 1] = cost;
		}
		T.floydWarshall(n);
	}
}
