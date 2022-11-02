import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #13334 철로
class Road implements Comparable<Road>{
	int lt, rt;
	public Road(int lt, int rt) {
		this.lt = lt;
		this.rt = rt;
	}
	@Override
	public int compareTo(Road o) {
		return this.rt - o.rt;
	}
}
public class Main {

	static ArrayList<Road> list = new ArrayList<>();
	
	static int findMax(int length) {
		PriorityQueue<Integer> lQ = new PriorityQueue<>();
		int llt, lrt;
		int tmp = 0;
		int answer = Integer.MIN_VALUE;
		for(Road cur : list) {
			llt = cur.rt - length;
			lrt = cur.rt;
			lQ.offer(cur.lt);
			tmp ++;
			while(true) {
				if(lQ.isEmpty() || lQ.peek() >= llt) break;
				if(lQ.peek() < llt) {
					tmp --;
					lQ.poll();
				}
			}
			answer = Math.max(answer, tmp);
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			list.add(new Road(Math.min(tmp1, tmp2), Math.max(tmp1, tmp2)));
		}
		Collections.sort(list);
		System.out.println(T.findMax(Integer.parseInt(br.readLine())));
	}
}
