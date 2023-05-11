import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #2246 콘도 선정
class Condo implements Comparable<Condo>{
	int dis, cost;
	public Condo(int dis, int cost) {
		this.dis = dis;
		this.cost = cost;
	}
	@Override
	public int compareTo(Condo o) {
		if(this.cost == o.cost) return this.dis - o.dis;
		return this.cost - o.cost;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Condo> list = new ArrayList<>();
		StringTokenizer st;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Condo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		int minDis = Integer.MAX_VALUE;
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			if(list.get(i).dis < minDis) {
				answer ++;
				minDis = list.get(i).dis;
			}
		}
		System.out.println(answer);
	}
}
