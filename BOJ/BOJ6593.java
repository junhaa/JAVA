import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #6593 상범 빌딩
class Point{
	int y, x, z;
	public Point(int z, int y, int x) {
		this.z = z;
		this.y = y;
		this.x = x;
	}
}
public class Main {

	static int[][][] map; // 0 . 1 # 2 S 3 E
	static boolean[][][] visited;
	static int L,R,C;
	static Point start, end;
	static int[] dz = {1, -1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 0, 1, 0};
	static int[] dx = {0, 0, 0, 1, 0, -1};
	
	
	static int BFS() {
		Queue<Point> Q = new LinkedList<>();
		Q.offer(start);
		visited[start.z][start.y][start.x] = true;
		int move = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				Point tmp = Q.poll();
				if(tmp.z == end.z && tmp.y == end.y && tmp.x == end.x) return move;
				for(int k = 0 ; k < 6 ; k ++) {
					int nz = tmp.z + dz[k];
					int ny = tmp.y + dy[k];
					int nx = tmp.x + dx[k];
					if(nz >= 0 && nz < L && ny >= 0 && ny < R && nx >= 0 && nx < C && map[nz][ny][nx] == 0 && !visited[nz][ny][nx]) {
						visited[nz][ny][nx] = true;
						Q.offer(new Point(nz, ny, nx));
					}
				}
			}
			move ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(L == 0 && R == 0 && C == 0) break;
			map = new int[L][R][C];
			visited = new boolean[L][R][C];
			for(int i = 0 ; i < L ; i ++) {
				for(int j = 0 ; j < R ; j ++) {
					String input = br.readLine();
					for(int k = 0 ; k < C ; k ++) {
						char tmp = input.charAt(k);
						if(tmp == 'S') {
							start = new Point(i, j, k);
						}
						else if(tmp == 'E') {
							end = new Point(i, j, k);
						}
						else if(tmp == '#') {
							map[i][j][k] = 1;
						}
					}
				}
				br.readLine();
			}
			int answer = T.BFS();
			if(answer != -1) {
				sb.append("Escaped in " + answer + " minute(s).\n");
			}
			else sb.append("Trapped!\n");
		}
		System.out.println(sb);
	}
}
