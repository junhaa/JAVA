import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #2667 단지번호붙이기
public class Main {
	static int[][] map,ch;
	static int[] mx = {0,1,0,-1};
	static int[] my = {-1,0,1,0};
	static int N, cnt = 0, size = 0;
	public static void DFS(int y, int x) {
		map[y][x] = cnt;
		size ++;
		int xx;
		int yy;
		for(int i = 0 ; i < 4 ; i ++) {
			xx = x + mx[i];
			yy = y + my[i];
			if(xx >=0 && xx < N && yy >= 0 && yy < N && ch[yy][xx] == 0 && map[yy][xx] == 1) {
				ch[yy][xx] = 1;
				DFS(yy,xx);
			}
		}
	}
	public static void solution(){
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> list = new ArrayList<>();
		ch = new int[N][N];
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				if(ch[i][k] == 0 && map[i][k] == 1) {
					ch[i][k] = 1;
					cnt ++;
					size = 0;
					DFS(i, k);
					list.add(size);
				}
			}
		}
		Collections.sort(list);
		sb.append(cnt).append('\n');
		for(int x : list) {
			sb.append(x).append('\n');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String input;
		for(int i = 0 ; i < N ; i ++) {
			input = br.readLine();
			for(int k = 0 ; k < N ; k ++) {
				map[i][k] = input.charAt(k) - '0';
			}
		}
		T.solution();
	}
}
