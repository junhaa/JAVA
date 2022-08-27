import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #7569 토마토

class Point {
	int x;
	int y;
	int z;

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

public class Main {
	static int[][][] map;
	static int blank = 0;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static Queue<Point> Q = new LinkedList<>();

	static int BFS(int M, int N, int H) { // 가로 세로 높이
		int answer = 1;
		while(!Q.isEmpty()) {
			int length = Q.size();
				for(int i = 0 ; i < length ; i ++) {
					Point tmp = Q.poll();
					if (tmp.z + 1 < H && map[tmp.z + 1][tmp.y][tmp.x] == 0) {
						Q.offer(new Point(tmp.x, tmp.y, tmp.z + 1));
						map[tmp.z + 1][tmp.y][tmp.x] = 1;
						blank--;
					}
					if (tmp.z - 1 >= 0 && map[tmp.z - 1][tmp.y][tmp.x] == 0) {
						Q.offer(new Point(tmp.x, tmp.y, tmp.z - 1));
						map[tmp.z - 1][tmp.y][tmp.x] = 1;
						blank --;
					}
					for (int k = 0; k < 4; k++) {
						int xx = tmp.x + dx[k];
						int yy = tmp.y + dy[k];
						if (xx >= 0 && yy >= 0 && xx < M && yy < N && map[tmp.z][yy][xx] == 0) {
							Q.offer(new Point(xx, yy, tmp.z));
							map[tmp.z][yy][xx] = 1;
							blank--;
						}
					}
					if(blank == 0) return answer;
				}
				answer ++;
			}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 가로
		int N = Integer.parseInt(st.nextToken()); // 세로
		int H = Integer.parseInt(st.nextToken()); // 높이
		map = new int[H][N][M];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 0)
						blank++;
					else if (num == 1)
						Q.offer(new Point(k, j, i));
					map[i][j][k] = num;
				}
			}
		}
		if(blank == 0) System.out.println(0);
		else System.out.println(T.BFS(M, N, H));
	}
}
