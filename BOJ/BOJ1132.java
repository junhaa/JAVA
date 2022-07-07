import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//BOJ #1132 합
/*
class sumAlpha{
	char Char;
	long sum;
	public sumAlpha (char Char, long sum) {
		this.Char = Char;
		this.sum = sum;
	}
}
*/
public class Main {
	public static long solution(HashMap<Character, Long> mapCnt, ArrayList<Character> first) {
		long answer = 0;
		/*
		ArrayList<sumAlpha> listSum = new ArrayList<>();
		for(char x : mapCnt.keySet()) {
			listSum.add(new sumAlpha(x, mapCnt.get(x)));
		}
		*/
		
		for(int i = 9 ; i >= 0 ; i --) {
			if(mapCnt.isEmpty()) break;
			else if(i != 0 && first.size() == i) {
				long maxVal = Long.MIN_VALUE;
				Character maxKey = null; 
				for(char x : mapCnt.keySet()) {
					if(maxVal < mapCnt.get(x) && first.contains(x)) {
						maxKey = x;
						maxVal = mapCnt.get(x);
					}
				}
				first.remove(first.indexOf(maxKey));
				mapCnt.remove(maxKey);
				answer += maxVal * i;
			}
			else {
				long maxVal = Long.MIN_VALUE;
				Character maxKey = null; 
				for(char x : mapCnt.keySet()) {
					if(maxVal < mapCnt.get(x)) {
						maxKey = x;
						maxVal = mapCnt.get(x);
					}
				}
				if(first.contains(maxKey)) first.remove(first.indexOf(maxKey));
				mapCnt.remove(maxKey);
				answer += maxVal * i;
			}
			/*
			if(listSum.isEmpty()) break;
			else if(first.size() == i) {
				sumAlpha tmp = listSum.get(0);
				for(int k = 1 ; k < listSum.size() ; i ++) {
					if(tmp.sum < listSum.get(k).sum && ) {
						tmp = listSum.get(k);
						listSum.remove(k);
					}
				}
			}
			else {
				sumAlpha tmp = listSum.get(0);
				for(int k = 1 ; k < listSum.size() ; i ++) {
					if(tmp.sum < listSum.get(k).sum) {
						tmp = listSum.get(k);
						listSum.remove(k);
					}
				}
				
				for(char x : first) {
					if(tmp.Char == x) first.remove(first.indexOf(tmp.Char));
				}
			}
			answer += tmp.sum * i;
			*/
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashMap<Character, Long> mapCnt = new HashMap<>();
		ArrayList<Character> first = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			String tmp = br.readLine();
			long cnt = 1;
			for(int k = tmp.length() - 1 ; k  >= 0 ; k --) {
				if(k == 0) {
					boolean flag = false; // 있으면 true;
					for(char x : first) {
						if(x == tmp.charAt(k)) flag = true;
					}
					if(!flag) first.add(tmp.charAt(k));
				
				}
				mapCnt.put(tmp.charAt(k), mapCnt.getOrDefault(tmp.charAt(k), (long)0) + cnt);
				cnt *= 10;
			}
		}
		System.out.println(T.solution(mapCnt, first));
	}
}
