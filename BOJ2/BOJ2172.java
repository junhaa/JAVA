import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #2172 팰린드롬 경로
public class Main {
	static int N, L;
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1}, dx = { 0, 1, 1, 1, 0, -1, -1, -1};
	static int[][] map;
	static int[][][][][] dp;
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		if(L == 1){
			System.out.println(N * N);
			return;
		}
		map = new int[N][N];
		dp = new int[L + 1][N][N][N][N];


		for(int i = 0 ; i < N ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++){
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[1][i][j][i][j] ++;
			}
		}

		main.init();

		main.checkPelin();

		long answer = 0;

		for(int y1 = 0 ; y1 < N ; y1 ++){
			for(int x1 = 0 ; x1 < N ; x1 ++){
				for(int y2 = 0 ; y2 < N ; y2 ++){
					for(int x2 = 0 ; x2 < N ; x2 ++){
						answer += dp[L][y1][x1][y2][x2];
					}
				}
			}
		}
		System.out.println(answer);
	}

	private void init(){
		for(int cy = 0 ; cy < N ; cy ++){
			for(int cx = 0 ; cx < N ; cx ++){
				checkPelinInit(cy, cx);
			}
		}
	}

	private void checkPelinInit(int cy, int cx){
		int first = map[cy][cx];
		for(int i = 0 ; i < 8 ; i ++){
			int ny = cy + dy[i];
			int nx = cx + dx[i];

			if(checkBound(ny, nx) && map[ny][nx] == first){
				dp[2][cy][cx][ny][nx] ++;
			}
		}
	}

	private void checkPelin(){
		for(int l = 3 ; l <= L ; l ++){
			for(int y1 = 0 ; y1 < N ; y1 ++){
				for(int x1 = 0 ; x1 < N ; x1 ++){

					for(int y2 = 0 ; y2 < N ; y2 ++){
						for(int x2 = 0 ; x2 < N ; x2 ++){

							if(map[y1][x1] != map[y2][x2]) continue;
							for(int i = 0 ; i < 8 ; i ++){
								int ny1 = y1 + dy[i];
								int nx1 = x1 + dx[i];
								if(!checkBound(ny1, nx1)) continue;
								for(int j = 0 ; j < 8 ; j ++){
									int ny2 = y2 + dy[j];
									int nx2 = x2 + dx[j];
									if(checkBound(ny2, nx2) && map[ny1][nx1] == map[ny2][nx2]){
										dp[l][y1][x1][y2][x2] += dp[l - 2][ny1][nx1][ny2][nx2];
									}
								}
							}
						}
					}

				}
			}
		}

	}


	private boolean checkBound(int y, int x){
		return y >= 0 && y < N && x >= 0 && x < N;
	}
}
