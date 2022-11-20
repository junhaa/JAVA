import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #9019 DSLR
public class Main {
	static boolean[] visited;
	static String[] answer;
	
	static String BFS(int start, int end) {
		Queue<Integer> Q = new LinkedList<>();
		visited[start] = true;
		Q.offer(start);
		while(!visited[end]) {
			int tmp = Q.poll();
			int num = tmp * 2 % 10000;
			if(!visited[num]) {
				visited[num] = true;
				Q.offer(num);
				answer[num] = answer[tmp] + "D";
			}
			num = (tmp == 0) ? 9999 : tmp - 1;
			if(!visited[num]) {
				visited[num] = true;
				Q.offer(num);
				answer[num] = answer[tmp] + "S";
			}
			num = (tmp % 1000) * 10 + tmp / 1000;
			if(!visited[num]) {
				visited[num] = true;
				Q.offer(num);
				answer[num] = answer[tmp] + "L";
			}
			num = (tmp % 10) * 1000 + tmp / 10;
			if(!visited[num]) {
				visited[num] = true;
				Q.offer(num);
				answer[num] = answer[tmp] + "R";
			}
		}
		return answer[end];
				
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		while(test-- > 0) {
			st = new StringTokenizer(br.readLine());
			visited = new boolean[10000];
			answer = new String[10000];
			Arrays.fill(answer, "");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(T.BFS(start, end) + "\n");
		}
		System.out.println(sb);
	}
}
