import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #27923 햄버거최대 몇개드실수있나요?
public class Main {

	static int[] sus, prefix;
	static ArrayList<Integer> bur = new ArrayList<>(), cola = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		if(L > N) L = N;
		Queue<Integer> Q = new LinkedList<>();
		prefix = new int[N + 1];
		sus = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			bur.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(bur, Collections.reverseOrder());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K ; i ++) {
			int tmp = Integer.parseInt(st.nextToken()) - 1;
			sus[tmp] ++;
		}
		
		for(int i = 1 ; i <= N ; i ++) {
			prefix[i] = prefix[i - 1] + sus[i - 1];
		}
		for(int i = 1 ; i < L ; i ++) {
			cola.add(prefix[i]);
		}
		for(int i = L ; i <= N ; i ++) {
			cola.add(prefix[i] - prefix[i - L]);
		}
		Collections.sort(cola, Collections.reverseOrder());
		long cap = 0;
		for(int i = 0 ; i < N ; i ++) {
			if(cola.get(i) >= 30) continue;
			int div = (int)Math.pow(2, cola.get(i));
			cap += bur.get(i) / div;
		}
		System.out.println(cap);
	}
}
