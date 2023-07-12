import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #20440 🎵니가 싫어 싫어 너무 싫어 싫어 오지 마 내게 찝쩍대지마🎵
class Query implements Comparable<Query>{
	int start, end;
	public Query(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(Query o) {
		return this.start - o.start;
	}
	
}
public class Main {

	static HashSet<Integer> set = new HashSet<Integer>();
	static ArrayList<Query> qry = new ArrayList<Query>();
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	static HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			qry.add(new Query(start, end));
			set.add(start);
			set.add(end);
		}
		ArrayList<Integer> tmp = new ArrayList<Integer>(set);
		Collections.sort(tmp);
		Collections.sort(qry);
		int[] prefix = new int[tmp.size() + 1]; 
		int[] arr = new int[tmp.size() + 1];
		for(int i = 0 ; i < tmp.size() ; i ++) {
			map.put(tmp.get(i), i);
			map2.put(i, tmp.get(i));
		}
		for(int i = 0 ; i < qry.size() ; i ++) {
			Query tmpQ = qry.get(i);
			int start = map.get(tmpQ.start) + 1;
			int end = map.get(tmpQ.end) + 1;
			arr[start] ++;
			arr[end] --;
		}
		prefix[0] = 0;
		for(int i = 1 ; i < tmp.size() + 1 ; i ++) {
			prefix[i] = prefix[i - 1] + arr[i];
		}
		int max = Integer.MIN_VALUE;
		int startM = -1;
		int lastM = -1;
		for(int i = 0 ; i < prefix.length ; i ++) {
			max = Math.max(prefix[i], max);
		}
		for(int i = 0 ; i < prefix.length ; i ++) {
			if(startM == -1 && prefix[i] == max) {
				startM = i - 1;
			}
			if(startM != -1 && prefix[i] != max) {
				lastM = i - 1;
				break;
			}
		}
		if(lastM == -1) {
			lastM = prefix.length - 1;
		}
		System.out.println(max + "\n" + map2.get(startM) + " " + map2.get(lastM));
	}
}
