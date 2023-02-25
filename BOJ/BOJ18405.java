import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #18405 경쟁적 전염 
class Virus implements Comparable<Virus>{
	int x, y, num;
	public Virus(int y, int x, int num) {
		this.y = y;
		this.x = x;
		this.num = num;
	} 
	@Override
	public int compareTo(Virus o) {
		return this.num - o.num;
	}
}
public class Main {

	static int N, K, S, Y, X;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int BFS(ArrayList<Virus> first) {
		Queue<Virus> q = new LinkedList<>(first);
		int L = 0;
		while(!q.isEmpty()) {
			if(L == S) {
				return map[Y][X];
			}
			int len = q.size();
			for(int i = 0 ; i < len ; i ++) {
				Virus tmp = q.poll();
				for(int j = 0 ; j < 4 ; j ++) {
					int nx = tmp.x + dx[j];
					int ny = tmp.y + dy[j];
					if(nx < N && nx >= 0 && ny < N && ny >= 0 && map[ny][nx] == 0) {
						map[ny][nx] = tmp.num;
						q.offer(new Virus(ny, nx, tmp.num));
					}
				}
			}
			L ++;
		}
		return map[Y][X];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		ArrayList<Virus> first = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp != 0) {
					map[i][j] = tmp;
					first.add(new Virus(i, j, tmp));
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken()) - 1;
		X = Integer.parseInt(st.nextToken()) - 1;
		Collections.sort(first);
		System.out.println(T.BFS(first));
	}
}
