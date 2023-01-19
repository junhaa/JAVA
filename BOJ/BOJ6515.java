import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// BOJ #6515 Frequent values
class Query{
	int start, end, num;
	public Query(int start, int end, int sqrtN, int num) {
		this.start = start;
		this.end = end;
		//this.sqrtN = sqrtN;
		this.num = num;
	}
//	@Override
//	public int compareTo(Query o) {
//		if(this.start / this.sqrtN != o.start / this.sqrtN) return this.start / this.sqrtN - o.start / this.sqrtN;
//		else return this.end - o.end;
//	}
}
public class Main {

	static int[] cnt = new int[202020], arr, cntArr, answer;
	static int N, max;
	static ArrayList<Query> q = new ArrayList<>();

	static void add(int x) {
		if(cnt[x] > 0) cntArr[cnt[x]] --;
		cnt[x] ++;
		cntArr[cnt[x]] ++;
		max = Math.max(max, cnt[x]);
	}
	
	static void delete(int x) {
		if(cnt[x] == 0) return;
		cntArr[cnt[x]] --;
		if(cntArr[cnt[x]] == 0 && max == cnt[x]) max --;
		cnt[x] --;
		cntArr[cnt[x]] ++;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true) {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		if(N == 0) break;
		int Q = Integer.parseInt(st.nextToken());
		int sqrtN = (int)Math.sqrt(N);
		arr = new int[N];
		cntArr = new int[N + 1];
		answer = new int[Q];
		max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken()) + 100000;
		}
		int idx = 0;
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			q.add(new Query(start, end, sqrtN, i));
		}
		Collections.sort(q, new Comparator<Query>() {
			@Override
			public int compare(Query q1, Query q2) {
				if(q1.start / sqrtN != q2.start / sqrtN) return q1.start / sqrtN - q2.start / sqrtN;
				else return q1.end - q2.end;
			}
		});
		
		int s = q.get(0).start;
		int e = q.get(0).end;
		
		for(int i = s ; i <= e ; i ++) {
			T.add(arr[i]);
		}
		
		answer[q.get(0).num] = max;
		
		for(int i = 1 ; i < q.size() ; i ++) {
			Query tmp = q.get(i);
			while(s > tmp.start) T.add(arr[--s]);
			while(e < tmp.end) T.add(arr[++e]);
			while(s < tmp.start) T.delete(arr[s++]);
			while(e > tmp.end) T.delete(arr[e--]);
			answer[tmp.num] = max;
		}
		
		for(int x : answer) sb.append(x + "\n");
		
		}
		System.out.println(sb);
	}
}
