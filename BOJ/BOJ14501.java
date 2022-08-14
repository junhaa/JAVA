import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #14501 Επ»η

class Counseling{
	int start ;
	int end ;
	int pay;
	public Counseling (int start, int end, int pay) {
		this.start = start;
		this.end = end;
		this.pay = pay;
	}
}

public class Main {
	static ArrayList<Counseling> list = new ArrayList<>();
	static int[] dp;
	static boolean[] visited;
	static int N;
	static int solution(int start) {
		if(visited[start]) return dp[start];
		if(list.get(start).end > N) {
			dp[start] = 0;
			visited[start] = true;
			return 0;
		}
		visited[start] = true;
		int max = 0;
		for(int i = list.get(start).end + 1 ; i <= N ; i ++) {
			if(list.get(i).end <= N) max = Math.max(max, solution(i));
		}
		
		return dp[start] = list.get(start).pay + max;
				
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		visited = new boolean[N + 1];
		list.add(null);
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int end = Integer.parseInt(st.nextToken());
			int pay = Integer.parseInt(st.nextToken());
			list.add(new Counseling(i,  i + end, pay));
		}
		int answer = 0;
		for(int i = N ; i > 0 ; i -- ) {
			answer = Math.max(answer, T.solution(i));
		}
		System.out.println(answer);
	}
}
