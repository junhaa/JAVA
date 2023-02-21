import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1327 소트 게임
public class Main {

	static int N, K;
	static String answer = "";
	
	static int BFS(String s) {
		HashSet<String> set = new HashSet<>();
		Queue<String> Q = new LinkedList<>();
		set.add(s);
		Q.offer(s);
		int L = 0;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				String tmp = Q.poll();
				if(tmp.equals(answer)) return L;
				for(int j = 0 ; j <= N - K ; j ++) {
					String t1 = "";
					String t3 = "";
					if(j > 0) {
						t1 = tmp.substring(0, j);
					}
					if(j < N - K) {
						t3 = tmp.substring(j + K, N);
					}
					String t2 = tmp.substring(j, j + K);
					String t2r = "";
					for(int k = K - 1 ; k >= 0 ; k --) {
						t2r += t2.charAt(k);
					}
					String cat = t1 + t2r + t3;
					if(!set.contains(cat)) {
						Q.offer(cat);
						set.add(cat);
					}
				}
			}
			L ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		String s = "";
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			s += st.nextToken();
		}
		for(int i = 1 ; i <= N ; i ++) {
			answer += i;
		}
		System.out.println(T.BFS(s));
	}
}
