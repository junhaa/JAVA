import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #14567 선수과목(Prerequisite)
public class Main {
	static int[] id, answer;
	static ArrayList<Integer>[] list;
	
	static void solution(int N, int M) {
		int cnt = 1;
		Queue<Integer> Q = new LinkedList<>(); 
		for(int i = 1; i <= N ; i ++) {
			if(id[i] == 0) { 
				answer[i] = cnt; 
				Q.offer(i);
			}
		}
		cnt ++;
		while(!Q.isEmpty()) { //사이클 X
			int length = Q.size();
			for(int i = 0 ; i < length ; i ++) {
				int tmp = Q.poll();
				for(int n : list[tmp]) {
					if(--id[n] == 0) {
						answer[n] = cnt;
						Q.offer(n);
					}
				}
			}
			cnt ++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		id = new int[N + 1];
		list = new ArrayList[N + 1];
		answer = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int pre = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			list[pre].add(next);
			id[next]++;
		}
		T.solution(N, M);
		for(int i = 1 ; i <= N ; i ++) {
			sb.append(answer[i] + " ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
