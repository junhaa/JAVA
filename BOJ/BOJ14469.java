import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #14469 소가 길을 건너간 이유 3
class Time implements Comparable<Time>{
	int start, end;
	public Time(int start, int end) { 
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Time o) {
		return this.start - o.start;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		ArrayList<Time> list = new ArrayList<Time>();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		int cur = list.get(0).start + list.get(0).end;
		for(int i = 1 ; i < list.size() ; i ++) {
			cur = Math.max(cur, list.get(i).start) + list.get(i).end;
		}
		
		System.out.println(cur);
	}
}
