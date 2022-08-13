import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1202 º¸¼® µµµÏ
class Gem implements Comparable<Gem>{
	int weight;
	int value;
	public Gem(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
	@Override
	public int compareTo(Gem o) {
		if(this.weight == o.weight) return o.value - this.value;
		else return this.weight - o.weight;
	}
}
public class Main {
	static ArrayList<Gem> list = new ArrayList<>();
	static int[] bag;
	
	static long solution(int K) {
		PriorityQueue<Gem> pQ = new PriorityQueue<>(new Comparator<Gem>() {
			@Override
			public int compare(Gem o1, Gem o2) {
				return o2.value - o1.value;
			}
		});
		long answer = 0;
		Collections.sort(list);
		Arrays.sort(bag);
		int idx = 0;
		for(int i = 0 ; i < K ; i ++) {
			for(; idx < list.size() ; idx ++) {
				if(bag[i] < list.get(idx).weight) break;
				else pQ.offer(list.get(idx));
			}
			if(pQ.isEmpty()) continue;
			answer += pQ.poll().value;
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M, V;
		for(int i = 0 ; i < N ; i ++) {
			st= new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			list.add(new Gem(M, V));
		}
		bag = new int[K];
		for(int i = 0 ; i < K ; i ++) bag[i] = Integer.parseInt(br.readLine());
		System.out.println(T.solution(K));
	}
}
