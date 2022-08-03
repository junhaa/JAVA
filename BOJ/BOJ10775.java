import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #10775 공항
public class Main {
	static int[] uf;
	static int find(int x) {
		if(uf[x] == x) return x;
		else return uf[x] = find(uf[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) uf[fb] = fa;
		else uf[fa] = fb;
	}
	
	public static void main(String[] args) throws IOException { // greedy 시간 초과 > union & find (return 시 다음 자리 반환)
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine()); // 게이트 수
		int P = Integer.parseInt(br.readLine()); // 비행기 수
		uf = new int[G + 1];
		for(int i = 1 ; i <= G ; i ++) uf[i] = i;
		int answer = 0;
		while(P -- > 0) {
			int ftmp = find(Integer.parseInt(br.readLine()));
			if(ftmp > 0) {
				union(ftmp - 1, ftmp);
				answer ++;
			}
			else { // 도킹할 자리가 없을 때
				System.out.println(answer);
				return;
			}
		}
		System.out.println(answer);
	}
}
