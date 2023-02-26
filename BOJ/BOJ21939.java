import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #21939 문제 추천 시스템 Version 1
class Max implements Comparable<Max>{
	int level, num;
	public Max(int level, int num) {
		this.level = level;
		this.num = num;
	}
	@Override
	public int compareTo(Max o) {
		if(o.level == this.level) {
			return o.num - this.num;
		}
		return o.level - this.level;
	}
}

class Min implements Comparable<Min>{
	int level, num;
	public Min(int level, int num) {
		this.level = level;
		this.num = num;
	}
	@Override
	public int compareTo(Min o) {
		if(o.level == this.level) {
			return this.num - o.num;
		}
		return this.level - o.level;
	}
}


public class Main {

	static HashMap<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Max> max = new PriorityQueue<>();
		PriorityQueue<Min> min = new PriorityQueue<>();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			max.offer(new Max(level, num));
			min.offer(new Min(level, num));
			map.put(num, level);
		}
		int M = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "recommend": {
				if(Integer.parseInt(st.nextToken()) == 1) {
					while(true) {
						Max tmp = max.poll();
						if(map.containsKey(tmp.num)) {
							if(map.get(tmp.num) == tmp.level) {
								sb.append(tmp.num + "\n");
								max.offer(tmp);
								break;
							}
						}
					}
				}
				else {
					while(true) {
						Min tmp = min.poll();
						if(map.containsKey(tmp.num)) {
							if(map.get(tmp.num) == tmp.level) {
								sb.append(tmp.num + "\n");
								min.offer(tmp);
								break;
							}
						}
					}
				}
				break;
			}
			case "add" : {
				 int num = Integer.parseInt(st.nextToken());
				 int level = Integer.parseInt(st.nextToken());
				 map.put(num, level);
				 max.offer(new Max(level, num));
				 min.offer(new Min(level, num));
				break;
			}
			case "solved" : {
				map.remove(Integer.parseInt(st.nextToken()));
				break;
			}
			}
		}
		System.out.println(sb);
	}
}
