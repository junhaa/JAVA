import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1766 문제집
public class Main {
	static ArrayList<ArrayList<Integer>> list;
	static int[] inDegree;
	static StringBuilder solution(int N, int M){ // 위상 정렬 + 우선순위 큐
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		for(int i = 1 ; i <= N ; i ++) {
			if(inDegree[i] == 0) pQ.offer(i);
		}
		for(int i = 0 ; i < N ; i ++) {
			if(pQ.isEmpty()) return new StringBuilder("사이클 발생");
			int tmp = pQ.poll();
			sb.append(tmp + " ");
			for(int x : list.get(tmp)) {
				if(--inDegree[x] == 0) pQ.offer(x);
			}
		}
		return sb;
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 문제의 수
		int M = Integer.parseInt(st.nextToken()); // 먼저 푸는 것이 좋은 문제 수
		inDegree = new int[N + 1];
		list = new ArrayList<ArrayList<Integer>>();
		for(int i = 0 ; i <= N ; i ++) {
			list.add(new ArrayList<Integer>());
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			inDegree[tmp2] ++;
			list.get(tmp1).add(tmp2);
		}
		System.out.println(T.solution(N, M));
	}
}
