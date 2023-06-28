import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #2170 선 긋기
class Line implements Comparable<Line>{
	int start,  end;
	public Line(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Line o) {
		if(this.start == o.start) {
			return o.end - this.end;
		}
		else {
			return this.start - o.start;
		}
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		ArrayList<Line> list = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		int ts = Integer.MIN_VALUE;
		int te = Integer.MIN_VALUE;
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			Line tmp = list.get(i);
			if(tmp.start > te) {
				answer += te - ts;
				ts = tmp.start;
				te = tmp.end;
			}
			else {
				te = Math.max(tmp.end, te);
			}
		}
		answer += te - ts;
		System.out.println(answer);
	}
}	
