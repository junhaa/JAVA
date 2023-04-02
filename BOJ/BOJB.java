import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #B 가희와 열리지 않는 건널목
class Train implements Comparable<Train>{
	int time;
	public Train(String t) {
		StringTokenizer st = new StringTokenizer(t, ":");
		int tmp = 0;
		tmp += 3600 * Integer.parseInt(st.nextToken());
		tmp += 60 * Integer.parseInt(st.nextToken());
		tmp += Integer.parseInt(st.nextToken());
		this.time = tmp;
	}
	@Override
	public int compareTo(Train o) {
		return this.time - o.time;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		ArrayList<Train> tlist = new ArrayList<>();
		for(int i = 0 ; i < c + h ; i ++) {
			tlist.add(new Train(br.readLine()));
		}
		Collections.sort(tlist);
		int close = 0;
		int start = tlist.get(0).time;
		int end = start + 40;
		for(int i = 1 ; i < c + h ; i ++) {
			int tmp = tlist.get(i).time;
			if(tmp <= end) {
				end = tmp + 40;
			}
			else {
				close += end - start;
				start = tmp;
				end = tmp + 40;
			}
		}
		close += end - start;
		System.out.println(86400 - close);
	}
}
