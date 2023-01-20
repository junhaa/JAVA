import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #12999 화려한 마을 3
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
		if(this.start / sqrtN != o.start / sqrtN) return this.start / sqrtN - o.start / sqrtN;
		else return this.end - o.end;
	}
}
public class Main {

	static int[] arr, cntArr, cnt = new int[200001], answer;
	static int max = Integer.MIN_VALUE;
	static ArrayList<Query> q = new ArrayList<>();
	
	public void add(int x) {
		if(cnt[x] != 0) cntArr[cnt[x]] --;
		cnt[x] ++;
		cntArr[cnt[x]] ++;
		max = Math.max(max, cnt[x]);
	}
	
	public void delete(int x) {
		cntArr[cnt[x]] --;
		if(cntArr[cnt[x]] == 0 && cnt[x] == max) max --;
		cnt[x] --;
		cntArr[cnt[x]] ++;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int sqrtN = (int)Math.sqrt(N);
		arr = new int[N + 1];
		cntArr = new int[N + 1];
		answer = new int[Q];
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken()) + 100000;
		}
		
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			q.add(new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), sqrtN, i));
		}
		
		Collections.sort(q);
		int s = q.get(0).start;
		int e = q.get(0).end;
		
		for(int i = s ; i <= e ; i ++) {
			T.add(arr[i]);
		}
		
		answer[q.get(0).num] = max;
		
		for(int i = 1 ; i < Q ; i ++) {
			Query tmp = q.get(i);
			while(s > tmp.start) T.add(arr[--s]);
			while(e < tmp.end) T.add(arr[++e]);
			while(s < tmp.start) T.delete(arr[s++]);
			while(e > tmp.end) T.delete(arr[e--]);
			answer[tmp.num] = max;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < Q ; i ++) {
			sb.append(answer[i] + "\n");
		}
		System.out.println(sb);
	}
}
