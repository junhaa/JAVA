import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #14431 소수마을

class Point{
	int y, x, num;
	public Point(int x, int y, int num) {
		this.num = num;
		this.y = y;
		this.x = x;
	}
}
class Edge implements Comparable<Edge>{
	int cur, dis;
	public Edge(int cur, int dis) {
		this.cur = cur;
		this.dis = dis;
	}
	@Override
	public int compareTo(Edge o) {
		return this.dis - o.dis;
	}
}

public class Main {

	static int[] d;
	static boolean[] ch = new boolean[10001];
	static ArrayList<Point> list = new ArrayList<>();
	
	static int dijkstra(Point start, Point end) {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		d[start.num] = 0;
		pQ.offer(new Edge(start.num, 0));
		
		while(!pQ.isEmpty()) {
			Edge tmp = pQ.poll();
			if(d[tmp.cur] < tmp.dis) continue;
			for(int i = 0 ; i < list.size() ; i ++) {
				if(tmp.cur == i) continue;
				int dis = (int)Math.sqrt(Math.pow(list.get(tmp.cur).x - list.get(i).x, 2) + Math.pow(list.get(tmp.cur).y - list.get(i).y, 2));
				if(!ch[dis]) {
					if(d[i] > d[tmp.cur] + dis) {
						d[i] = d[tmp.cur] + dis;
						pQ.offer(new Edge(i, dis));
					}
				}
			}
		}
		if(d[end.num] == Integer.MAX_VALUE) return -1;
		else return d[end.num];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		list.add(start);
		int N = Integer.parseInt(br.readLine());
		Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), N + 1);
		d = new int[N + 2];
		Arrays.fill(d, Integer.MAX_VALUE);
		ch[1] = true;
		ch[0] = true;
		for(int i = 2 ; i <= Math.sqrt(10000) ; i ++){
			for(int j = i * i ; j <= 10000 ; j += i) {
				ch[j] = true;
			}
		}
		for(int i = 1 ; i <= N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
		}
		list.add(end);
		System.out.println(T.dijkstra(start, end));
	}
}
