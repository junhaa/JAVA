import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #4963 ¼¶ÀÇ °³¼ö
public class Main {
	static int[][] map;
	static int w, h;
	static int[] mx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] my = {-1, -1, 0, 1, 1, 1, 0, -1};
	public void DFS(int x, int y) {
		map[y][x] = -1;
		for(int i = 0 ; i < 8 ; i ++) {
			int xx = x + mx[i];
			int yy = y + my[i];
			if(xx >= 0 && xx < w && yy >= 0 && yy < h && map[yy][xx] == 1) {
				DFS(xx,yy);
			}
		}
	}
	
	public int solution() {
		int answer = 0 ;
		for(int i = 0 ; i < h; i ++) {
			for(int k = 0 ; k < w ; k ++) {
				if(map[i][k] == 1) {
					answer ++;
					DFS(k,i);
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			for(int i = 0 ; i < h ; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0 ; k < w ; k ++) {
					map[i][k] = Integer.parseInt(st.nextToken());
				}
			}
			if(w == 0 && h == 0) break;
			else {
				sb.append(T.solution()).append('\n');
			}
		}
		System.out.println(sb);
	}
}
