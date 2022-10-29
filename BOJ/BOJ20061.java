import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #20061 모노미노도미노 2
public class Main {
	static int[][] map = new int[11][11];
	static int score = 0;
	static void move(int block, int y, int x) { // 블럭을 이동시키는 함수
		// 아래로 이동
		switch (block) { // block = 블럭 모양
		case 1: {
			int xx = x, yy = y;
			while(yy < 10) {
				if(map[yy + 1][xx] == 1) {
					map[yy][xx] = 1;
					break;
				}
				yy ++;
			}
			break;
		}
		case 2: {
			int xx = x + 1, yy = y;
			while(yy < 10) {
				if(map[yy + 1][xx] == 1 || map[yy + 1][xx - 1] == 1) {
					map[yy][xx] = 1;
					map[yy][xx - 1] = 1;
					break;
				}
				yy ++;
			}
			break;
		}
		case 3: {
			int xx = x, yy = y + 1;
			while(yy < 10) {
				if(map[yy + 1][xx] == 1) {
					map[yy][xx] = 1;
					map[yy - 1][xx] = 1;
					break;
				}
				yy ++;
			}
			break;
		}
		}
		// 오른쪽으로 이동
		switch (block) {
		case 1: {
			int xx = x, yy = y;
			while(xx < 10) {
				if(map[yy][xx + 1] == 1) {
					map[yy][xx] = 1;
					break;
				}
				xx ++;
			}
			break;
		}
		case 2: {
			int xx = x + 1, yy = y;
			while(xx < 10) {
				if(map[yy][xx + 1] == 1) {
					map[yy][xx] = 1;
					map[yy][xx - 1] = 1;
					break;
				}
				xx ++;
			}
			break;
		}
		case 3: {
			int xx = x, yy = y;
			while(xx < 10) {
				if(map[yy][xx + 1] == 1 || map[yy + 1][xx + 1] == 1) {
					map[yy][xx] = 1;
					map[yy + 1][xx] = 1;
					break;
				}
				xx ++;
			}
			break;
		}
		}
		
	}
	
	static void delete(boolean dir, int line) { // 열 혹은 행을 지우는 함수
		if(dir) { // dir => 아래 방향 !dir => 오른쪽 방향
			for(int i = line ; i > 3 ; i --) {
				for(int k = 0 ; k < 4 ; k ++) {
					map[i][k] = map[i - 1][k];
				}
			}
		}
		else {
			for(int i = line ; i > 3 ; i --) {
				for(int k = 0 ; k < 4 ; k ++) {
					map[k][i] = map[k][i - 1];
				}
			}
		}
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0 ; i < 4 ; i ++) {
			map[10][i] = 1;
			map[i][10] = 1;
		}
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			T.move(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			// 아래 방향 탐색
			for(int k = 9 ; k >= 5 ; k --) { 
				boolean flag = true; // 채워진 줄이 있는지
				for(int j = 0 ; j < 4 ; j ++) {
					if(map[k][j] == 0) {
						flag = false;
						break;
					}
				}
				if(flag) {
					T.delete(true, k);
					score ++;
					k ++;
				}
			}
			// 오른쪽 방향 탐색
			for(int k = 9 ; k >= 5 ; k --) { // 아래 방향 탐색
				boolean flag = true; // 채워진 줄이 있는지
				for(int j = 0 ; j < 4 ; j ++) {
					if(map[j][k] == 0) {
						flag = false;
						break;
					}
				}
				if(flag) {
					T.delete(false, k);
					score ++;
					k ++;
				}
			}
			// 옅은 구간 탐색
			for(int k = 5 ; k >= 4 ; k --) {
				boolean flag2 = false;
				for(int j = 0 ; j < 4; j ++) {
					if(map[k][j] == 1) {
						flag2 = true;
						break;
					}
				}
				if(flag2) {
					T.delete(true, 9);
					k ++;
				}
			}
			for(int k = 5 ; k >= 4 ; k --) {
				boolean flag2 = false;
				for(int j = 0 ; j < 4; j ++) {
					if(map[j][k] == 1) {
						flag2 = true;
						break;
					}
				}
				if(flag2) {
					T.delete(false, 9);
					k ++;
				}
			}
		}
		// 채워진 칸 수 구하기
		int sum = 0;
		for(int i = 6 ; i < 10 ; i ++) {
			for(int k = 0 ; k < 4 ; k ++) {
				if(map[i][k] == 1) sum ++;
			}
		}
		for(int i = 6 ; i < 10 ; i ++) {
			for(int k = 0 ; k < 4 ; k ++) {
				if(map[k][i] == 1) sum ++;
			}
		}
		System.out.println(score);
		System.out.println(sum);
	}
}
