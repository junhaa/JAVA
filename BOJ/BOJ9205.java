import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #9205 맥주 마시면서 걸어가기 
class Point {
	int y, x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Main {

	static Point start, end;
	
	static int getDis(Point s, Point e) {
		return Math.abs(s.x - e.x) + Math.abs(s.y - e.y);
	}
	
	static String BFS(ArrayList<Point> list) {
		Queue<Point> Q = new LinkedList<>();
		boolean[] visited = new boolean[list.size()];
		Q.offer(start);
		visited[0] = true;
		while(!Q.isEmpty()) {
			Point tmp = Q.poll();
			if(tmp.equals(end)) return "happy";
			for(int i = 0 ; i < list.size() ; i ++) {
				if(!visited[i] && getDis(tmp, list.get(i)) <= 1000) {
					visited[i] = true;
					Q.offer(list.get(i));
				}
			}
		}
		return "sad";
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(test -- > 0) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			ArrayList<Point> list = new ArrayList<>();
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list.add(start);
			for(int i = 0 ; i < n ; i ++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			st = new StringTokenizer(br.readLine());
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list.add(end);
			sb.append(T.BFS(list) + "\n");
		}
		System.out.println(sb);
	}
}
