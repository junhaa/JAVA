import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #12851 숨바꼭질 2
class Node{
	int cnt;
	int start;
	public Node(int start, int cnt) {
		this.start = start;
		this.cnt = cnt;
	}
}
public class Main {
	static StringBuilder solution(int N, int K) {
		StringBuilder sb = new StringBuilder();
		if(K < N) {
			sb.append(N - K).append('\n');
			sb.append('1');
			return sb;
		}
		boolean ch[] = new boolean[K * 2 + 1];
		int cntAns = 0; // 찾은 경우의 수
		int time = 0;
		Queue<Node> Q = new LinkedList<>();
		Q.offer(new Node(N, 1));
		ch[N] = true;
		while(!Q.isEmpty()) {
			int	length = Q.size();
			HashMap<Integer, Integer> hm = new HashMap<>();
			for(int i = 0 ; i < length ; i ++) {
				Node tmp = Q.poll();
				int start = tmp.start;
				int cnt = tmp.cnt;
				if(start == K) cntAns += cnt;
				else {
					if(start < K && !ch[start * 2]) hm.put(start * 2, hm.getOrDefault(start * 2, 0) + cnt);
					if(start < K && !ch[start + 1]) hm.put(start + 1, hm.getOrDefault(start + 1, 0) + cnt);
					if(start > 0 && !ch[start - 1]) hm.put(start - 1, hm.getOrDefault(start - 1, 0) + cnt);
				}
			}
			
			if(cntAns > 0) {
				sb.append(time).append('\n');
				sb.append(cntAns);
				return sb;
			}
			
			for(int x : hm.keySet()) {
				ch[x] = true;
				Q.offer(new Node(x, hm.get(x)));
			}
			
			time ++;
		}
		sb.append("error");			
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
