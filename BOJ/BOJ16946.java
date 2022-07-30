import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16946 벽 부수고 이동하기 4
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int[][] map;
	static int N, M;
	public static void solution() {
		boolean[][] visited = new boolean[N][M];
		int[][] idxMap = new int[N][M];
		int[] dx = { 0, 1, 0, -1};
		int[] dy = { -1, 0, 1, 0};
		int[] union = new int[N * M];
		StringBuilder sb = new StringBuilder();
		Queue<Point> Q = new LinkedList<>();
		int num = 2;
		for(int i = 0 ; i < N ; i ++) { // BFS로 0 탐색
			for(int k = 0 ; k < M ; k ++) {
				if(map[i][k] == 0 && visited[i][k] == false) {
					int cnt = 0;
					Q.offer(new Point(k, i));
					idxMap[i][k] = num;
					visited[i][k] = true;
					while(!Q.isEmpty()) {
						int length = Q.size();
						for(int j = 0 ; j < length ; j ++) {
							Point tmp = Q.poll();
							cnt ++;
							for(int l = 0 ; l < 4 ; l ++) {
								int xx = tmp.x + dx[l];
								int yy = tmp.y + dy[l];
								if(xx >= 0 && yy >= 0 && xx < M && yy < N && map[yy][xx] == 0 && visited[yy][xx] == false) {
									Q.offer(new Point(xx,yy));
									idxMap[yy][xx] = num;
									visited[yy][xx] = true;
								}
							}
						}
					}
					union[num++] = cnt;
				}
			}
		}
		
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < M ; k ++) {
				if(map[i][k] == 1) {
					int sum = 0;
					ArrayList<Integer> ch = new ArrayList<>();
					for(int j = 0 ; j < 4 ; j ++) {
						int xx = k + dx[j];
						int yy = i + dy[j];
						if(xx >= 0 && yy >= 0 && xx < M && yy < N && map[yy][xx] == 0 && !ch.contains(idxMap[yy][xx])) {
							ch.add(idxMap[yy][xx]);
							sum += union[idxMap[yy][xx]];
						}
					}
					map[i][k] = sum + 1;
				}
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < M ; k ++) {
				sb.append(map[i][k] % 10);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			String str = br.readLine();
			for(int k = 0 ; k < M ; k ++) {
				map[i][k] = str.charAt(k) - '0';
			}
		}
		T.solution();
	}
}
