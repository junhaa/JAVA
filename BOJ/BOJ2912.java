import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #2912 백설공주와 난쟁이
class Query implements Comparable<Query>{
	int start, end, num, sqrtN;
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

	static int[] cntArr, arr, cnt, answer;
	static ArrayList<Query> q = new ArrayList<>();
	static int max = 0, N;
	
	static void add(int x) {
		cntArr[cnt[x] + N] --;
		cnt[x] ++;
		cntArr[cnt[x] + N] ++;
		max = Math.max(max, cnt[x]);
	}
	
	static void delete(int x) {
		if(cntArr[cnt[x] + N] == 1 && cnt[x] == max) max --;
		cntArr[cnt[x] + N] --;
		cnt[x] --;
		cntArr[cnt[x] + N] ++;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int sqrtN = (int)Math.sqrt(N);
		int C = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		cnt = new int[C + 1];
		cntArr = new int[N + 1 + N];
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		answer = new int[M];
		
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			q.add(new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), sqrtN, i));
		}
		Collections.sort(q);
		
		int s = q.get(0).start;
		int e = q.get(0).end;
		for(int i = s ; i <= e ; i ++) {
			T.add(arr[i]);
		}
		if((e - s + 1) / 2 < max) {
			for(int i = 1 ; i <= C ; i ++) {
				if(cnt[i] == max) {
					answer[q.get(0).num] = i;
					break;
				}
			}
		}
		else answer[q.get(0).num] = -1;
		for(int i = 1 ; i < M ; i ++) {
			Query tmp = q.get(i);
			while(e < tmp.end) T.add(arr[++e]);
			while(s > tmp.start) T.add(arr[--s]);
			while(e > tmp.end) T.delete(arr[e--]);
			while(s < tmp.start) T.delete(arr[s++]);
			if((e - s + 1) / 2 < max) {
				for(int k = 1 ; k <= C ; k ++) {
					if(cnt[k] == max) {
						answer[q.get(i).num] = k;
						break;
					}
				}
			}
			else answer[q.get(i).num] = -1;
		}
		for(int i = 0 ; i < M ; i ++) {
			if(answer[i] != -1) sb.append("yes " + answer[i] + "\n");
			else sb.append("no\n");
		}
		System.out.println(sb);
	}
}
