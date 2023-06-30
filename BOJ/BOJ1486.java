import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #1486 등산
public class Main {
	static int[][] map, time;
	static int[] map2, dx = { 0, 1, 0, -1 }, dy = { -1, 0, 1, 0 };
	static int Ts, D, N, M;
	static final int max = 2_000_000;
	static void floyd() {
		for(int i = 0 ; i < N * M ; i ++) {
			for(int j = 0 ; j < N * M ; j ++) {
				for(int k = 0 ; k < N * M ; k ++) {
					if(time[j][i] + time[i][k] < time[j][k]) {
						time[j][k] = time[j][i] + time[i][k];
					}
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Ts = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		map2 = new int[N * M];
		time = new int[N * M][N * M];
		for(int i = 0 ; i < N * M ; i ++) {
			Arrays.fill(time[i], max);
		}
		for(int i = 0 ; i < N * M ; i ++) {
			time[i][i] = 0;
		} 
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int k = 0 ; k < M ; k ++) {
				char tmpCh = input.charAt(k);
				if(tmpCh >= 'a') {
					map[i][k] = tmpCh - 'a' + 26;
				}
				else {
					map[i][k] = tmpCh - 'A';
				}
				map2[i * M + k] = map[i][k];
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				for(int k = 0 ; k < 4 ; k ++) {
					int ny = i + dy[k];
					int nx = j + dx[k];
					if(ny >= 0 && ny < N && nx >= 0 && nx < M) {
						if(Math.abs(map[i][j] - map[ny][nx]) > Ts) continue;
						int pre = i * M + j;
						int next = ny * M + nx;
						if(map2[next] > map2[pre]) {
							time[pre][next] = (map2[next] - map2[pre]) * (map2[next] - map2[pre]);
						}
						else {
							time[pre][next] = 1;
						}
					}
				}
			}
		}
		T.floyd();
		int answer = Integer.MIN_VALUE;
		
		for(int i = 0 ; i < N * M ; i ++) {
			if(time[0][i] + time[i][0] <= D && answer < map2[i]) answer = map2[i];
		}
		System.out.println(answer);
	}
}	

