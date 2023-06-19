import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #15720 카우버거
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int sB = 0;
		int sC = 0;
		int sD = 0;
		int tB = 0;
		int tC = 0;
		int tD = 0;
		ArrayList<Integer> Blist = new ArrayList<>();
		ArrayList<Integer> Clist = new ArrayList<>();
		ArrayList<Integer> Dlist = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < B ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			Blist.add(tmp);
			tB += tmp;
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < C ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			Clist.add(tmp);
			tC += tmp;
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < D ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			Dlist.add(tmp);
			tD += tmp;
		}
		Collections.sort(Blist, Collections.reverseOrder());
		Collections.sort(Clist, Collections.reverseOrder());
		Collections.sort(Dlist, Collections.reverseOrder());
		double answer = 0;
		int len = Math.min(Blist.size(), Clist.size());
		len = Math.min(len, Dlist.size());
		for(int i = 0 ; i < len ; i ++) {
			int tsum = Blist.get(i) + Clist.get(i) + Dlist.get(i);
			sB += Blist.get(i);
			sC += Clist.get(i);
			sD += Dlist.get(i);
			answer += tsum * 0.9;
		}
		System.out.println(tB + tC + tD);
		System.out.println((int)(answer + tB + tC + tD - sB - sC - sD));
	}
}	
