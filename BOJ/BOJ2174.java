import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #2174 로봇 시뮬레이션
class Robot{
	int x, y, dir;
	public Robot(int y, int x, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}
public class Main {

	static int[] dx = { 0, 1, 0, -1 }; // N E S W 순
	static int[] dy = { -1, 0, 1, 0 };
	static int[][] map;
	static ArrayList<Robot> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		map = new int[B][A];
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		list.add(new Robot(-1, -1, -1));
		for(int i = 1 ; i <= N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = (B - 1) - (Integer.parseInt(st.nextToken()) - 1);
			int dir = -1;
			switch (st.nextToken()) {
			case "N":
				dir = 0;
				break;
			case "E":
				dir = 1;
				break;
			case "S":
				dir = 2;
				break;
			case "W":
				dir = 3;
				break;
			}
			list.add(new Robot(y, x, dir));
			map[y][x] = i;
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			char q = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			for(int k = 0 ; k < cnt ; k ++) {
				Robot cur = list.get(num);
				switch (q) {
				case 'L':
					cur.dir = (cur.dir + 3) % 4;
					break;

				case 'R':
					cur.dir = (cur.dir + 1) % 4;
					break;
					
				case 'F':
					int ny = cur.y + dy[cur.dir];
					int nx = cur.x + dx[cur.dir];
					map[cur.y][cur.x] = 0;
					if(ny >= B || ny < 0 || nx >= A || nx < 0) {
						System.out.print("Robot " + num + " crashes into the wall");
						return;
					}
					if(map[ny][nx] != 0) {
						System.out.print("Robot " + num + " crashes into robot " + map[ny][nx]);
						return;
					}
					map[ny][nx] = num;
					cur.x = nx;
					cur.y = ny;
					break;
				}
			}
		}
		System.out.println("OK");
	}
}
