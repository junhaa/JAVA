import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #13547 수열과 쿼리 5
class Query implements Comparable<Query>{
	int start, end, num;
	int sqrtN;
	public Query(int start, int end, int sqrtN, int num) {
		this.start = start;
		this.end = end;
		this.sqrtN = sqrtN;
		this.num = num;
	}
	@Override
	public int compareTo(Query o) {
		if(this.start / sqrtN == o.start / sqrtN) return this.end - o.end;
		else if(this.start / sqrtN > o.start / sqrtN) return 1;
		else return -1;
	}
}
public class Main {

	static int[] cnt = new int[1000001], arr, answerArr;
	static ArrayList<Query> q = new ArrayList<>();
	static int answer;
	
	public void add(int idx) {
		if(++cnt[arr[idx]] == 1) answer ++;
	}
	
	public void delete(int idx ) {
		if(--cnt[arr[idx]] == 0) answer --;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int sqrtN = (int)Math.sqrt(N);
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		answerArr = new int[M];
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			Query tmp = new Query(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1 , sqrtN, i);
			q.add(tmp);
		}
		Collections.sort(q);
		int s = q.get(0).start;
		int e = q.get(0).end;
		for(int i = s ; i <= e ; i ++) {
			if(++ cnt[arr[i]] == 1) {
				answer ++;
			}
		}
		answerArr[q.get(0).num] = answer;
		
		for(int i = 1 ; i < M ; i ++) {
			Query tmp = q.get(i);
			while(tmp.start < s) {
				T.add(--s);
			}
			while(tmp.start > s) {
				T.delete(s++);
			}
			while(tmp.end > e) {
				T.add(++e);
			}
			while(tmp.end < e) {
				T.delete(e --);
			}
			answerArr[tmp.num] = answer;
		}
		for(int i = 0 ; i < M ; i ++) {
			sb.append(answerArr[i] + "\n");
		}
		System.out.println(sb);
	}
}
