import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
// BOJ #3825 은행수
public class Main {
	static boolean[] isPrime = new boolean[20001];
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.init();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(T -- > 0){
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			sb.append(isPrime[(m * m) + (n * n)] ? "P\n" : "C\n");
		}
		System.out.println(sb);
	}

	private void init(){
		Arrays.fill(isPrime, true);
		List<Integer> list = new ArrayList<>();
		for(int i = 0 ; i <= 100 ; i ++){
			list.add(i * i);
		}

		Set<Integer> listPow = new HashSet<>();
		for(int i = 0 ; i < 100 ; i ++){
			for(int j = i ; j < 100 ; j ++){
				listPow.add(list.get(i) + list.get(j));
			}
		}

		listPow.remove(1);
		List<Integer> toList = new ArrayList<>(listPow);
		Collections.sort(toList);
		for(int i = 0 ; i < toList.size() ; i ++){
			for(int j = i ; j < toList.size() ; j ++){
				int mul = toList.get(i) * toList.get(j);
				if(mul > 20000) break;
				isPrime[mul] = false;
			}
		}
	}
}
