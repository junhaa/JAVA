import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #1931 회의실 배정
class Time implements Comparable<Time>{
	int start, end;
	public Time(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Time o) {
		if(this.end == o.end) return this.start - o.start;
		else return this.end - o.end; 
	}
}
public class Main {

	static ArrayList<Time> list = new ArrayList<>();
	
	static int solution() {
		int cnt, time;
		Collections.sort(list);
		cnt = 1;
		time = list.get(0).end;
		for(int i = 1 ; i < list.size() ; i ++) {
			Time tmp = list.get(i);
			if(tmp.start < time) continue;
			time = tmp.end;
			cnt ++;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new Time(start, end));
		}
		System.out.println(T.solution());
	}
}
