import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #13904 °úÁ¦
class Homework implements Comparable<Homework>{
	int d, w;
	public Homework(int d , int w) {
		this.d = d;
		this.w = w;
	}
	@Override
	public int compareTo(Homework o) {
		return o.d - this.d;
	}
}
public class Main {
	static ArrayList<Homework> list = new ArrayList<>();
	
	static int solution(int N, int max) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		int sum = 0;
		int idx = 0;
		Collections.sort(list);
		while(max > 0) {
			while(true) {
				if(idx == list.size()) break;
				if(list.get(idx).d < max) break;
				pQ.offer(list.get(idx++).w);
			}
			if(!pQ.isEmpty()) sum += pQ.poll();
			max --;
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			max = Math.max(d, max);
			list.add(new Homework(d, w));
		}
		System.out.println(T.solution(N, max));
	}
}
