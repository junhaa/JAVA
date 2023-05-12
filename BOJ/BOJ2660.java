import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #2660 회장뽑기 
public class Main {

	static int[][] map;
	static int N;
	
	static void floyd() {
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j ++) {
				for(int k = 0 ; k < N ; k ++) {
					if(map[j][i] + map[i][k] < map[j][k]) {
						map[j][k] = map[j][i] + map[i][k];
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i ++) {
			Arrays.fill(map[i], (int)1e9);
			map[i][i] = 0;
		}
		while(true) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			if(start == -2 && end == -2) break;
			map[start][end] = 1;
			map[end][start] = 1;
		}
		T.floyd();
		int min = Integer.MAX_VALUE;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < N ; i ++) {
			int curMin = Integer.MIN_VALUE;
			for(int j = 0 ; j < N ; j ++) {
				if(map[i][j] != (int)1e9) {
					curMin = Math.max(map[i][j], curMin);
				}
			}
			if(curMin < min) {
				list = new ArrayList<Integer>();
				list.add(i + 1);
				min = curMin;
			}
			else if(curMin == min) list.add(i + 1);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(min + " " + list.size() + "\n");
		for(int i = 0 ; i < list.size() ; i ++) {
			sb.append(list.get(i) + " ");
		}
		System.out.println(sb);
	}
}
