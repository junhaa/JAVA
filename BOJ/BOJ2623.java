import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #2623 �������α׷�
public class Main {
	static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	static int[] inDegree;
	
	static StringBuilder solution(int N, int M) {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> Q = new LinkedList<>();
		for(int i = 1 ; i <= N ; i ++) {
			if(inDegree[i] == 0) Q.offer(i);
		}
		for(int i = 1 ; i <= N ; i ++) {
			if(Q.isEmpty()) {
				return new StringBuilder("0");
			}
			else {
				int tmp = Q.poll();
				sb.append(tmp).append('\n');
				for(int x : list.get(tmp)) {
					if( --inDegree[x] == 0) Q.offer(x);
				}
			}
		}
		return sb;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		inDegree = new int[N + 1];
		for(int i = 0 ; i < N + 1 ; i ++) {
			list.add(new ArrayList<Integer>());
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			int tmp1 = Integer.parseInt(st.nextToken());
			for(int k = 1 ; k < length; k ++) {
				int tmp2 = Integer.parseInt(st.nextToken());
				list.get(tmp1).add(tmp2);
				inDegree[tmp2] ++;
				tmp1 = tmp2;
			}
		}
		System.out.println(T.solution(N, M));
	}
}
