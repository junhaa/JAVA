import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #17144 미세먼지 안녕!
public class Main {
	static int[][] map;
	static int R,C ;
	public static void clean(int y) {
			//UP
			for(int i = 1 ; i < y ; i ++) {
				map[y - i][0] = map[y - i - 1][0];
			}
			for(int i = 0 ; i < C - 1 ; i ++) {		
				map[0][i] = map[0][i + 1];
			}
			for(int i = 0 ; i < y ; i ++) {
				map[i][C - 1] = map[i + 1][C - 1];
			}
			for(int i = 0 ; i < C - 1 ; i ++) {
				map[y][(C - 1) - i] = map[y][(C - 1) - i - 1];
			}
			//DOWN
			y ++;
			for(int i = 1 ; i < (R - 1) - y ; i ++) {
				map[y + i][0] = map[y + i + 1][0];
			}
			for(int i = 0 ; i < C - 1; i ++) {
				map[R - 1][i] = map[R - 1][i + 1];
			}
			for(int i = 0 ; i < (R - 1) - y ; i ++) {
				map[(R - 1) - i][C - 1] = map[(R - 1) - i - 1][C - 1];
			}
			for(int i = 0 ; i < C - 1 ; i ++) {
				map[y][(C - 1) - i] = map[y][(C - 1) - i - 1];
			}
			map[y - 1][1] = 0;
			map[y][1] = 0;
		}

	
	public static int solution(int t) {
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0 };
		int cleaner = 0;
		int answer = 0;
		boolean find = false;
		for(int i = 0; i < R ; i ++) {
			for(int k = 0 ; k < C ; k++) {
				if(map[i][k] == -1 && !find) {
						cleaner = i;
						find = true;
				}
			}
		}
		while(t-- > 0) {
			int[][] move = new int[R][C];
			for(int i = 0 ; i < R ; i ++) { // 먼지 이동
				for(int k = 0 ; k < C ; k ++) {
					if(map[i][k] > 0) {
						int dust = map[i][k] / 5;
						for(int j = 0 ; j < 4 ; j ++) {
							int xx = k + dx[j];
							int yy = i + dy[j];
							if(xx >= 0 && xx < C && yy >= 0 && yy < R && map[yy][xx] != -1) {
								move[yy][xx] += dust;
								move[i][k] -= dust;
							}
						}
					}
				}
			}
			for(int i = 0 ; i < R ; i ++) {
				for(int k = 0 ; k < C ; k ++) {
					map[i][k] += move[i][k];
				}
			}
			clean(cleaner);
		}
		for(int i = 0 ; i < R ; i ++) {
			for(int k = 0 ; k < C ; k ++) {
				answer += map[i][k];
			}
		}
		return answer + 2;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken()); // 시간
		map = new int[R][C];
		for(int i = 0 ; i < R ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < C ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(T.solution(t));
	}
}
