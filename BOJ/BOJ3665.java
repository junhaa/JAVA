import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #3665 최종 순위
public class Main {
	static int[] id;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer>[] list;
	static int[] last; //작년 순위

	static void solution(int n) {
		Queue<Integer> Q = new LinkedList<>();
		StringBuilder seq = new StringBuilder();
		for(int i = 1 ; i <= n ; i++) {
			if(id[i] == 0) Q.add(i);
		}
		for(int i = 0 ; i < n ; i++) {
			if(Q.isEmpty()) {
				sb.append("IMPOSSIBLE").append('\n');
				return;
			}
			else if(Q.size() > 1) {
				sb.append("?").append('\n');
				return;
			}
			int tmp = Q.poll();
			seq.append(tmp + " ");
			for(int x : list[tmp]) {
				if(--id[x] == 0) Q.add(x);
			}
		}
		sb.append(seq).append('\n');
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int test = Integer.parseInt(br.readLine());
		while(test -- > 0) {
			int n = Integer.parseInt(br.readLine());
			id = new int[n + 1];
			last = new int[n];
			list = new ArrayList[n + 1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= n ; i ++) { 
				list[i] = new ArrayList<>();
				int num = Integer.parseInt(st.nextToken());
				last[i - 1] = num;
				id[num] = i - 1; 
			}
			for(int i = 0 ; i < n - 1; i ++) {
				for(int k = i + 1; k < n ; k ++) {
					list[last[i]].add(last[k]);
				}
			}
			int m = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < m ; i ++) {
				st = new StringTokenizer(br.readLine());
				int high = Integer.parseInt(st.nextToken());
				int low = Integer.parseInt(st.nextToken());
				if(list[low].contains(high)) { // low > high  * "상대적으로" 순위가 변경됨 *
					list[low].remove(list[low].indexOf(high));
					list[high].add(low);
					id[high] --;
					id[low] ++;
				}
				else { // high > low
					list[high].remove(list[high].indexOf(low));
					list[low].add(high);
					id[low] --;
					id[high] ++;
				}
				
			}
			T.solution(n);
		}
		System.out.println(sb);
	}
}
