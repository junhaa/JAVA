import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #6087 레이저 통신
class Point{
	int x;
	int y;
	int num; // 거울 갯수
	int d; // 방향  0 UP 1 RIGHT 2 DOWN 3 LEFT
	public Point(int x, int y, int num, int d) {
		this.x = x;
		this.y = y;
		this.num = num;
		this.d = d;
	}
}

public class Main {
	static char[][] map;
	static int[][] ch ;
	static int[] dx = { 0, 1, 0, -1};
	static int[] dy = { -1, 0, 1, 0};
	static int W, H;
	static Queue<Point> Q = new LinkedList<>();
	static void reflect(Point x) {
		switch (x.d) {
		case 0:
		case 2: { // UP
			int xx = x.x + dx[1];
			int yy = x.y + dy[1];
			if(xx >= 0 && yy >= 0 && xx < W && yy < H && ch[yy][xx] >= x.num + 1 && map[yy][xx] != '*') {
				Q.offer(new Point(xx, yy, x.num + 1, 1));
				ch[yy][xx] = x.num + 1;
			}
			xx = x.x + dx[3];
			yy = x.y + dy[3];
			if(xx >= 0 && yy >= 0 && xx < W && yy < H && ch[yy][xx] >= x.num + 1 && map[yy][xx] != '*' ) {
				Q.offer(new Point(xx, yy, x.num + 1, 3));
				ch[yy][xx] = x.num + 1;
			}
			break;
		}
		case 1:
		case 3: { // RIGHT
			int xx = x.x + dx[0];
			int yy = x.y + dy[0];
			if(xx >= 0 && yy >= 0 && xx < W && yy < H && ch[yy][xx] >= x.num + 1 && map[yy][xx] != '*') {
				Q.offer(new Point(xx, yy, x.num + 1, 0));
				ch[yy][xx] = x.num + 1;
			}
			xx = x.x + dx[2];
			yy = x.y + dy[2];
			if(xx >= 0 && yy >= 0 && xx < W && yy < H && ch[yy][xx] >= x.num + 1 && map[yy][xx] != '*') {
				Q.offer(new Point(xx, yy, x.num + 1, 2));
				ch[yy][xx] = x.num + 1;
			}
			break;
		}
		}
	}
	
	
	static int solution(int startX, int startY) {
		int answer = Integer.MAX_VALUE;
		
		for(int i = 0 ; i < 4 ; i++) {
			int xx = startX + dx[i];
			int yy = startY + dy[i];
			if(xx >= 0 && yy >= 0 && xx < W && yy < H  && map[yy][xx] != '*') {
				Q.offer(new Point(xx, yy, 0, i));
				ch[yy][xx] = 0;
			}
		}
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				Point tmp = Q.poll();
				if(map[tmp.y][tmp.x] == 'C') answer = Math.min(tmp.num, answer);
				// 계속 이동하는 경우
				int xx = tmp.x + dx[tmp.d];
				int yy = tmp.y + dy[tmp.d];
				if(xx >= 0 && yy >= 0 && xx < W && yy < H && ch[yy][xx] > tmp.num && map[yy][xx] != '*') {
					Q.offer(new Point(xx, yy, tmp.num, tmp.d));
					ch[yy][xx] = tmp.num;
				}
				// 거울로 회전시키는 경우
				reflect(tmp);
			}
		}
		return answer;
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		int startX = 0, startY = 0;
		boolean flag = false;
		map = new char[H][W];
		ch = new int[H][W];
		for(int i = 0 ; i < H ; i ++) {
			String str = br.readLine();
			for(int k = 0 ; k < W ; k ++) {
				ch[i][k] = Integer.MAX_VALUE;
				char tmp = str.charAt(k);
				if(tmp == 'C' && !flag) {
					startX = k;
					startY = i;
					map[i][k] = 'S';
					flag = true;
				}
				map[i][k] = tmp;
			}
		}
		System.out.println(T.solution(startX, startY));
	}
}
