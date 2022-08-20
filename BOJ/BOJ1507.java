import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1507 궁금한 민호
public class Main {
	static int[][] d;
	static int[][] pre;
	static boolean[][] ch;
	
	static int floydWarshall(int N) {
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				for(int j = 0 ; j < N ; j ++) {
					if(i == k || k == j || i == j) continue;
					if(d[k][j] > d[k][i] + d[i][j]) return -1; // 최단거리보다 짧아지는 경우
					else if(d[k][j] == d[k][i] + d[i][j]) pre[k][j] = Integer.MAX_VALUE;
				}
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				if(i == k) continue;
				if(pre[i][k] != Integer.MAX_VALUE && !ch[i][k]) { 
					answer += pre[i][k];
					ch[i][k] = true;
					ch[k][i] = true;
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		d = new int[N][N];
		pre = new int[N][N];
		ch = new boolean[N][N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < N ; k ++) {
				d[i][k] = Integer.parseInt(st.nextToken());
				pre[i][k] = d[i][k];
			}
		}
		System.out.println(T.floydWarshall(N));
	}
}
