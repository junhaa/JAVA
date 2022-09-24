import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #4716 풍선
class Team implements Comparable<Team>{
	int length; // length < 0이면 A < B
	int num; // 개수
	boolean isA;
	int aLength;
	public Team(int length, int num, boolean isA, int aLength) {
		this.length = length;
		this.num = num;
		this.isA = isA;
		this.aLength = aLength;
	}
	@Override
	public int compareTo(Team o) {
		return o.length - this.length;
	}
}
public class Main {
	static PriorityQueue<Team> pQ = new PriorityQueue<>();
	
	static int solution(int A, int B) {
		int answer = 0;
		while(!pQ.isEmpty()) {
			Team tmp = pQ.poll();
			if(tmp.isA) { // A가 더 짧은 경로일 때
				if(tmp.num > A) {
					answer += A * tmp.aLength;
					tmp.num -= A;
					A = 0;
					answer += tmp.num * (tmp.aLength + tmp.length);
					B -= tmp.num;
				}
				else {
					answer += tmp.num * tmp.aLength;
					A -= tmp.num;
				}
			}
			else { // B가 더 짧은 경로일 때
				if(tmp.num > B) {
					answer += B * (tmp.aLength - tmp.length);
					tmp.num -= B;
					B = 0;
					answer += tmp.num * tmp.aLength;
					A -= tmp.num;
				}
				else {
					answer += tmp.num * (tmp.aLength - tmp.length);
					B -= tmp.num;
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N, K, Da, Db, A, B, length;
		boolean isA;
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			if(N == 0 && A == 0 && B == 0) break;
			for(int i = 0 ; i < N ; i ++) {
				st = new StringTokenizer(br.readLine());
				K = Integer.parseInt(st.nextToken());
				Da = Integer.parseInt(st.nextToken());
				Db = Integer.parseInt(st.nextToken());
				if(Da < Db) {
					length = Db - Da;
					isA = true;
				}
				else {
					length = Da - Db;
					isA = false;
				}
				pQ.offer(new Team(length, K, isA, Da));
			}		
			sb.append(T.solution(A, B) + "\n");
		}
		System.out.println(sb);
	}
}
