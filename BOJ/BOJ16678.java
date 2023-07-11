import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #16678 모독 
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] score = new int[100001];
		
		for(int i = 0 ; i < N ; i ++) {
			score[Integer.parseInt(br.readLine())] ++;
		}
		
		Queue<Integer> fQ = new LinkedList<Integer>(); // 넘치는 큐
		Queue<Integer> bQ = new LinkedList<Integer>(); // 채워야 하는 큐
		
		for(int i = 1 ; i <= 100000 ; i ++) {
			if(score[i] > 1) {
				int len = score[i];
				for(int j = 0 ; j < len - 1 ; j ++) {
					fQ.offer(i);
					score[i] --;
				}
			}
			else if(score[i] == 0) {
				bQ.offer(i);
			}
		}
		
		long answer = 0;
		
		while(!fQ.isEmpty()) {
			int tmp = fQ.poll();
			if(bQ.peek() > tmp) continue;
			else {
				int btmp = bQ.poll();
				answer += tmp - btmp;
				score[btmp] ++;
			}
		}
		if(!bQ.isEmpty()) {
			for(int i = 100000 ; i > 0 ; i --) {
				if(score[i] != 0) { 
					while(true) {
						if(!bQ.isEmpty() && bQ.peek() > i) {
							bQ.poll();
						}
						else break;
					}
					if(bQ.isEmpty()) break;
					int btmp = bQ.poll();
					answer += i - btmp;
					score[i] --;
					score[btmp] ++;
				}
				if(bQ.isEmpty()) break;
			}
		}
		System.out.println(answer);
	}
}
