import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #1043 °ÅÁþ¸»
public class Main {
	static int[] p;
	static ArrayList<int[]> list = new ArrayList<>();
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
	
	static int solution(int M) {
		int answer = 0;
		for(int i = 0 ; i < M ; i ++ ) {
			int first = list.get(i)[0];
			for(int k = 1 ; k < list.get(i).length ; k ++) {
				union(first, list.get(i)[k]);
			}
		}
		for(int i = 0 ; i < M ; i ++) {
			boolean flag = false;
			for(int k = 0 ; k < list.get(i).length ; k ++) {
				if(find(0) == find(list.get(i)[k])) {
					flag = true;
					break;
				}
			}
		if(!flag) answer ++; 
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int answer = 0;
		p = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) p[i] = i;
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < K ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			union(0, tmp);
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			int tmp[] = new int[length];
			for(int k = 0 ; k < length ; k ++) {
				tmp[k] = Integer.parseInt(st.nextToken());
			}
			list.add(tmp);
		}
		System.out.println(T.solution(M));
	}
}
