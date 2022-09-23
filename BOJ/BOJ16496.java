import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #16496 큰 수 만들기
class Num implements Comparable<Num>{
	int length;
	String num;
	public Num(String num) {
		length = num.length();
		this.num = num;
		int idx = 0;
		while(this.num.length() < 10) {
			this.num += num.charAt(idx % num.length());
			idx ++;
		}
	}
	@Override
	public int compareTo(Num o) {
		if(this.num == o.num) {
			return this.length - o.length;
		}
		else {
			long oNum = Long.parseLong(o.num);
			long thisNum = Long.parseLong(this.num); 
			if(oNum > thisNum) return 1;
			else if(oNum < thisNum) return -1;
			else return 0;
		}
		}
}

public class Main {
	
	static PriorityQueue<Num> pQ = new PriorityQueue<>();
	
	static BigInteger solution() {
		String answer = "";
		while(!pQ.isEmpty()) {
			Num tmp = pQ.poll();
			for(int i = 0 ; i < tmp.length ; i ++){
				answer += tmp.num.charAt(i);
			}
		}
		return new BigInteger(answer);
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			pQ.offer(new Num(st.nextToken()));
		}
		System.out.println(T.solution());
	}
}
