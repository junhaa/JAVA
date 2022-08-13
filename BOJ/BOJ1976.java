import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1976 여행 가자
public class Main {
	static int[] unf;
	
	static int find(int x) {
		if(x == unf[x]) return x;
		else return unf[x] = find(unf[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) unf[fb] = fa;
		else unf[fa] = fb;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		unf = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) unf[i] = i;
		for(int i = 1 ; i <= N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int k = 1 ; k <= N ; k ++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1 && find(i) != find(k)) union(i, k);
			}
		}
		st = new StringTokenizer(br.readLine());
		int num1 = Integer.parseInt(st.nextToken());
		int num2;
		boolean flag = false;
		for(int i = 1 ; i < M ; i ++) {
			num2 = Integer.parseInt(st.nextToken());
			if(find(num1) != find(num2)) {
				flag = true;
				break;
			}
			num1 = num2;
		}
		if(flag) System.out.println("NO");
		else System.out.println("YES");
	}
}
