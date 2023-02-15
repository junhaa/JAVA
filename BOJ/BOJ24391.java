import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #24391 귀찮은 해강이
public class Main {

	static int[] uf;
	
	static int find(int x) {
		if(x == uf[x]) return x;
		else return uf[x] = find(uf[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) uf[fb] = fa;
		else uf[fa] = fb;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		uf = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			uf[i] = i;
		}
		int answer = 0;
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(find(a) != find(b)) T.union(a, b);
		}
		st = new StringTokenizer(br.readLine());
		int tmp = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < N - 1 ; i ++) {
			int cur = Integer.parseInt(st.nextToken());
			if(find(tmp) != find(cur)) answer ++;
			tmp = cur;
		}
		System.out.println(answer);
	}
}
