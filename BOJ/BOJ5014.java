import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #5014 스타트링크
public class Main {
	public static String solution(int F, int S, int G, int U, int D) {
		int[] ch = new int[F + 1];
		int cnt = 0;
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(S);
		ch[S] = 1;
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int i = 0 ; i < length; i ++) {
				int tmp = Q.poll();
				if(tmp == G) return String.valueOf(cnt);
				if(tmp + U <= F && ch[tmp + U] != 1) {
					Q.offer(tmp + U);
					ch[tmp + U] = 1;
				}
				if(tmp - D >= 1 && ch[tmp - D] != 1) {
					Q.offer(tmp - D);
					ch[tmp - D] = 1;
				}
			}
			cnt ++;
		}
		return "use the stairs";
	}
		
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(F, S, G, U, D));
	}
}
