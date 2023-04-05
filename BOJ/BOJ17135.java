import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #17135 캐슬 디펜스
class Point{
	int y, x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	
	static boolean[] arrow, castle, shoot;
	static int N, M, D, castleCnt = 0;
	static int[][] map;
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 0, -1, 0 };
	static int answer = Integer.MIN_VALUE;
	
	static int getDis(int a, Point b) {
		return N - b.y + Math.abs(a - b.x);
	}

	static int[][] moveMonster(int[][] tmpMap) {
		for(int i = N - 1 ; i > 0 ; i --) {
			for(int j = 0 ; j < M ; j ++) {
				tmpMap[i][j] = tmpMap[i - 1][j];
			}
		}
		for(int i = 0 ; i < M ; i ++) {
			tmpMap[0][i] = 0;
		}
		return tmpMap;
	}
	
	
	static Point DFS(Point start, int[][] tmpMap) { // 공격가능한 몬스터 위치 리턴 
		Queue<Point> Q = new LinkedList<Point>();
		boolean[][] visited = new boolean[N][M];
		visited[start.y][start.x] = true;
		Q.offer(start);
		int L = 1;
		while(!Q.isEmpty()) {
			if(L == D) {
				int lt = Integer.MAX_VALUE;
				Point cur = null;
				while(!Q.isEmpty()) {
					Point tmp = Q.poll();
					if(tmpMap[tmp.y][tmp.x] == 1 && tmp.x < lt) {
						cur = tmp;
						lt = tmp.x;
					}
				}
				return cur;
			}
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				Point tmp = Q.poll();
				if(tmpMap[tmp.y][tmp.x] == 1) return tmp;
				for(int j = 0 ; j < 3 ; j ++) {
					int nx = tmp.x + dx[j];
					int ny = tmp.y + dy[j];
					if(nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[ny][nx]) {
						Q.offer(new Point(ny, nx));
						visited[ny][nx] = true;
					}
				}
			}
			L ++;
		}
		return null;
	}
	
	static void getCnt() {
		int[][] tmpMap = new int[N][M];
		int curAnswer = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				tmpMap[i][j] = map[i][j];
			}
		}
	
		Queue<Point> delMon = new LinkedList<Point>();
		for(int i = 0 ; i < N ; i ++) {
			shoot = new boolean[M];
			for(int k = 0 ; k < M ; k ++) {
				if(castle[k]) {
					Point tmp = DFS(new Point(N - 1, k), tmpMap);
					if(tmp == null) continue;
					else {
						shoot[k] = true;
						delMon.add(tmp);
					}
				}
//					if(tmpMap[j][k] == 1) {
//						boolean flag = false;º
//						for(int l = 0 ; l < M ; l ++) {
//							if(castle[l] && getDis(l, new Point(j, k)) <= D && !shoot[l]) {
//								flag = true;
//								shoot[l] = true;
//							}
//						}
//						if(flag) {
//							curAnswer ++;
//							tmpMap[j][k] = 0;
//						}
//					}
			}
			int dlen = delMon.size();
			for(int j = 0 ; j < dlen ; j ++) {
				Point cur = delMon.poll();
				if(tmpMap[cur.y][cur.x] == 1) {
					tmpMap[cur.y][cur.x] = 0;
					curAnswer ++;
				}
			}
			tmpMap = moveMonster(tmpMap);
		}
		answer = Math.max(curAnswer, answer);
	}
	
	static void backTracking(int i) {
		if(castleCnt == 3) {
			// getCnt
			getCnt();
			return;
		}
		if(i == M - 1) return;
		castle[i + 1] = true;
		castleCnt ++;
		backTracking(i + 1);
		castle[i + 1] = false;
		castleCnt --;
		backTracking(i + 1);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		castle = new boolean[M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		T.backTracking(-1);
		System.out.println(answer);
	}
}
