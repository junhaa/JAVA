import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #2476 주사위 게임
public class Main {
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int answer = Integer.MIN_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			HashSet<Integer> set = new HashSet<>();
			int max = Integer.MIN_VALUE;
			for(int k = 0 ; k < 3 ; k ++) {
				int tmp = Integer.parseInt(st.nextToken());
				max = Math.max(tmp, max);
				set.add(tmp);
			}
			int sumTmp = 0;
			if(set.size() == 1) {
				sumTmp += 10000;
				sumTmp += 1000 * max;
			}
			else if(set.size() == 2) {
				sumTmp += 1000;
				sumTmp += 100 * max;
			}
			else {
				sumTmp = 100 * max;
			}
			answer = Math.max(answer, sumTmp);
		}
		System.out.println(answer);
	}
}	
