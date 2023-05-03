import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #2412 암벽 등반
class Point{
	int y, x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {

	static ArrayList<Integer>[] node;
	
	static int BFS(int top) {
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(0, 0));
		int L = 0;
		while(!Q.isEmpty()) {
			int len = Q.size();
			for(int i = 0 ; i < len ; i ++) {
				Point tmp = Q.poll();
				if(tmp.y == top) return L;
				int miny = tmp.y - 2 >= 0 ? tmp.y - 2 : 0;
				int minx = tmp.x - 2 >= 0 ? tmp.x - 2 : 0;
				for(int j = miny ; j <= (tmp.y + 2 > top ? top : tmp.y + 2) ; j ++) {
					for(int k = 0 ; k < node[j].size() ; k ++) {
						if(node[j].get(k) < minx) continue;
						if(node[j].get(k) > tmp.x + 2) break;
						Q.offer(new Point(j, node[j].get(k)));
						node[j].remove(k);
						k --;
					}
				}
			}
			L ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int top = Integer.parseInt(st.nextToken());
		node = new ArrayList[top + 1];
		for(int i = 0 ; i <= top ; i ++) {
			node[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < n ; i ++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			node[y].add(x);
		}
		for(int i = 0 ; i <= top ; i ++) {
			Collections.sort(node[i]);
		}
		System.out.println(T.BFS(top));
	}
}
