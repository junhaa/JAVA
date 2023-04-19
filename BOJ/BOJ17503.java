import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #17503 맥주 축제 	
class Beer implements Comparable<Beer>{
	int v, c;
	public Beer(int v, int c) {
		this.v = v; // 선호도 
		this.c = c; // 도수 레벨 
	}
	@Override
	public int compareTo(Beer o) {
		return o.v - this.v;
	}
}
public class Main {
	static int N, M, K;
	static ArrayList<Beer> list = new ArrayList<Beer>();
	static int min = Integer.MAX_VALUE;
	
	
	static void binarySearch() {
		int lt = 1;
		int rt = Integer.MAX_VALUE;
		while(lt <= rt) {
			int mid = (int)(((long)lt + rt) / 2); // 도수 레벨 
			int tmpSum = 0;
			int cnt = 0;
			for(int i = 0 ; i < K ; i ++) {
				Beer tmp = list.get(i);
				if(tmp.c <= mid) { 
					tmpSum += tmp.v;
					cnt ++;
					if(cnt == N) break;
				}
			}
			if(tmpSum < M) {
				lt = mid + 1;
			}
			else {
				if(cnt != N) {
					lt = mid + 1;
				}
				else {
					min = Math.min(min, mid);
					rt = mid - 1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < K ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Beer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		long sum = 0;
		for(int i = 0 ; i < N ; i ++) {
			sum += list.get(i).v;
		}
		if(sum < M) {
			System.out.println(-1);
			return;
		}
		T.binarySearch();
		System.out.println(min);
	}
}
