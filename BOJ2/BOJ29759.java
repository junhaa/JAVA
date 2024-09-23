import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #29759 서로소 스도쿠

public class Main {

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] canNotUse = new int[1000001]; // 0 = 사용가능, 1 = 사용불가능, 2 = 입력 값
		canNotUse[0] = 1;
		canNotUse[1] = 1;

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N * N][N * N];

		StringTokenizer st;

		for(int i = 0 ; i < N * N ; i ++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N * N ; j ++){
				int cur = Integer.parseInt(st.nextToken());
				if(cur == 0) continue;
				map[i][j] = cur;
				canNotUse[cur] = 2;
			}
		}

		for(int i = 2 ; i <= 1000000 ; i ++) {
			for (int k = i + i ; k <= 1000000; k += i) {
				if(canNotUse[k] == 2){
					canNotUse[i] = 1;
				}
				else canNotUse[k] = 1;
			}
		}

		int idx = 0;
		idx = main.findNextUsableIdx(canNotUse, idx);

		for(int i = 0 ; i < N * N ; i ++){
			for(int j = 0 ; j < N * N ; j ++){
				if(map[i][j] != 0) continue;
				map[i][j] = idx;
				canNotUse[idx] = 1;
				idx = main.findNextUsableIdx(canNotUse, idx);
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N * N ; i ++){
			for(int j = 0 ; j < N * N ; j ++){
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private int findNextUsableIdx(int[] canNotUse, int curIdx){
		while(true){
			if(canNotUse[curIdx] == 0){
				return curIdx;
			}
			curIdx ++;
		}
	}
}
