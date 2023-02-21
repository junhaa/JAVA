import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #25391 특별상
class Score implements Comparable<Score>{
	int s1, s2;
	public Score(int s1, int s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	@Override
	public int compareTo(Score o) {
		return (o.s1) - (this.s1);
	}
}
public class Main {

	static int[] arr1, arr2;
	static Score[] arrs;
	static HashSet<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long answer = 0;
		arr1 = new int[N];
		arr2 = new int[N];
		arrs = new Score[N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			arr1[i] = tmp1;
			arr2[i] = tmp2;
			arrs[i] = new Score(tmp1, tmp2);
		}
		Arrays.sort(arrs,(Comparator<Score>) (Score a, Score b) -> {return b.s2 - a.s2;});
		for(int i = 0 ; i < K ; i ++) {
			set.add(arrs[i].s2);
			answer += arrs[i].s1;
		}
		Arrays.sort(arrs);
		int cnt = 0;
		for(int i = 0 ; i < N ; i ++) {
			if(set.contains(arrs[i].s2)) continue;
			answer += arrs[i].s1;
			cnt++;
			if(cnt == M) break;
		}
		System.out.println(answer);
	}
}
