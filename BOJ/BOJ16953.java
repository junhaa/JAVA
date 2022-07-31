import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16953 A ¡æ B
public class Main {
	static int solution(long A, long B) {
		HashSet<Long> ch = new HashSet<>();
		Queue<Long> Q = new LinkedList<>();
		Q.offer(A);
		ch.add(A);
		int L = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length; i++) {
				long tmp = Q.poll();
				if(tmp == B) return L + 1;
				else{
					if(tmp * 2 <= B && !ch.contains(tmp * 2)) { 
						ch.add(tmp * 2);
						Q.offer(tmp * 2);
					}
					if(tmp * 10 + 1 <= B && !ch.contains(tmp * 10 + 1) ) { 
						ch.add(tmp * 10 + 1);
						Q.offer(tmp * 10 + 1);
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
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		System.out.println(T.solution(A, B));
	}
}
