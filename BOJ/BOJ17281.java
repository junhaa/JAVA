import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #17281 ⚾
public class Main {
	static int[] seq = new int[9];
	static int[][] score;
	static boolean[] ch = new boolean[9];
	static int N;
	
	static int getScore() {
		int outCnt = 0;
		int cur = 0;  // seq idx
		int scoreSum = 0;
		int inning = 0;
		Stack<Integer> stack = new Stack<>();
		while(inning < N) {
			int curHit = score[inning][seq[cur]];
			if(curHit == 0) {
				outCnt ++;
				if(outCnt == 3) {
					int sum = 0;
					while(!stack.isEmpty()) {
						sum += stack.peek();
						if(sum >= 4) break;
						else stack.pop();
					}
					scoreSum += stack.size();
					stack.clear();
					inning ++;
					outCnt = 0;
				}
			}
			else stack.push(curHit);
			cur ++;
			cur %= 9;
		}
		return scoreSum;
	}
	
	static int back_tracking(int L) {
		if(L == 9) {
			return getScore();
		}
		if(L == 3) {
			seq[L] = 0;
			ch[0] = true;
			return back_tracking(L + 1);
		}
		int max = Integer.MIN_VALUE;
		for(int i = 1 ; i < 9 ; i ++) {
			if(ch[i]) continue;
			seq[L] = i;
			ch[i] = true;
			max = Math.max(back_tracking(L + 1), max);
			ch[i] = false;
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		score = new int[N][9];
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 9 ; j ++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(back_tracking(0));
	}
}
