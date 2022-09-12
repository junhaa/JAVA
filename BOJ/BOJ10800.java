import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #10800 컬러볼
class Result implements Comparable<Result>{
	int num;
	int res;
	public Result(int num, int res) {
		this.num = num;
		this.res = res;
	}
	@Override
	public int compareTo(Result o) {
		return this.num - o.num;
	}
}

class Ball implements Comparable<Ball>{
	int num;
	int size;
	int color;
	public Ball(int num, int color, int size) {
		this.num = num;
		this.size = size;
		this.color = color;
	}
	@Override
	public int compareTo(Ball o) {
		return this.size - o.size;
	}
}

public class Main {
	static ArrayList<Ball> list = new ArrayList<>();
	static int[] s;

	static StringBuilder solution(int N) {
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Result> pQ = new PriorityQueue<>();
		Collections.sort(list);
		HashMap<Integer, Integer> map = new HashMap<>();
		s = new int[N + 1];
		int sum = 0;
		int last = 0;
		for(Ball tmp : list) {
			if(last == tmp.size || map.isEmpty()) {
				map.put(tmp.color, map.getOrDefault(tmp.color, 0) + tmp.size);
			}
			else if(!map.isEmpty()) {
					int tmpSum = 0;
					for(int idx : map.keySet()) {
						s[idx] += map.get(idx);
						tmpSum += map.get(idx);
					}
				sum += tmpSum;
				map.clear();
				map.put(tmp.color, map.getOrDefault(tmp.color, 0) + tmp.size);
			}
			pQ.offer(new Result(tmp.num, sum - s[tmp.color]));
			last = tmp.size;
		}
		while(!pQ.isEmpty()) {
			sb.append(pQ.poll().res).append('\n');
		}
		return sb;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Ball(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		System.out.println(T.solution(N));
	}
}
