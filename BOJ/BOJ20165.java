import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #20165 인내의 도미노 장인 호석
public class Main {

	static int N, M, R;
	
	static int map[][], dx[] = { 0, 1, 0, -1 }, dy[] = { -1, 0, 1, 0 };

	static int domino(int y, int x, char dir) {
		int cnt = 0; // 넘어뜨린 도미노 수
		if(map[y][x] > 0) {
			int dirN;
			if(dir == 'N') dirN = 0;
			else if(dir == 'E') dirN = 1;
			else if(dir == 'S') dirN = 2;
			else dirN = 3;
			if(dirN == 0) {
				int len = y;
				for(int i = y ; i >= len ; i --) {
					if(i >= 0 && map[i][x] > 0) {
						len = Math.min(len, i - (map[i][x] - 1));
						map[i][x] *= -1;
						cnt ++;
					}
				}
			}
			else if(dirN == 1){
				int len = x;
				for(int i = x ; i <= len ; i ++) {
					if(i < M && map[y][i] > 0) {
					len = Math.max(len, i + (map[y][i] - 1));
					map[y][i] *= -1;
					cnt ++;
					}
				}
			}
			else if(dirN == 2) {
				int len = y;
				for(int i = y ; i <= len ; i ++) {
					if(i < N && map[i][x] > 0) {
						len = Math.max(len, i + (map[i][x] - 1));
						map[i][x] *= -1;
						cnt ++;
					}
				}
			}
			else {
				int len = x;
				for(int i = x ; i >= len ; i --) {
					if(i > 0 && map[y][i] > 0) {
					len = Math.min(len, i - (map[y][i] - 1));
					map[y][i] *= -1;
					cnt ++;
					}
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		for(int i = 0 ; i < R ; i ++) {
			st = new StringTokenizer(br.readLine());
			answer += T.domino(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, st.nextToken().charAt(0));
			st = new StringTokenizer(br.readLine());
			int cury = Integer.parseInt(st.nextToken()) - 1;
			int curx = Integer.parseInt(st.nextToken()) - 1;
			if(map[cury][curx] < 0) map[cury][curx] *= -1;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(answer + "\n");
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				if(map[i][j] < 0) {
					sb.append("F ");
				}
				else {
					sb.append("S ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
