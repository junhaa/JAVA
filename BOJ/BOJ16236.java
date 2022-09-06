import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ 16236 아기 상어

class Point{
	int x;
	int y;
	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}
}

class Fish implements Comparable<Fish>{
	int distance;
	Point point;
	public Fish(int distance, int y, int x) {
		this.distance = distance;
		this.point = new Point(y, x);
	}
	@Override
	public int compareTo(Fish o) {
		if(this.distance == o.distance) {
			if(this.point.y == o.point.y) return this.point.x - o.point.x;
			else return this.point.y - o.point.y;
		}
		else return this.distance - o.distance;
	}	
}
public class Main {

	static int[][] map;
	static int N;
	
	static int solution(Point start) { // BFS
		Queue<Point> Q = new LinkedList<>();
		PriorityQueue<Fish> pQ = new PriorityQueue<>();
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0 };
		Q.offer(start);
		int size = 2; // 상어의 크기
		int sizeCnt = 0; // 아기 상어가 잡아먹은 물고기 수
		int answer = 0; // 시간
		

		while (true) {
			int distance = 1;
			boolean flag = false;
			boolean[][] visited = new boolean[N][N];
			while (!Q.isEmpty()) {
				int length = Q.size();
				for (int i = 0; i < length; i++) {
					Point shark = Q.poll();
					for (int k = 0; k < 4; k++) {
						int yy = shark.y + dy[k];
						int xx = shark.x + dx[k];
						if (yy >= 0 && xx >= 0 && yy < N && xx < N && map[yy][xx] <= size && !visited[yy][xx]) {
							if (map[yy][xx] != 0 && map[yy][xx] < size) {
								flag = true;
								visited[yy][xx] = true;
								pQ.offer(new Fish(distance, yy, xx));
							}
							if (!flag) {
								Q.offer(new Point(yy, xx));
								visited[yy][xx] = true;
							}
						}
					}
				}
				distance++;
			}
			if(pQ.isEmpty()) return answer;
			Fish eat = pQ.poll();
			map[eat.point.y][eat.point.x] = 0;
			answer += eat.distance;
			Q.clear();
			pQ.clear();
			if(++sizeCnt == size) {
				sizeCnt = 0;
				size ++;
			}
			Q.offer(eat.point);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		Point start = null;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < N ; k ++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 9) start = new Point(i, k);
				else if(tmp > 0) map[i][k] = tmp;
			}
		}
		System.out.println(T.solution(start));
	}
}
