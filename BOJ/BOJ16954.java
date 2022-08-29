import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #16954 움직이는 미로 탈출
class Point{
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static boolean[][] chess = new boolean[8][8];
	ArrayList<Point> list = new ArrayList<>();
	static Queue<Point> wQ = new LinkedList<>();
	
	
	static void moveWall() {
		int length = wQ.size();
		chess = new boolean[8][8];
		for(int i = 0 ; i < length ; i ++) {
			Point tmp = wQ.poll();
			if(tmp.y != 7) { 
				chess[tmp.y + 1][tmp.x] = true;
				wQ.offer(new Point(tmp.x, tmp.y + 1));
			}
		}
	}
	
	static int BFS() {
		int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1, 0 };
		int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1, 0 };
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(0, 7)); // 왼쪽 아랫 칸에서 출발
		while(!Q.isEmpty()) {
			int length = Q.size();
			boolean[][] ch = new boolean[8][8];
			for(int i = 0 ; i < length ; i ++) {
				Point tmp = Q.poll();
				if(chess[tmp.y][tmp.x]) continue; // 벽이 이동한 자리면
				if(tmp.x == 7 && tmp.y == 0) return 1;
				for(int k = 0 ; k < 9 ; k ++) {
					int xx = tmp.x + dx[k];
					int yy = tmp.y + dy[k];
					if(xx >= 0 && yy >= 0 && xx < 8 && yy < 8) {
						if (chess[yy][xx] || ch[yy][xx]) continue;
						Q.offer(new Point(xx, yy));
						ch[yy][xx] = true;
					}
				}
			}
			moveWall(); // 벽 위치 이동
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0 ; i < 8 ; i ++) {
			String input = br.readLine();
			for(int k = 0 ; k < 8 ; k ++) {
				if(input.charAt(k) == '#') { 
					wQ.offer(new Point(k, i));
					chess[i][k] = true;
				}
			}
		}
		System.out.println(T.BFS());
	}
}
