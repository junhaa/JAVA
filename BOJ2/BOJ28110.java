import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// BOJ #28110 마지막 문제
class Difficult implements Comparable<Difficult> {
	int idx;
	int dif;

	public Difficult(int dix, int dif) {
		this.idx = dix;
		this.dif = dif;
	}

	@Override
	public int compareTo(Difficult o) {
		if(this.dif == o.dif){
			return this.idx - o.idx;
		}
		return o.dif - this.dif;
	}
}
public class Main {
	static int[] fibo = new int[46];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i = 0 ; i < n ; i ++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		List<Difficult> difList = new ArrayList<>();

		int cur = arr[0];
		for(int i = 1 ; i < n ; i ++){
			difList.add(new Difficult(i, (arr[i] - cur) % 2 == 1 ? arr[i] - cur - 1 : arr[i] - cur));
			cur = arr[i];
		}

		Collections.sort(difList);

		Difficult resDif = difList.get(0);
		int difCost = resDif.dif / 2;
		if(difCost == 0){
			System.out.println(-1);
			return;
		}


		System.out.println(arr[resDif.idx - 1] + difCost);
	}
}
