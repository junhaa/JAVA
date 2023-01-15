import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

// BOJ #A - 2023년이 기대되는 이유
public class Main {

	static int[] arr;
	static int length;
	static HashSet<Integer> set;
	static String strN;
	static long max;
	static void backTracking(int L) {
		if(L == length) {
			int tmp = 0;
			String tmpS = "";
			tmpS += strN.charAt(0);
			for(int i = 0 ; i < length ; i ++) {
				if(arr[i] == 0) tmpS += strN.charAt(i + 1);
				else {
					tmp += Integer.parseInt(tmpS);
					tmpS = "";
					tmpS += strN.charAt(i + 1);
				}
			}
			tmp += Integer.parseInt(tmpS);
			set.add(tmp);
			max = Math.max(tmp, max);
			return;
		}
		arr[L] = 0;
		backTracking(L + 1);
		arr[L] = 1;
		backTracking(L + 1);
	}
	
	static int solution(int n) {
		boolean num1 = false;
		strN = String.valueOf(n);
		set = new HashSet<>();
		length = strN.length() - 1;
		arr = new int[length];
		max = Long.MIN_VALUE;
		boolean flag = false;
		for(int i = 0 ; i < strN.length() ; i ++) {
			if(strN.charAt(i) == '1') continue;
			if(strN.charAt(i) == '0') continue;
			else flag = true;
		}
		if(!flag) return -1;
		backTracking(0);
		if(set.contains(1) && num1) return -1;
		
		int answer = 0;
		int p = 1;
		while(true) {
			long sum = 0;
			for(int i = 0 ; i < strN.length() ; i ++) {
				sum += Math.pow(strN.charAt(i) - '0', p);
			}
			if(sum > max) break;
			if(set.contains((int)sum)) answer ++;
			p ++;
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(test -- > 0) {
			int n = Integer.parseInt(br.readLine());
			int answer = T.solution(n);
			if(answer == -1) {
				sb.append("Hello, BOJ 2023!" + "\n");
			}
			else sb.append(answer + "\n");
		}
		System.out.println(sb);
	}
}
