import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #5939 Race Results
class Time implements Comparable<Time>{
	int sum;
	String time;
	public Time(String time) {
		this.time = time;
		StringTokenizer st = new StringTokenizer(time);
		sum += 3600 * Integer.parseInt(st.nextToken());
		sum += 60 * Integer.parseInt(st.nextToken());
		sum += Integer.parseInt(st.nextToken());
	}
	@Override
	public int compareTo(Time o) {
		return this.sum - o.sum;
	}
}
public class Main {

	 public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Time> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			list.add(new Time(br.readLine()));
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			sb.append(list.get(i).time + "\n");
		}
		System.out.println(sb);
	}
}
