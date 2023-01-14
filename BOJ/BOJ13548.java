import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #13548 수열과 쿼리 6

class Query implements Comparable<Query>{
	int start, end, sqrtN, num;
	public Query(int start, int end, int sqrtN, int num) {
		this.start = start;
		this.end = end;
		this.sqrtN = sqrtN;
		this.num = num;
	}
	@Override
	public int compareTo(Query o) {
		if(this.start / this.sqrtN != o.start / this.sqrtN) return this.start / sqrtN - o.start / sqrtN;
		else return this.end - o.end;
	}
}

public class Main {

	static int[] cnt = new int[100100], cntArr = new int[200002], arr, answerArr;
	static ArrayList<Query> q = new ArrayList<>();
	static int answer = 0, m = 100000;
	
	
	static void add(int idx) {
		cntArr[cnt[arr[idx]] + m] --;
		cnt[arr[idx]] ++;
		cntArr[cnt[arr[idx]] + m] ++;
		answer = Math.max(answer, cnt[arr[idx]]);
	}
	
	static void delete(int idx) {
		if(--cntArr[cnt[arr[idx]] + m] == 0 && cnt[arr[idx]] == answer) answer --;
		cnt[arr[idx]] --;
		cntArr[cnt[arr[idx]] + m] ++;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sqrtN = (int)Math.sqrt(N);
		StringBuilder sb = new StringBuilder();
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		answerArr = new int[M];
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			q.add(new Query(start, end, sqrtN, i)); 
		}
		Collections.sort(q);
		int s = q.get(0).start;
		int e = q.get(0).end;
		for(int i = s ; i <= e ; i ++) {
			T.add(i);
		}
		answerArr[q.get(0).num] = answer;
		for(int i = 1 ; i < M ; i ++) {
			Query tmp = q.get(i);
			while(tmp.start < s) T.add(--s);
			while(tmp.start > s) T.delete(s++);
			while(tmp.end < e) T.delete(e --);
			while(tmp.end > e) T.add(++ e);
			answerArr[tmp.num] = answer;
		}
		
		for(int i = 0 ; i < M ; i ++) {
			sb.append(answerArr[i]);
		}
		System.out.println(sb);
	}
}
