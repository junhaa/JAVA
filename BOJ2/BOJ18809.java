
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 배양액은 매 초마다 이전에 배양액이 도달한 적이 없는 인접한 땅으로 이동
 * 초록색 배양액과 빨간색 배양액이 동일한 시간에 도달하는 경우에는 꽃이 핌
 * 꽃이 피면 배양액이 사라지기 때문에 더 이상 인접한 땅으로 배양액 이동 X
 * 주어진 배양액은 모두 사용되어야 함
 */
// BOJ #18809 Gaaaaaaaaaarden
class Water{
	Point p;
	int color;

	public Water(Point p, int color) {
		this.p = p;
		this.color = color;
	}
}
class Point{
	int y, x;

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {

	static List<Point> startPointList = new ArrayList<>();
	static int[][] visited, colorArrCombi;
	static boolean[][] canExtend;
	static int answer = Integer.MIN_VALUE, N, M, G, R, colorIdx = 0, colorCombiLen;
	static int[] startPointCombi, colorArr , dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		visited = new int[N][M];
		canExtend = new boolean[N][M];

		startPointCombi = new int[G + R];
		colorArr = new int[G + R];
		colorCombiLen = main.fact(G + R) / (main.fact(G) * main.fact(R));
		colorArrCombi = new int[G + R][colorCombiLen];

		for(int i = 0 ; i < N ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++){
				int cur = Integer.parseInt(st.nextToken());
				if(cur == 0){
					canExtend[i][j] = false;
				}
				else if(cur == 1){
					canExtend[i][j] = true;
				}
				else{
					startPointList.add(new Point(i, j));
					canExtend[i][j] = true;
				}
			}
		}
		main.solve(G, R, 0);
		main.backtracking(0, -1);
		System.out.println(answer);
	}

	private int fact(int num){
		int answer = 1;
		for(int i = 2 ; i <= num ; i ++){
			answer *= i;
		}
		return answer;
	}
	private void backtracking(int idx, int last){
		if(idx == G + R){
			// StringBuilder sb = new StringBuilder();
			// for(int i = 0 ; i < G + R ; i ++){
			// 	sb.append(startPointCombi[i] + " ");
			// }
			// System.out.println(sb);
			BFS();
			return;
		}

		if(startPointList.size() - last - 1 < G + R - idx) return;
		for(int i = last + 1 ; i < startPointList.size() ; i ++){
			startPointCombi[idx] = i;
			backtracking(idx + 1, i);
		}
	}


	private void solve(int curG, int curR, int idx){
		if(idx == G + R){
			for(int i = 0 ; i < G + R ; i ++){
				colorArrCombi[i][colorIdx] = colorArr[i];
			}
			colorIdx ++;
		}
		if(curG > 0) {
			colorArr[idx] = 1;
			solve(curG - 1, curR, idx + 1);
			colorArr[idx] = 0;
		}
		if(curR > 0){
			colorArr[idx] = -1;
			solve(curG, curR - 1, idx + 1);
			colorArr[idx] = 0;
		}
	}
	private void BFS(){
		for(int k = 0 ; k < colorCombiLen ; k ++) {
			Queue<Water> Q = new LinkedList<>();
			int L = 1;
			int len;
			int flowers = 0;
			visited = new int[N][M];
			for (int i = 0; i < G + R; i++) {
				Point curPoint = startPointList.get(startPointCombi[i]);
				Q.add(new Water(curPoint, colorArrCombi[i][k]));
				visited[curPoint.y][curPoint.x] = 1 * colorArrCombi[i][k];
			}

			while (!Q.isEmpty()) {
				L++;
				len = Q.size();
				for (int i = 0; i < len; i++) {
					Water cur = Q.poll();
					if (visited[cur.p.y][cur.p.x] == Integer.MAX_VALUE)
						continue; // 꽃이 핀 경우 확장 X
					for (int j = 0; j < 4; j++) {
						int ny = cur.p.y + dy[j];
						int nx = cur.p.x + dx[j];
						if (ny >= 0 && ny < N && nx >= 0 && nx < M && canExtend[ny][nx]) {
							if (checkFlower(ny, nx, cur.color, L)) {
								visited[ny][nx] = Integer.MAX_VALUE;
								flowers++;
							} else if (visited[ny][nx] == 0) {
								visited[ny][nx] = cur.color * L;
								Q.add(new Water(new Point(ny, nx), cur.color));
							}
						}
					}
				}
			}
			answer = Math.max(flowers, answer);
		}
	}


	// 꽃이 될 수 있는지 체크
	private boolean checkFlower(int y, int x, int color, int L){
		if(visited[y][x] == color * L * -1) return true;
		return false;
	}

}
