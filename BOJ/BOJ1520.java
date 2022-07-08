import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1520 내리막 길
public class Main {
	static int[][] map;
	static int[][] ch;
	static int[] mx = {0, 1, 0, -1};
	static int[] my = {-1, 0, 1, 0};
	static int M, N ;
	public int DFS(int row, int col, int max) {
		if(row == M - 1 && col == N - 1) return 1;
		if(ch[row][col] != -1) {
			return ch[row][col];
		}
		else {
			ch[row][col] = 0;
			for(int i = 0 ; i < 4 ; i++) {
				if(0 <= col + mx[i] && col + mx[i] < N && 0 <= row + my[i] && row + my[i] < M && map[row + my[i]][col + mx[i]] < max) {
					ch[row][col] += DFS(row + my[i] , col + mx[i], map[row + my[i]][col + mx[i]]); 
				}
			}
			return ch[row][col];
		}
	}
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // M = 세로의 길이
		N = Integer.parseInt(st.nextToken()); // N = 가로의 길이
		map = new int[M][N];
		ch = new int[M][N];
		for(int i = 0 ; i < M ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				ch[i][k] = -1;
			}
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 0 ; k < N ; k ++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		T.DFS(0, 0, map[0][0]);
		System.out.println(ch[0][0]);
	}
	
}
