import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1071 소트
class Number implements Comparable<Number>{
	int num;
	int seq;
	public Number(int num, int seq) {
		this.num = num;
		this.seq = seq;
	}
	@Override
	public int compareTo(Number o) {
		return this.num - o.num;
	}
}
public class Main {
	
	static PriorityQueue<Number> pQ = new PriorityQueue<>();

	static StringBuilder solution() {
		Number tmp1 = null, tmp2 = null;
		StringBuilder answer = new StringBuilder();
		int last = -10000; //= pQ.poll().num;
		//answer.append(last + " ");
		
		while(!pQ.isEmpty()) {
			tmp1 = pQ.poll();
			if(pQ.size() == 1) {
				tmp2 = pQ.poll();
				if(tmp1.num + 1 == tmp2.num) {
					for(int i = 0 ; i < tmp2.seq ; i ++) {
						answer.append(tmp2.num + " ");
					}
					for(int i = 0 ; i < tmp1.seq ; i ++) {
						answer.append(tmp1.num + " ");
					}
					break;
				}
				else {
					if(last + 1 == tmp1.num) {
						answer.append(tmp2.num + " ");
						tmp2.seq --;
					}
					
					for(int i = 0 ; i < tmp1.seq ; i ++) {
						answer.append(tmp1.num + " ");
					}
					for(int i = 0 ; i < tmp2.seq ; i ++) {
						answer.append(tmp2.num + " ");
					}
				}
			}
			else {
			if(tmp1.num - 1 == last) {
				tmp2 = pQ.poll();
				answer.append(tmp2.num + " ");
				last = tmp2.num;
				if(--tmp2.seq != 0) {
					pQ.offer(tmp2);
				}
				pQ.offer(tmp1);
			}
			else {
				for(int i = 0 ; i < tmp1.seq ; i ++) {
					answer.append(tmp1.num + " ");
				}
				last = tmp1.num;
			}
					
			}
			/*
			if(pQ.isEmpty()) {
				if(tmp1.num + 1 == tmp2.num) {
					for(int i = 0 ; i < tmp2.seq ; i ++) {
						answer.append(tmp2.num + " ");
					}
					for(int i = 0 ; i < tmp1.seq ; i ++) {
						answer.append(tmp1.num + " ");
					}
					break;
				}
				else {
					for(int i = 0 ; i < tmp1.seq ; i ++) {
						answer.append(tmp1.num + " ");
					}
					for(int i = 0 ; i < tmp2.seq ; i ++) {
						answer.append(tmp2.num + " ");
					}
				}
			}
			else if(tmp1.num + 1 == tmp2.num) {
				for(int i = 0 ; i < tmp1.seq ; i ++) {
					answer.append(tmp1.num + " ");
				}
				tmp1 = pQ.poll();
				pQ.offer(tmp2);
			}
			else {
				for(int i = 0 ; i < tmp1.seq ; i ++) {
					answer.append(tmp1.num + " ");
				}
				tmp1 = tmp2;
			}
			*/
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		if(N == 1) {
			System.out.println(arr[0]);
			return;
		}
		Arrays.sort(arr);
		int tmp = arr[0];
		int seq = 1;
		for(int i = 1 ; i < N ; i ++) {
			if(arr[i] != tmp) {
				pQ.offer(new Number(tmp, seq));
				tmp = arr[i];
				seq = 1;
			}
			else {
				seq ++;
			}
			if(i == N - 1) {
				pQ.offer(new Number(tmp, seq));
			}
		}
		System.out.println(T.solution());
	}
}
