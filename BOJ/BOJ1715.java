import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// BOJ #1715 카드 정렬하기
public class Main {
	static PriorityQueue<Integer> pQ = new PriorityQueue<>();
	static int solution() {
		int answer = 0;
		while(true) {
			int tmp1 = pQ.poll();
			if(pQ.isEmpty()) return answer;
			int tmp2 = pQ.poll();
			answer += tmp1 + tmp2;
			pQ.offer(tmp1 + tmp2);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			pQ.offer(Integer.parseInt(br.readLine()));
		}
		System.out.println(T.solution());
	}
	
}
