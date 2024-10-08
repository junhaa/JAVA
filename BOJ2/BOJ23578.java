import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #23578 비 오는 날
class Building implements Comparable<Building>{
	int a, k;
	long cost;
	public Building(int a, int k) {
		this.a = a;
		this.k = k;
		this.cost = a * (2 * k + 1);
	}

	@Override
	public int compareTo(Building o){
		if(this.cost > o.cost) return 1;
		else if(this.cost < o.cost) return -1;
		else return 0;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N == 1) {
			System.out.println(0);
			return;
		}
		PriorityQueue<Building> pq = new PriorityQueue<>();
		List<Integer> list = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());

		for(int i = 0 ; i < N ; i ++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);

		long answer = 0;
		pq.offer(new Building(list.get(0), 0));

		for(int i = 1 ; i < N ; i ++){
			int cur = list.get(i);
			answer += main.getCostAndOffer(pq.poll(), pq);
			pq.offer(main.createNewBuilding(cur));
			answer += cur;
		}

		System.out.println(answer);
	}

	private long getCostAndOffer(Building building, PriorityQueue<Building> pq){
		long cost = building.cost;
		pq.offer(new Building(building.a, building.k + 1));

		return cost;
	}
	private Building createNewBuilding(int a){
		return new Building(a, 1);
	}

}
