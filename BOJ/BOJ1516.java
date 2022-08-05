import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1516 게임 개발
public class Main {
	static int[] time, inDegree, sum;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	
	static StringBuilder solution(int N) {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> Q = new LinkedList<>();
		for(int i = 1 ; i <= N ; i ++) {
			if(inDegree[i] == 0) { 
				Q.offer(i);
				sum[i] = time[i];
			}
		}
		for(int i = 0 ; i < N ; i ++) {
			if(Q.isEmpty()) return new StringBuilder("cycle");
			int tmp = Q.poll();
			for(int idx : list.get(tmp)) {
				sum[idx] = Math.max(sum[idx], sum[tmp] + time[idx]);
				if( --inDegree[idx] == 0 ) Q.offer(idx); 
			}
		}
		for(int i = 1;  i <= N ; i ++) {
			sb.append(sum[i]).append('\n');
		}
		return sb;
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i <= N ; i ++) {
			list.add(new ArrayList<>());
		}
		time = new int[N + 1];
		inDegree = new int[N + 1];
		sum = new int[N + 1];
		StringTokenizer st;
		int idx = 1;
		int test = N;
		while(test -- > 0) {
			 st = new StringTokenizer(br.readLine());
			 time[idx] = Integer.parseInt(st.nextToken());
			 while(true) {
				 int pre = Integer.parseInt(st.nextToken());
				 if(pre == -1) break;
				 inDegree[idx] ++;
				 list.get(pre).add(idx);
			 }
			 idx ++;
		}
		System.out.println(T.solution(N));
	}
}
