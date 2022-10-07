import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #7562 나이트의 이동
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
	
	static int BFS(Point start, Point end, int l) {
		Queue<Point> Q = new LinkedList<>();
		Q.add(start);
		int answer = 0;
		boolean[][] visited = new boolean[l][l];
		
		while(!Q.isEmpty()) {
			int length = Q.size();
			for(int j = 0 ; j < length ; j ++) {
			Point tmp = Q.poll();
				if(tmp.x == end.x && tmp.y == end.y) return answer;
				for(int i = 0 ; i < 8 ; i ++) {
					int xx = tmp.x + dx[i];
					int yy = tmp.y + dy[i];
					if(xx >= 0 && yy >= 0 && xx < l && yy < l) {
						if(!visited[yy][xx]) { 
							Q.offer(new Point(xx, yy));
							visited[yy][xx] = true;
						}
					}
				}
			}
			answer ++;
		}
		return -1;
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(test -- > 0) {
			int l = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine());
			Point end =  new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sb.append(T.BFS(start, end, l) + "\n");
		}
		System.out.println(sb);
	}
}
