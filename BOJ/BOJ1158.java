import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1158 요세푸스 문제
public class Main {
	public StringBuilder solution(int N, int K) {
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		Queue<Integer> Q = new LinkedList<>();
		for(int i = 1; i <= N ; i ++) {
			Q.offer(i);
		}
		int cnt = 0;
		while(!Q.isEmpty()) {
			cnt ++;
			if(cnt % K == 0) {
				sb.append(Q.poll());
				sb.append(", ");
			}
			else Q.offer(Q.poll());
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.append('>');
		return sb;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(N, K));
		
				
	}
}
