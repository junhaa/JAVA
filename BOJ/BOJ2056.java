import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #2056 ÀÛ¾÷
public class Main {

	static ArrayList<Integer>[] list;
	static int[] dp, id, time;
	
	static int solution(int N) {
		Queue<Integer> Q = new LinkedList<>();
		int answer = Integer.MIN_VALUE;
		for(int i = 1 ; i <= N ; i ++) {
			if(id[i] == 0) { 
				Q.offer(i);
				dp[i] = time[i];
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			if(Q.isEmpty()) return -1;
			int tmp = Q.poll();
			for(int x : list[tmp]) {
				if(--id[x] == 0) {
					Q.offer(x);
				}
				dp[x] = Math.max(dp[x], dp[tmp] + time[x]);
			}
		}
		
		for(int t : dp) {
			answer = Math.max(answer, t);
		}
		return answer;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		dp = new int[N + 1];
		id = new int[N + 1];
		time = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			time[i + 1] = cost;
			int num = Integer.parseInt(st.nextToken());
			for(int k = 0 ; k < num ; k ++) {
				int pre = Integer.parseInt(st.nextToken());
				list[pre].add(i + 1);
				id[i + 1] ++;
			}
		}
		System.out.println(T.solution(N));
	}
}
