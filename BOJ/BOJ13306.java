import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #13306 트리
public class Main {

	static String[] q;
	static int[] parent;
	static int[] pNode;
	static Stack<Integer> answer = new Stack<>();
	
	
	static int find(int u) {
		if(u == parent[u]) return u;
		else return parent[u] = find(parent[u]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa != fb) {
			if(fa > fb) parent[fa] = fb;
			else parent[fb] = fa;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		q = new String[Q + N - 1];
		parent = new int[N];
		pNode = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			parent[i] = i;
		}
		for(int i = 1 ; i < N ; i ++) {
			pNode[i] = Integer.parseInt(br.readLine()) - 1;
		}
		for(int i = 0 ; i < q.length ; i ++) {
			q[i] = br.readLine();
		}
		for(int i = q.length - 1 ; i >= 0 ; i --) {
			st = new StringTokenizer(q[i]);
			if(Integer.parseInt(st.nextToken()) == 1) {
				if(T.find(Integer.parseInt(st.nextToken()) - 1) == T.find(Integer.parseInt(st.nextToken()) - 1)) {
					answer.push(1);
				}
				else {
					answer.push(0);
				}
			}
			else {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				T.union(pNode[tmp], tmp);
			}
		}
		int length = answer.size();
		for(int i = 0 ; i < length ; i ++) {
			if(answer.pop() == 1) {
				sb.append("YES" + "\n");
			}
			else {
				sb.append("NO" + "\n");
			}
		}
		System.out.println(sb);
	}
}
