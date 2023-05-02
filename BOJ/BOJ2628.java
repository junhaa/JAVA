import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #2628 종이자르기
public class Main {

	static HashSet<Integer> setn = new HashSet<>(), setm = new HashSet<>();
	static ArrayList<Integer> sn,sm;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 0) {
				setm.add(Integer.parseInt(st.nextToken()));
			}
			else {
				setn.add(Integer.parseInt(st.nextToken()));
			}
		}
		sn = new ArrayList<>(setn);
		sn.add(0);
		sn.add(N);
		sm = new ArrayList<>(setm);
		sm.add(0);
		sm.add(M);
		Collections.sort(sn);
		Collections.sort(sm);
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < sm.size() ; i ++) {
			if(i == sm.size() - 1) break;
			for(int j = 0 ; j < sn.size() ; j ++) {
				if(j == sn.size() - 1) break;
				max = Math.max((sn.get(j + 1) - sn.get(j)) * (sm.get(i + 1) - sm.get(i)), max); 
			}
		}
		System.out.println(max);
	}
}
