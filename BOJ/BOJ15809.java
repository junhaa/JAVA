import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #15809 전국시대
public class Main {
	static int p[];
	static int troop[];
	
	static int find(int x) {
		if(x == p[x]) return x;
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
		ArrayList<Integer> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		int answer = 0 ; // 남아있는 국가의 수
		int N = Integer.parseInt(st.nextToken()); // 국가의 수
		int M = Integer.parseInt(st.nextToken()); // 기록의 수
		p = new int[N + 1];
		troop = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			troop[i] = Integer.parseInt(br.readLine());
			p[i] = i;
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			switch (Integer.parseInt(st.nextToken())){
			case 1: { // 서로 동맹을 한 경우
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int sum = troop[find(a)] + troop[find(b)];
				troop[find(a)] = 0;
				troop[find(b)] = 0;
				union(a, b);
				troop[find(a)] = sum;
				break;
			}
			case 2: { // 서로 전쟁을 벌인 경우
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int sum;
				if(troop[find(a)] > troop[find(b)]) { 
					sum = troop[find(a)] -= troop[find(b)];
					troop[find(a)] = 0;
					troop[find(b)] = 0;
				}
				else { 
					sum = troop[find(b)] -= troop[find(a)];
					troop[find(a)] = 0;
					troop[find(b)] = 0;
				}
				union(a, b);
				troop[find(a)] = sum;
				break;
			}
			}
		}
		
		for(int i = 1 ; i <= N ; i ++) {
			if(troop[i] != 0) { 
				answer ++;
				list.add(troop[i]);
			}
		}
		sb.append(answer).append('\n');
		Collections.sort(list);
		for(int x : list) sb.append(x + " ");
		System.out.println(sb);
	}
}
