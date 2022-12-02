import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #14248 점프 점프
public class Main {

	static int[] arr;
	static boolean[] visited;
	
	static int BFS(int n, int start) {
		int answer = 0;
		Queue<Integer> Q = new LinkedList<>();
		visited[start] = true;
		answer ++;
		Q.offer(start);
		while(!Q.isEmpty()) {
			int tmp = Q.poll();
			if(tmp + arr[tmp] < n && !visited[tmp + arr[tmp]]) {
				Q.offer(tmp + arr[tmp]);
				visited[tmp + arr[tmp]] = true;
				answer ++;
			}
			if(tmp - arr[tmp] >= 0 && !visited[tmp - arr[tmp]]) {
				Q.offer(tmp - arr[tmp]);
				visited[tmp - arr[tmp]] = true;
				answer ++;
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		visited = new boolean[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		int start = Integer.parseInt(br.readLine()) - 1;
		System.out.println(T.BFS(n, start));
	}
}
