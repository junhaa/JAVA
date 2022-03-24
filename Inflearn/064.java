package codingTest_064;

import java.util.Scanner;

public class Main {
	static int[][] map ;
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int answer= 0, N ;
	
	public void DFS(int i, int k) {
		for(int l = 0 ; l < 8 ; l ++) {
			if((k + dx[l]) >= 0 && (k + dx[l]) <= (N-1) && (i + dy[l]) >=0 && (i + dy[l]) <= (N-1) && map[i + dy[l]][k + dx[l]] == 1) {
				map[i + dy[l]][k + dx[l]] = 0;
				answer --;
				DFS(i + dy[l] ,k + dx[l]);
			}
		}
	}
	public void solution() {
		for(int i=0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				if(map[i][k] == 1) {
					map[i][k] = 0;
					DFS(i,k);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for(int i=0 ; i < N ; i ++) {
			for(int k = 0 ; k < N ; k ++) {
				map[i][k] = sc.nextInt();
				if(map[i][k] == 1 )  answer++;				
			}
		}
		T.solution();
		System.out.println(answer);
	}
}
