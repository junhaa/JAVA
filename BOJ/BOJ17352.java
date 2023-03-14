import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #17352 여러분의 다리가 되어 드리겠습니다! 
public class Main {
	
	static int[] parent;
	
	static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) parent[fb] = fa;
		else parent[fa] = fb;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parent = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			parent[i] = i;
		}
		StringTokenizer st;
		for(int i = 0 ; i < N - 2 ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			T.union(start, end);
		}
		int last = find(0);
		int lastnum = 0;
		for(int i = 1 ; i < N ; i ++) {
			if(find(i) != last) {
				System.out.println(1 + " " + ++i);
				return;
			}
		}
	}

}
