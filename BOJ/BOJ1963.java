import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1963 소수 경로 
public class Main {
	
	static boolean[] ch = new boolean[10000];
	static boolean[] visited;
	
	static String solution(int start, int end) {
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(start);
		visited[start] = true;
		int L = 0;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				int tmp = Q.poll();
				if(tmp == end) return String.valueOf(L);
				int num = tmp - (tmp / 1000 * 1000);
				for(int k = 1 ; k <= 9 ; k ++) {
					if(!ch[num + k * 1000] && !visited[num + k * 1000]) {
						visited[num + k * 1000] = true;
						Q.offer(num + k * 1000);
					}
				}
				num = tmp - (tmp % 1000 / 100 * 100);
				for(int k = 0 ; k <= 9 ; k ++) {
					if(!ch[num + k * 100] && !visited[num + k * 100]) {
						visited[num + k * 100] = true;
						Q.offer(num + k * 100);
					}
				}
				num = tmp - (tmp % 100 / 10 * 10);
				for(int k = 0 ; k <= 9 ; k ++) {
					if(!ch[num + k * 10] && !visited[num + k * 10]) {
						visited[num + k * 10] = true;
						Q.offer(num + k * 10);
					}
				}
				num = tmp - (tmp % 10);
				for(int k = 0 ; k <= 9 ; k ++) {
					if(!ch[num + k] && !visited[num + k]) {
						visited[num + k] = true;
						Q.offer(num + k);
					}
				}
			}
			L ++;
		}
		return new String("Impossible");
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 2 ; i <= Math.sqrt(10000) ; i ++) {
			for(int k = i * 2 ; k < 10000 ; k += i) ch[k] = true;
		}
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(test -- > 0) {
			visited = new boolean[10000];
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(T.solution(start, end) + "\n");
		}
		System.out.println(sb);
	}
}
