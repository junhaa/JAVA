import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

// BOJ #4386 별자리 만들기
class Star{
	double x;
	double y;
	public Star(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
class Road implements Comparable<Road>{
	int end;
	double cost;
	public Road (int end, double cost) {                                                                                                                                                                                              
		this.end = end;
		this.cost = cost;
	}
	@Override
	public int compareTo(Road o) {
		if(this.cost > o.cost) return 1;
		else if(this.cost < o.cost) return -1;
		else return 0;
	}
}
public class Main { // Prim
	static Star[] stars;
	static double solution(int n) {
		boolean[] v = new boolean[n + 1];
		double answer = 0;
		ArrayList<ArrayList<Road>> list = new ArrayList<>();
		PriorityQueue<Road> pQ = new PriorityQueue<>();
		for(int i = 0 ; i <= n ; i ++) {
			list.add(new ArrayList<Road>());
		}
		for(int i = 1 ; i < n ; i ++) {
			Star start = stars[i];
			for(int k = i + 1 ; k <= n ; k ++) {
				Star end = stars[k];
				double cost = Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));
				list.get(i).add(new Road(k, cost));
				list.get(k).add(new Road(i, cost));
			}
		}
		pQ.offer(new Road(1, 0));
		while(!pQ.isEmpty()) {
			Road tmp = pQ.poll();
			if(v[tmp.end] == true) continue;
			v[tmp.end] = true;
			answer += tmp.cost;
			for(Road x : list.get(tmp.end)) pQ.offer(x);
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st ;
		stars = new Star[n + 1];
		for(int i = 1 ; i <= n ; i ++) {
			st = new StringTokenizer(br.readLine());
			double tmp1 = Double.parseDouble(st.nextToken());
			double tmp2 = Double.parseDouble(st.nextToken());
			stars[i] = new Star(tmp1, tmp2);
		}
		System.out.print(String.format("%.2f", T.solution(n)));
	}
}
