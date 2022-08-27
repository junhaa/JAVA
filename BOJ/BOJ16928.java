import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16928 뱀과 사다리 게임
public class Main {

	static int[] move = new int[101];
	static boolean[] visited = new boolean[101];
	
	static int BFS() {
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(1); // 1번 칸과 100번 칸은 뱀과 사다리의 시작 또는 끝이 X
		visited[1] = true;
		int answer = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				int tmp = Q.poll();
				if(tmp == 100) return answer;
				for(int k = 1 ; k <= 6 ; k ++) {
					if(tmp + k > 100) continue;
					if(!visited[tmp + k]) {
						visited[tmp + k] = true;
						if(move[tmp + k] != -1) {
							if(!visited[move[tmp + k]]) { 
								Q.offer(move[tmp + k]);
								visited[move[tmp + k]] = true;
							}
						}
						else Q.offer(tmp + k);
					}
				}
			}
			answer ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사다리의 수
		int M = Integer.parseInt(st.nextToken()); // 뱀의 수
		Arrays.fill(move, -1);
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken());
			int up = Integer.parseInt(st.nextToken());
			move[cur] = up;
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken());
			int down = Integer.parseInt(st.nextToken());
			move[cur] = down;
		}
		System.out.println(T.BFS());
	}
}
