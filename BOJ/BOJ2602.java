import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// BOJ #2602 돌다리 건너기
public class Main {

	static String str, devil, angel;
	static Deque<Character> q = new LinkedList<>();
	static int[][][] dp;
	
	static void initQ() {
		for(int i = 0 ; i < str.length() ; i ++) q.offer(str.charAt(i));
	}
	
	static int solution(int x, int y, int idx) {
		if(dp[x][y][idx] != -1) return dp[x][y][idx];
		if(q.isEmpty()) return dp[x][y][idx] = 1;
		int tmp = 0;
		if(y == 0) {
			for(int i = x + 1 ; i < angel.length() ; i ++) {
				if(angel.charAt(i) == q.peek()) {
					char next = q.poll();
					tmp += solution(i, 1, idx + 1);
					q.addFirst(next);
				}
			}
		}
		else {
			for(int i = x + 1 ; i < devil.length() ; i ++) {
				if(devil.charAt(i) == q.peek()) {
					char next = q.poll();
					tmp += solution(i, 0, idx + 1);
					q.addFirst(next);
				}
			}
		}
		return dp[x][y][idx] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		devil = br.readLine();
		angel = br.readLine();
		dp = new int[devil.length()][2][str.length()];
		for(int i = 0 ; i < devil.length() ; i ++) { 
			for(int j = 0 ; j < 2 ; j ++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		T.initQ();
		int answer = 0;
		for(int i = 0 ; i < devil.length() ; i ++) {
			if(devil.charAt(i) == q.peek()) {
				char next = q.poll();
				answer += T.solution(i, 0, 0);
				q.addFirst(next);
			}
		}
		for(int i = 0 ; i < angel.length() ; i ++) {
			if(angel.charAt(i) == q.peek()) {
				char next = q.poll();
				answer += T.solution(i, 1, 0);
				q.addFirst(next);
			}
		}
		System.out.println(answer);
	}
}
