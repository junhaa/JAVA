import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// BOJ #15337 Starting a Scenic Railroad Service
class Query{
	int a, b;

	public Query(int a, int b) {
		this.a = a;
		this.b = b;
	}
}
public class Main {
	static int[] imos, startSum, endSum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		imos = new int[100002]; // 1 - based
		startSum = new int[100002];
		endSum = new int[100002];

		ArrayList<Query> q = new ArrayList<>();

		for(int i = 0 ; i < N ; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			imos[a] ++;
			imos[b] --;
			startSum[a] ++;
			endSum[b] ++;
			q.add(new Query(a, b));
		}

		for(int i = 1 ; i <= 100001 ; i ++){
			imos[i] += imos[i - 1];
			startSum[i] += startSum[i - 1];
			endSum[i] += endSum[i - 1];
		}
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;

		for(int i = 0 ; i <= 100001 ; i ++){
			max2 = Math.max(max2, imos[i]);
		}

		for(int i = 0 ; i < N ; i ++){
			Query cur = q.get(i);
			int val = N;
			val -= startSum[100001] - startSum[cur.b - 1];
			val -= endSum[cur.a];
			max1 = Math.max(val, max1);
		}
		System.out.println(max1 + " " + max2);
	}
}
