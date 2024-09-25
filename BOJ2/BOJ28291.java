import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #28291 레드스톤
class Point{
	int y, x, power;

	public Point(int y, int x, int power) {
		this.y = y;
		this.x = x;
		this.power = power;
	}
}
public class Main {
	static int w, h;
	static int[][] map;
	static int[][] canMove; // 1 dust, 2 lamp

	static Queue<Point> q = new LinkedList<>();

	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();
		System.out.println(main.solve() ? "success" : "failed");

	}

	/**
	 *	레드스톤 가루는 상하좌우 인접한 회로 블록에 신호 전달, 더 큰 값이 있는 경우 전달 X 다른 블록으로 전달 될 때 1 감소, 0이 되면 사라진다
	 *  레드스톤 블록은 상하좌우 인접한 회로 블록에 15 전달
	 *  레드스톤 램프는 1 이상의 전기 신호를 받을 경우 불이 켜진다.
	 */
	private boolean solve(){
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if(map[cur.y][cur.x] > cur.power) continue;
			for(int i = 0 ; i < 4 ; i ++){
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if(nx >= 0 && nx < w && ny >= 0 && ny < h){
					if(canMove[ny][nx] >= 1){
						if(cur.power - 1 > map[ny][nx]){
							if(canMove[ny][nx] == 1) q.add(new Point(ny, nx, cur.power - 1));
							map[ny][nx] = cur.power - 1;
						}
					}
				}
			}
		}
		return checkAllLampsOn();
	}

	public void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		map = new int[h][w];
		canMove = new int[h][w];

		for(int i = 0 ; i < N ; i ++){
			st = new StringTokenizer(br.readLine());
			String b = st.nextToken();
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(b.equals("redstone_dust")){
				canMove[y][x] = 1;
			} else if(b.equals("redstone_block")){
				q.add(new Point(y, x, 16));
			}
			else{
				canMove[y][x] = 2;
			}
		}
	}

	public boolean checkAllLampsOn(){
		for(int i = 0 ; i < h ; i ++){
			for(int j = 0 ; j < w ; j ++){
				if(canMove[i][j] == 2 && map[i][j] == 0) return false;
			}
		}
		return true;
	}
}
