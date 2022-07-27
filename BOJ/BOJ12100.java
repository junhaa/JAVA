import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #12100 2048(Easy)
public class Main {
	static int[][] map;
	static int N, answer = Integer.MIN_VALUE;
	static Queue<Integer> Q = new LinkedList<>();
	public static void move(int[][] preMap, int F) {
		switch (F) {
		case 0: { // UP
			for(int i = 0 ; i < N ; i ++) {
				for(int k = 0 ; k < N ; k ++) {
					if(preMap[k][i] != 0 ) Q.offer(preMap[k][i]);
				}
				int cnt = 0;
				while(cnt < N) {
					if(Q.size() >= 2) {
						int tmp = Q.poll();
						if(tmp == Q.peek()) map[cnt++][i] = tmp + Q.poll();
						else map[cnt++][i] = tmp;
					}
					else if(Q.size() == 1) map[cnt++][i] = Q.poll();
					else map[cnt++][i] = 0;
				}
			}
			break;
		}
		case 1: { // RIGHT
			for(int i = 0 ; i < N ; i ++) {
				for(int k = N - 1 ; k >= 0 ; k --) {
					if(preMap[i][k] != 0 )Q.offer(preMap[i][k]);
				}
				int cnt = N - 1;
				while(cnt >= 0) {
					if(Q.size() >= 2) {
						int tmp = Q.poll();
						if(tmp == Q.peek()) map[i][cnt--] = tmp + Q.poll();
						else map[i][cnt--] = tmp;
					}
					else if(Q.size() == 1) map[i][cnt--] = Q.poll();
					else map[i][cnt--] = 0;
				}
			}
			break;
		}
		case 2: { // DOWN
			for(int i = 0 ; i < N ; i ++) {
				for(int k = N - 1 ; k >= 0 ; k --) {
					if(preMap[k][i] != 0 )Q.offer(preMap[k][i]);
				}
				int cnt = N - 1;
				while(cnt >= 0) {
					if(Q.size() >= 2) {
						int tmp = Q.poll();
						if(tmp == Q.peek()) map[cnt--][i] = tmp + Q.poll();
						else map[cnt--][i] = tmp;
					}
					else if(Q.size() == 1) map[cnt--][i] = Q.poll();
					else map[cnt--][i] = 0;
				}
			}	
			break;
		}
		case 3: { // LEFT
			for(int i = 0 ; i < N ; i ++) {
				for(int k = 0 ; k < N ; k ++) {
					if(preMap[i][k] != 0 )Q.offer(preMap[i][k]);
				}
				int cnt = 0;
				while(cnt < N) {
					if(Q.size() >= 2) {
						int tmp = Q.poll();
						if(tmp == Q.peek()) map[i][cnt++] = tmp + Q.poll();
						else map[i][cnt++] = tmp;
					}
					else if(Q.size() == 1) map[i][cnt++] = Q.poll();
					else map[i][cnt ++] = 0;
				}
			}
			break;
		}
		}
	}
	
	public static void DFS(int L) {
		if(L == 5) {
			for(int i = 0 ; i < N ; i ++) {
				for(int k = 0 ; k < N ; k ++) {
					answer = Math.max(answer, map[i][k]);
				}
			}
			return;
		}
		int[][] preMap = new int[N][N];
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				preMap[i][k] = map[i][k];
			}
		}
		for(int i = 0 ; i < 4 ; i ++) {
			move(preMap, i);
			DFS(L + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < N ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		T.DFS(0);
		System.out.println(answer);
	}
}
