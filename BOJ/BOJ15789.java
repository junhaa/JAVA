import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #15789 CTP 왕국은 한솔 왕국을 이길 수 있을까 ?
class Kingdom implements Comparable<Kingdom>{
	int num, size;
	public Kingdom(int num, int size) {
		this.num = num;
		this.size = size;
	}
	@Override
	public int compareTo(Kingdom o) {
		return o.size - this.size;
	}
}
public class Main {

	static int[] parent, num;
	static ArrayList<Kingdom> list = new ArrayList<>();
	
	static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb) {
			parent[fb] = fa;
			num[fa] += num[fb];
			num[fb] = 0;
		}
		else {
			parent[fa] = fb;
			num[fb] += num[fa];
			num[fa] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		num = new int[N];
		Arrays.fill(num, 1);
		for(int i = 0 ; i < N ; i ++) {
			parent[i] = i;
		}
		int answer = 0;
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			if(T.find(a) != T.find(b)) {
				T.union(a, b);
			}
		}
		st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken()) - 1;
		int H = Integer.parseInt(st.nextToken()) - 1;
		int K = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < N ; i ++) {
			if(num[i] != 0) {
				list.add(new Kingdom(i, num[i]));
			}
		}
		Collections.sort(list);
		int d = 0;
		for(int i = 0 ; i < list.size() ; i ++) {
			if(list.get(i).num != T.find(C) && list.get(i).num != T.find(H)) {
				T.union(C, list.get(i).num);
				if(++d == K) break;
			}
		}
		System.out.println(num[T.find(C)]);
	}
}
