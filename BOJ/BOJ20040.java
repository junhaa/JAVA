import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #20040 사이클 게임
public class Main {
	static int[] uf;
	static int find(int x) {
		if(uf[x] == x) return x;
		else return uf[x] = find(uf[x]);
	}
	
	static void union(int min, int max) {
		int fmin = find(min);
		int fmax = find(max);
		uf[fmax] = uf[fmin];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = 0;
		int L = 0;
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		uf = new int[n];
		for(int i = 1 ; i < n ; i ++) {
			uf[i] = i;
		}
		for(int i = 0 ; i < m ; i ++) {
			st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			if(find(tmp1) == find(tmp2)) { 
				answer = L + 1;
				break;
			}
			else union(Math.min(tmp1,tmp2) , Math.max(tmp1, tmp2));
			L ++;
		}
		System.out.println(answer);
	}

}
