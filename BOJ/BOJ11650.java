import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #11650 좌표 정렬하기
class Point implements Comparable<Point>{
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int compareTo(Point o) {
		if(this.x == o.x) return this.y - o.y;
		else return this.x - o.x;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		ArrayList<Point> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		for(int i = 0 ; i < N ; i ++) {
			sb.append(list.get(i).x + " " + list.get(i).y + "\n");
		}
		System.out.println(sb);
	}
}
