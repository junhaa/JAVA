import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #16562 친구비
public class Main {
	static int[] cost; 
	static int[] p;
	static HashMap<Integer, Integer> map = new HashMap<>();
	
	static int find(int x) {
		if(p[x] == x) return x;
		else return p[x] = find(p[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) p[fb] = fa;
		else p[fa] = fb;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 친구관계 수
		int k = Integer.parseInt(st.nextToken()); // 가지고 있는 돈
		int sum = 0;
		cost = new int[N + 1];
		p = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			cost[i] = Integer.parseInt(st.nextToken());
			p[i] = i;
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		for(int i = 1 ; i <= N ; i ++) {
			int fi =find(i);
			map.put(fi, Math.min(cost[i], map.getOrDefault(fi, Integer.MAX_VALUE)));
		}
		for(int idx : map.keySet()) {
			sum += map.get(idx);
		}
		if(sum > k) System.out.println("Oh no");
		else System.out.println(sum);
	}
}
