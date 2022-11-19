import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #11403 경로 찾기
public class Main {

	static int[][] map;
	static int N;
	static void floydWarshall() {
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j ++) {
				for(int k = 0 ; k < N ; k ++) {
					if(map[j][i] + map[i][k] == 2) {
						map[j][k] = 1;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		T.floydWarshall();
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j ++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
