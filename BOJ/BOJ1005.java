import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1005 ACM Craft
public class Main {
	static int[] inDegree, time, sum;
	static ArrayList<ArrayList<Integer>> list;
	
	static int solution(int W, int N) { // 위상 정렬
		Queue<Integer> Q = new LinkedList<>();
		for(int i = 1 ; i <= N ; i ++) {
			if(inDegree[i] == 0) {
				Q.offer(i);
				sum[i] = time[i];
			}
		}
		while(!Q.isEmpty()) {
			int tmp = Q.poll();
			if(tmp == W) return sum[W];
			for(int x : list.get(tmp)) {
				if(--inDegree[x] == 0) Q.offer(x);
				sum[x] = Math.max(sum[x], sum[tmp] + time[x]);
			}
		}
		return -1;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 건물의 개수
			int K = Integer.parseInt(st.nextToken()); // 건설순서 규칙 수
			inDegree = new int [N + 1];
			list = new ArrayList<ArrayList<Integer>>();
			time = new int[N + 1];
			sum = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= N ; i ++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			for(int i = 0 ; i <= N ; i ++) {
				list.add(new ArrayList<Integer>());
			}
			for(int i = 0 ; i < K ; i ++) {
				st = new StringTokenizer(br.readLine());
				int tmp1 = Integer.parseInt(st.nextToken());
				int tmp2 = Integer.parseInt(st.nextToken());
				inDegree[tmp2] ++;
				list.get(tmp1).add(tmp2);
			}
			int W = Integer.parseInt(br.readLine());
			sb.append(T.solution(W,N)).append('\n');
		}
		System.out.println(sb);
	}
}
