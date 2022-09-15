import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #2636 치즈
class Point{
	int x;
	int y;
	public Point(int y, int x) {
		this.x = x; 
		this.y = y;
	}
}
public class Main {
	static int[][] map; //  1 치즈 -1 외부 공기 0 치즈 내부
	static int[][] ch;
	static int[] mx = { 0, 1, 0, -1};
	static int[] my = { -1, 0, 1, 0};
	static int N, M, cheeze;
	
	public static void DFS(int y, int x) {
		map[y][x] = -1;
		
		for(int i = 0 ; i < 4 ; i ++) {
			int yy = y + my[i];
			int xx = x + mx[i];
			if( yy >= 0 && yy < N && xx >= 0 && xx < M && ch[yy][xx] == 0) {
				ch[yy][xx] = 1;
				if(map[yy][xx] == 0 || map[yy][xx] == -1) DFS(yy,xx);
			}
		}
		
	}
	
	public static void solution(ArrayList<Point> list) {
		int answer = 0;
		int remain = 0;
		
		while(cheeze != 0) {
			remain = cheeze;
			ch = new int[N][M];
			DFS(0,0);
			int length = list.size();
			ArrayList<Integer> delete = new ArrayList<>();
			for(int i = 0 ; i < length ; i ++) {
				int x = list.get(i).x;
				int y = list.get(i).y;
				for(int k = 0 ; k < 4 ; k ++) {
					int yy = y + my[k];
					int xx = x + mx[k];
					if(map[yy][xx] == -1) {
						delete.add(i);
						break;
					}
				}
			}
			for(int i = delete.size() - 1; i >= 0 ; i --) {
				int tmp = delete.get(i);
				map[list.get(tmp).y][list.get(tmp).x] = -1;
				cheeze --;
				list.remove(tmp);
			}
			answer ++;
		}
		System.out.println(answer);
		System.out.println(remain);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Point> list = new ArrayList<>();
		N = Integer.parseInt(st.nextToken()); // 세로 격자의 수
		M = Integer.parseInt(st.nextToken()); // 가로 격자의 수
		map = new int[N][M];
		cheeze = 0;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < M ; k ++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 1) {
					cheeze ++;
					list.add(new Point(i,k));
				}
				map[i][k] = tmp;
			}
		}
		T.solution(list);
	}
}
