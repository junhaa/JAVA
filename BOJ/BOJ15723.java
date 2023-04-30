import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #15723 n단 논법
public class Main {

	static int[][] map = new int[26][26];
	
	static void floydWarshall() {
		for(int i = 0 ; i < 26 ; i ++) {
			for(int j = 0 ; j < 26 ; j ++) {
				for(int k = 0 ; k < 26 ; k ++) {
					if(map[j][i] == 1 && map[i][k] == 1) {
						map[j][k] = 1;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int end = st.nextToken().charAt(0) - 'a';
			map[start][end] = 1;
		}
		T.floydWarshall();
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int end = st.nextToken().charAt(0) - 'a';
			if(map[start][end] == 1) {
				sb.append("T\n");
			}
			else sb.append("F\n");
		}
		System.out.println(sb);
	}
}
