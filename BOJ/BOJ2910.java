import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #2910 빈도 정렬
class A implements Comparable<A>{
	int cnt;
	int num;
	int first;
	public A(int cnt, int num, int first) {
		this.cnt = cnt;
		this.num = num;
		this.first = first;
 	}
	@Override
	public int compareTo(A o) {
		if(o.cnt == this.cnt) return this.first - o.first;
		return o.cnt - this.cnt;
	}
}
public class Main {

	static HashMap<Integer, Integer> map = new HashMap<>();
	static HashMap<Integer, Integer> fmap = new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(!fmap.containsKey(tmp)) fmap.put(tmp, i);
			map.put(tmp, map.getOrDefault(tmp, 0) + 1);
		}
		ArrayList<A> list = new ArrayList<>();
		for(int key : map.keySet()) {
			list.add(new A(map.get(key), key, fmap.get(key)));
		}
		Collections.sort(list);
		for(A a : list) {
			int cnt = a.cnt;
			for(int i = 0 ; i < cnt ; i ++) {
				sb.append(a.num + " ");
			}
		}
		System.out.println(sb);
	}
}
