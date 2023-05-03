import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #20166 문자열 지옥에 빠진 호석  
public class Main {
	
	static int N, M, K, curA = 0;
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static char[][] map;
	static char[] curArr = new char[5];
	static String cur;
	static HashMap<String, Integer> hmap = new HashMap<>();
	static void DFS(int y, int x, int L, int end) {
		curArr[L] = map[y][x];
		if(L == end) {
			String tmp = "";
			for(int i = 0 ; i <= end ; i ++) {
				tmp += curArr[i];
			}
			hmap.put(tmp, hmap.getOrDefault(tmp, 0) + 1);
			return;
		}
		for(int i = 0 ; i < 8 ; i ++) {
			int nx = (x + dx[i] + M) % M;
			int ny = (y + dy[i] + N) % N;
			DFS(ny, nx, L + 1, end);
		}
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			String input = br.readLine();
			for(int j = 0 ; j < M ; j ++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		for(int j = 0 ; j < N ; j ++) {
			for(int k = 0 ; k < M ; k ++) {
				for(int l = 0 ; l < 5 ; l ++) {
					T.DFS(j, k, 0, l);
				}
			}	
		}
		
		for(int i = 0 ; i < K ; i ++) {
			cur = br.readLine();
			sb.append(hmap.getOrDefault(cur, 0) + "\n");
		}
		System.out.println(sb);
	}
}
