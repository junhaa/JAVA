import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #17406 배열 돌리기 4

class Query{
	int r, c, s, num;
	public Query(int r, int c, int s, int num) {
		this.num = num;
		this.r = r;
		this.c = c;
		this.s = s;
	}
}

public class Main {
	
	static int N, M, K;
	static ArrayList<Query> list = new ArrayList<>();
	static boolean[] ch;
	static int getArrSum(int[][] tmpMap) {
		int min = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			int sum = 0;
			for(int j = 0 ; j < M ; j ++) {
				sum += tmpMap[i][j];
			}
			min = Math.min(sum, min);
		}
		return min;
	}

	static int backTracking(int[][] lastMap, int L) {
		if(L == K) {
			return getArrSum(lastMap);
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0 ; i < K ; i ++) {
			if(ch[i]) continue;
			ch[i] = true;
			int[][] tmpMap = new int[N][M];
			for(int j = 0 ; j < N ; j ++) {
				for(int k = 0 ; k < M ; k ++) {
					tmpMap[j][k] = lastMap[j][k];
				}
			}
			Query tmp = list.get(i);
			for(int j = 0 ; j <= tmp.s ; j ++) {
				turnRec(tmp.r, tmp.c, j, tmpMap);
			}
			min = Math.min(backTracking(tmpMap, L + 1), min);
			ch[i] = false;
		}
		return min;
	}
	
	static void turnRec(int r, int c, int num, int[][] tmpMap) {
		if(num == 0) return;
		int ltmp = tmpMap[r - num][c - num];
		int tmp = -1;
		for(int i = 1 ; i < 2 * num + 1 ; i ++) {
			tmp = tmpMap[r - num][c - num + i];
			tmpMap[r - num][c - num + i] = ltmp;
			ltmp = tmp;
		}
		
		for(int i = 1 ; i < 2 * num + 1 ; i ++) {
			tmp = tmpMap[r - num + i][c + num];
			tmpMap[r - num + i][c + num] = ltmp;
			ltmp = tmp;
		}
		
		for(int i = 1 ; i < 2 * num + 1 ; i ++) {
			tmp = tmpMap[r + num][c + num - i];
			tmpMap[r + num][c + num - i] = ltmp;
			ltmp = tmp;
		}
		
		for(int i = 1 ; i < 2 * num + 1 ; i ++) {
			tmp = tmpMap[r + num - i][c - num];
			tmpMap[r + num - i][c - num] = ltmp;
			ltmp = tmp;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[][] map;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ch = new boolean[K];
		map = new int[N][M];
		
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//Query
		for(int i = 0 ; i < K ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Query(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), i));
		}
		
		
		//backtracking

		System.out.println(T.backTracking(map, 0));
	}
}
