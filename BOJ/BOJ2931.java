import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #2931 가스관
class Point {
	int y, x, dir;

	public Point(int y, int x, int dir) {
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}

public class Main {
	static Point setP = new Point(-1, -1, -1), M, Z;
	static int R, C;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 }, dy = { -1, 0, 1, 0 };
	static boolean[][] visited;

	static int BFS(Point start) {
		int cnt = 2;
		visited[start.y][start.x] = true;
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(M.y + dy[M.dir], M.x + dx[M.dir], M.dir));
		visited[M.y + dy[M.dir]][M.x + dx[M.dir]] = true;
		while (!Q.isEmpty()) {
				Point tmp = Q.poll();
				if(map[tmp.y][tmp.x] == 101) continue;
				if (map[tmp.y][tmp.x] == 0) {
					if(setP.y == -1 && setP.x == -1) {
						setP = new Point(tmp.y, tmp.x, -1);
					}
					return -1;
				}
			if(map[tmp.y][tmp.x] == 3) {
				for(int k = 0 ; k < 4 ; k ++) {
					int ny = tmp.y + dy[k];
					int nx = tmp.x + dx[k];
					if(ny < 0 || ny >= R || nx < 0 || nx >= C) return -1;
					if(!visited[ny][nx]){
						visited[ny][nx] = true;
						Q.offer(new Point(ny, nx, k));
						if(map[ny][nx] != 0)cnt ++;
					}
				}
				continue;
			}
			int nDir = getNextDir(tmp.dir, map[tmp.y][tmp.x]);
			if(nDir == -1) { 
				 return -1;
			}
			int ny = tmp.y + dy[nDir];
			int nx = tmp.x + dx[nDir];
			if(ny >= 0 && ny < R && nx >= 0 && nx < C) {
				if(visited[ny][nx]) {
					if(map[ny][nx] != 3 && getNextDir(nDir, map[ny][nx]) == -1) return -1;
					else continue;
				}
				visited[ny][nx] = true;
				Q.offer(new Point(ny, nx, nDir));
				if(map[ny][nx] != 0)cnt ++;
			}
			else return -1;
		}
		return cnt;
	}

	static int getNextDir(int inputDir, int curMap) {
		if(inputDir == 0) {
			switch (curMap) {
			case 1:
				return 0;
			case 4:
				return 1;
			case 7:
				return 3;
			default:
				return -1;
			}
		}
		else if(inputDir == 1) {
			switch (curMap) {
			case 2:
				return 1;
			case 6:
				return 0;
			case 7:
				return 2;
			default:
				return -1;
			}
		}
		else if(inputDir == 2) {
			switch (curMap) {
			case 1:
				return 2;
			case 5:
				return 1;
			case 6:
				return 3;
			default:
				return -1;
			}
		}
		else {
			switch (curMap) {
			case 2:
				return 3;
			case 4:
				return 2;
			case 5:
				return 0;
			default:
				return -1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0; // 블록의 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				char tmp = input.charAt(j);
				if (tmp > '0' && tmp < '5') {
					map[i][j] = tmp - '0' + 3;
					cnt++;
				} else if (tmp == '.')
					map[i][j] = 0;
				else {
					cnt++;
					if (tmp == '|')
						map[i][j] = 1;
					else if (tmp == '-')
						map[i][j] = 2;
					else if (tmp == '+')
						map[i][j] = 3;
					else if (tmp == 'M') {
						map[i][j] = 100;
						M = new Point(i, j, -1);
					} else {
						map[i][j] = 101;
						Z = new Point(i, j, -1);
					}
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			int ny = M.y + dy[i];
			int nx = M.x + dx[i];
			if (ny >= 0 && ny < R && nx >= 0 && nx < C && map[ny][nx] != 0) {
				if (i == 0) {
					if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 7) {
						M.dir = i;
					}
				} else if (i == 1) {
					if (map[ny][nx] == 2 || map[ny][nx] == 3 || map[ny][nx] == 6 || map[ny][nx] == 7) {
						M.dir = i;
					}
				} else if (i == 2) {
					if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 5 || map[ny][nx] == 6) {
						M.dir = i;
					}
				} else {
					if (map[ny][nx] == 2 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 5) {
						M.dir = i;
					}
				}
			}
		}
		int result = T.BFS(M);
		if(result != -1) {
			System.out.println("errorr");
			return;
		}
		int answer = -1;
		for(int i = 1 ; i <= 7 ; i ++) {
			visited = new boolean[R][C];
			map[setP.y][setP.x] = i;
			int cntNum = T.BFS(M);
			if(cntNum == -1) continue;
			if(cntNum == cnt + 1) {
				answer = i;
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append((setP.y + 1) + " " + (setP.x + 1) + " ");
		if(answer >= 4) sb.append(answer - 3);
		else {
			if(answer == 1) {
				sb.append("|");
			}
			else if(answer == 2) {
				sb.append("-");
			}
			else sb.append("+");
		}
		System.out.println(sb);
	}
}
