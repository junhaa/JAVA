import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #8462 배열의 힘
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
	
	static int[] arr, cnt = new int[1000001];
	static long[] answer;
	static long sum = 0;
	static ArrayList<Query> q = new ArrayList<>();

	public void add(int x) {
		sum -= (long)x * cnt[x] * cnt[x];
		cnt[x] ++;
		sum += (long)x * cnt[x] * cnt[x];
	}
	
	public void delete(int x) {
		sum -= (long)x * cnt[x] * cnt[x];
		cnt[x] --;
		sum += (long)x * cnt[x] * cnt[x];
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int sqrtN = (int)Math.sqrt(n);
		int t = Integer.parseInt(st.nextToken());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= n ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		answer = new long[t];
		for(int i = 0 ; i < t ; i ++) {
			st = new StringTokenizer(br.readLine());
			q.add(new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), sqrtN, i));
		}
		Collections.sort(q);
		
		int s = q.get(0).start;
		int e = q.get(0).end;
		for(int i = s ; i <= e;  i ++) {
			T.add(arr[i]);
		}
		answer[q.get(0).num] = sum;
		
		for(int i = 1 ; i < t; i ++) {
			Query tmp = q.get(i);
			while(s > tmp.start) T.add(arr[--s]);
			while(e < tmp.end) T.add(arr[++e]);
			while(s < tmp.start) T.delete(arr[s++]);
			while(e > tmp.end) T.delete(arr[e--]);
			answer[tmp.num] = sum;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < t ; i ++) {
			sb.append(answer[i] + "\n");
		}
		System.out.println(sb);
	}
}
