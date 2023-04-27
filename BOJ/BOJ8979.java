import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

//BOJ #8979 올림픽 
class Cont implements Comparable<Cont>{
	int gold, silver, bronze, num;
	public Cont(int num, int gold, int silver, int bronze) {
		this.num = num;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
	}
	@Override
	public int compareTo(Cont o) {
		if(this.gold == o.gold) {
			if(this.silver == o.silver) {
				return o.bronze - this.bronze;
			}
			else {
				return o.silver - this.silver;
			}
		}
		else return o.gold - this.gold;
	}
}
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
		HashMap<Integer, Cont> map = new HashMap<Integer, Cont>();
		StringTokenizer st= new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Cont> list = new ArrayList<Cont>();
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Cont(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		Cont last = new Cont(Integer.MIN_VALUE, Integer.MIN_VALUE, 0, 0);
		int[] rank = new int[N + 1];
		int cur = 0;
		int seq = 0;
		for(int i = 0 ; i < N ; i ++) {
			Cont curC = list.get(i);
			seq ++;
			if(last.gold == curC.gold && last.silver == curC.silver && last.bronze == curC.bronze);
			else {
				cur += seq;
				seq = 0;
			}
			rank[curC.num] = cur;
			last = curC;
		}
		System.out.println(rank[K]);
	}
}
