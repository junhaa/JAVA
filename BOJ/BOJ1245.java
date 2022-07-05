import java.util.Scanner;

// BOJ #1245 ³óÀå °ü¸®
public class Main {
	static int[] mx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] my = { -1, -1, 0, 1, 1, 1, 0 , -1 };
	static int[][] map;
	static int[][] ch;
	static int N, M;
	static boolean flag = true;
	
	public void DFS(int x, int y, int num) {
		ch[y][x] = 1;
		for(int i = 0 ; i < 8 ; i ++) {
			if(y + my[i] >= 0 && y + my[i] < N && x + mx[i] >= 0 && x + mx[i] < M) {
				if(map[y + my[i]][x + mx[i]] > num) flag = false;
				else if(ch[y + my[i]][x + mx[i]] == 0 && map[y + my[i]][x + mx[i]] == map[y][x]) {
					DFS(x + mx[i], y + my[i], num);
				}
			}
		}
	}
	
	public int solution(int N, int M, int[][] map) {
		int answer = 0;
		
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < M ; k ++) {
				if(ch[i][k] == 0) {
					flag = true;
					DFS(k, i, map[i][k]);
					if(flag) answer ++;
				}
			}
		}
		
		return answer;
	}

	public static void main(String[] args) {
		Main T = new Main();
		Scanner sc =  new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		ch = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			for(int k = 0 ; k < M ; k ++) {
				map[i][k] = sc.nextInt();
			}
		}
		System.out.println(T.solution(N, M, map));
	}
}
