import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #2457 공주님의 정원
class Flower implements Comparable<Flower>{
	int start; // 피는 날
	int end; // 지는 날
	public Flower(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Flower o) {
		return this.start - o.start;
	}
}
public class Main {

	static Flower[] arr;
	
	static int solution(int N) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
		Arrays.sort(arr);
		int answer = 0;
		int tmp = 301;
		int k = 0;
		while(true) {
			for(; k < N ; k ++) {
				if(arr[k].start <= tmp) pQ.offer(arr[k].end);
				else break;
			}
			if(pQ.isEmpty()) break;
			tmp = pQ.poll();
			answer ++;
			if(tmp > 1130) return answer;
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		arr = new Flower[N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Flower(Integer.parseInt(st.nextToken())* 100 + Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken()));
		}
		System.out.println(T.solution(N));
	}
}
