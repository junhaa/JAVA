import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1781 ÄÅ¶ó¸é
public class Main {
	
	static ArrayList<Integer>[] list;
	
	static int solution(int N) {
		int answer = 0;
		PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = N ; i >= 1 ; i --) {
			if(list[i] != null) {
				for(int x : list[i]) {
					pQ.offer(x);
				}
			}
			if(!pQ.isEmpty()) {
				answer += pQ.poll();
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			if(list[first] == null) {
				list[first] = new ArrayList<>();
			}
			list[first].add(second);
		}
		System.out.println(T.solution(N));
	}
}
