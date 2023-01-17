import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
// BOJ #2578 빙고
class Point{
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {

	static int[][] map = new int[5][5];
	static HashMap<Integer, Point> hmap = new HashMap<>();
	
	public boolean check() {
		int cnt = 0;
		int sum = 0;
		for(int i = 0 ; i < 5 ; i ++) {
			sum = 0;
			for(int j = 0 ; j < 5 ; j ++) {
				sum += map[i][j];
			}
			if(sum == -5) cnt ++;
		}
		for(int i = 0 ; i < 5 ; i ++) {
			sum = 0;
			for(int j = 0 ; j < 5 ; j ++) {
				sum += map[j][i];
			}
			if(sum == -5) cnt ++;
		}
		sum = 0;
		for(int i = 0 ; i < 5 ; i ++) {
			sum += map[i][i];
		}
		if(sum == -5) cnt ++;
		
		sum = 0;
		for(int i = 0 ; i < 5 ; i ++) {
			sum += map[4 - i][i];
		}
		if(sum == -5) cnt ++;
		if(cnt >= 3) return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		for(int i = 0 ; i < 5 ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 5 ; j ++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				hmap.put(tmp, new Point(j, i));
			}
		}
		int cnt = 1;
		for(int i = 0 ; i < 5 ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 5 ; j ++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(hmap.containsKey(tmp)) {
					Point cur = hmap.get(tmp);
					map[cur.y][cur.x] = -1;
				}
				if(T.check()) {
					System.out.println(cnt);
					return;
				}
				cnt ++;
			}
		}
	}
}
