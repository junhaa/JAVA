import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #2014 ¼Ò¼öÀÇ °ö
public class Main {

	static PriorityQueue<Integer> pQ = new PriorityQueue<>();
	static HashSet<Integer> set = new HashSet<>();
	static int[] arr ;
	
	static int solution(int N, int K) {
		int cnt = 0;
		while(!pQ.isEmpty()) {
			int tmp = pQ.poll();
			if(++cnt == N) return tmp;
			int k;
			for(k = K - 1 ; k >= 0 ; k --) {
				if(tmp % arr[k] == 0) break;
			}
			for(int i = k ; i < K ; i ++) {
				long mul = (long)tmp * arr[i];
				if(mul > Integer.MAX_VALUE) break;
				pQ.offer((int)mul);
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		arr = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			arr[i] = tmp;
			pQ.offer(tmp);
			set.add(tmp);
		}
		System.out.println(T.solution(N, K));
	}
}
