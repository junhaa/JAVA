import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #15683 감시

class Point{
	int y, x, num;
	public Point(int y, int x, int num) {
		this.y = y;
		this.x = x;
		this.num = num;
	}
}

public class Main {

	static int N, M, walls = 0;
	static ArrayList<Point> list = new ArrayList<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	
	
	static int getSize(int[][] tmpMap) {
		int sum = 0;
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				if(tmpMap[i][j] == -1) sum ++;
			}
		}
		return N * M - list.size() - sum - walls; // 최소값을 반환
	}
	
	static boolean canMove(int y, int x) {
		if(y >= 0 && y < N && x >= 0 && x < M) return true;
		else return false;
	}
	
	static void fillOneDir(int[][] tmpMap, int dir, int y, int x) {
		int ny = y;
		int nx = x;
		while(canMove(ny, nx)) {
			if(tmpMap[ny][nx] == 0) tmpMap[ny][nx] = -1;
			else if(tmpMap[ny][nx] == 6) break;
			ny += dy[dir];
			nx += dx[dir];
		}
	}
	
	static void fillDir(int[][] tmpMap, int num, int dir, int y, int x) {
		switch (num) {
		case 1:
			fillOneDir(tmpMap, dir, y, x);
			break;
		case 2:
			fillOneDir(tmpMap, dir, y, x); // dir 0 1 만 가능
			fillOneDir(tmpMap, (dir + 2) % 4, y, x);
			break;
		case 3:
			fillOneDir(tmpMap, dir, y, x);
			fillOneDir(tmpMap, (dir + 1) % 4, y, x);
			break;
		case 4:
			fillOneDir(tmpMap, dir, y, x);
			fillOneDir(tmpMap, (dir + 1) % 4, y, x);
			fillOneDir(tmpMap, (dir + 3) % 4, y, x);
			break;
		case 5:
			fillOneDir(tmpMap, dir, y, x); // dir 0 만 가능
			fillOneDir(tmpMap, dir + 1, y, x);
			fillOneDir(tmpMap, dir + 2, y, x);
			fillOneDir(tmpMap, dir + 3, y, x);
			break;
			
		}
	}

	static int[][] makeTmpMap(int[][] lastMap) {
		int[][] tmpMap = new int[N][M];
		
		for(int i = 0 ; i < N ; i ++) {
			System.arraycopy(lastMap[i], 0, tmpMap[i], 0, M);
		}
		return tmpMap;
	}
	
	static int backTracking(int L, int[][] lastMap) {
		if(L == list.size()) {
			return getSize(lastMap);
		}
		int min = Integer.MAX_VALUE;
		
		Point tmpP = list.get(L);
		if(tmpP.num == 5) {
			int[][] tmpMap = makeTmpMap(lastMap);
			fillDir(tmpMap, tmpP.num, 0, tmpP.y, tmpP.x);
			min = Math.min(backTracking(L + 1, tmpMap), min);
		}
		else if(tmpP.num == 2) {
			for(int i = 0 ; i < 2 ; i ++) {
				int[][] tmpMap = makeTmpMap(lastMap);
				fillDir(tmpMap, tmpP.num, i, tmpP.y, tmpP.x);
				min = Math.min(backTracking(L + 1, tmpMap), min);
			}
		}
		else {
			for(int i = 0 ; i < 4 ; i ++) {
				int[][] tmpMap = makeTmpMap(lastMap);
				fillDir(tmpMap, tmpP.num, i, tmpP.y, tmpP.x);
				min = Math.min(backTracking(L + 1, tmpMap), min);
			}
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0 && map[i][j] < 6) {
					list.add(new Point(i, j, map[i][j]));
				}
				else if(map[i][j] == 6) walls ++;
			}
		}
		System.out.println(T.backTracking(0, map));
	}
}
