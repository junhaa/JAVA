import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1717 집합의 표현
public class Main {
	static int uf[];
	static int find(int x) {
		if(uf[x] == x) return x;
		else return uf[x] = find(uf[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa > fb) uf[fa] = fb;
		else uf[fb] = fa;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		uf = new int[n + 1];
		for(int i = 1 ; i <= n ; i ++) {
			uf[i] = i;
		}
		while(m -- > 0) {
			st = new StringTokenizer(br.readLine());
			int ch = Integer.parseInt(st.nextToken());
			if(ch == 1) { // 같은 집합인지 확인하는 연산
				int f1 = find(Integer.parseInt(st.nextToken()));
				int f2 = find(Integer.parseInt(st.nextToken()));
				if(f1 == f2) sb.append("YES");
				else sb.append("NO");
				sb.append('\n');
			}
			else if(ch == 0) { // 합집합 연산
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				union(num1, num2);
			}
		}
		System.out.println(sb);
	}
}
