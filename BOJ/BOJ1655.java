import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

// BOJ #1655 가운데를 말해요
public class Main {
	static int mid, us = 0, ds = 0;
	static PriorityQueue<Integer> uQ = new PriorityQueue<>();
	static PriorityQueue<Integer> dQ = new PriorityQueue<>(Collections.reverseOrder());
	
	
	static int solution(int i, int num) {
		if(mid > num) { 
			dQ.offer(num);
			ds ++;
		}
		else { 
			uQ.offer(num);
			us ++;
		}
		if(i % 2 == 1) {
			if(us > ds) {
				dQ.offer(mid);
				ds ++;
				mid = uQ.poll();
				us --;
			}
			else if(us < ds) {
				uQ.offer(mid);
				us ++;
				mid = dQ.poll();
				ds --;
			}
			return mid;
		}
		else {
			if(us > ds) return mid;
			else return dQ.peek();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		mid = Integer.parseInt(br.readLine());
		sb.append(mid + "\n");
		for(int i = 2 ; i <= N ; i ++) {
			sb.append(T.solution(i, Integer.parseInt(br.readLine())) + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
