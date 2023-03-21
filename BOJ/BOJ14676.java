import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #14676 영우는 사기꾼 ?
public class Main {

	static int[] cnt, inDegree;
	static boolean[] canBuild;
	static ArrayList<Integer>[] adj, pre;

	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N]; // 0-based
		pre = new ArrayList[N];
		cnt = new int[N];
		canBuild = new boolean[N];
		inDegree = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			adj[i] = new ArrayList<>();
			pre[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			inDegree[end] ++;
			adj[end].add(start);
			pre[start].add(end);
		}
		for(int i = 0 ; i < N ; i ++) {
			if(inDegree[i] == 0) {
				canBuild[i] = true;
			}
		}
		for(int i = 0 ; i < K ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) { // 1 query
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				if(inDegree[tmp] == 0) {
					if(cnt[tmp] == 0) {
						for(int next : pre[tmp]) {
							inDegree[next] --;
						}
					}
					cnt[tmp] ++;
				}
				else {
					System.out.println("Lier!");
					return;
				}
			}
			else { // 2 query
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				if(cnt[tmp] == 0) {
					System.out.println("Lier!");
					return;
				}
				else {
					cnt[tmp] --;
					if(cnt[tmp] == 0) {
						for(int next : pre[tmp]) {
							inDegree[next] ++;
						}
 					}
				}
			}
			
		}
		System.out.println("King-God-Emperor");
	}
}
