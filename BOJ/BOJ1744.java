import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;


// BOJ #1744 ¼ö ¹­±â
public class Main {
	static PriorityQueue<Integer> ppQ = new PriorityQueue<>(Collections.reverseOrder());
	static PriorityQueue<Integer> npQ = new PriorityQueue<>();
	static int solution() {
		int answer = 0 ;
		int tmp1, tmp2;
		if(ppQ.size() > 0) {
			while(!ppQ.isEmpty()) {
				tmp1 = ppQ.poll();
				if(ppQ.isEmpty()) {
					answer += tmp1;
					break;
				}
				tmp2 = ppQ.poll();
				if(tmp1 == 1 || tmp2 == 1) answer += tmp1 + tmp2;
				else answer += tmp1 * tmp2;
			}
		}
		if(npQ.size() > 0) {
			while(!npQ.isEmpty()) {
				tmp1 = npQ.poll();
				if(npQ.isEmpty()) {
					answer += tmp1;
					break;
				}
				tmp2 = npQ.poll();
				answer += tmp1 * tmp2;
			}
		}
		return answer;
	}
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nNum = 0;
		int N = Integer.parseInt(br.readLine());
		int tmp;
		for(int i = 0 ; i < N ; i ++) {
			tmp = Integer.parseInt(br.readLine());
			if(tmp <= 0) {
				npQ.offer(tmp);
			}
			else ppQ.offer(tmp);
		}
		System.out.println(T.solution());
	}
}
