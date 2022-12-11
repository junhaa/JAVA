import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #11651 좌표 정렬하기 2
class Point implements Comparable<Point>{
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Point o) {
		if(this.y == o.y) return this.x - o.x;
		return this.y - o.y;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		ArrayList<Point> list = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		for(Point t : list) {
			sb.append(t.x + " " + t.y + "\n");
		}
		System.out.println(sb);
	}
}
