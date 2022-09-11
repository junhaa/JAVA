import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #1759 암호 만들기
public class Main {
	static char[] arr;
	static int L, C;
	static StringBuilder sb = new StringBuilder();
	static HashSet<Character> set = new HashSet<>();
	
	static void backTracking(int idx, int length, int mo, String str) {
		if(length == L) {
			if(mo >= 1 && length - mo >= 2) sb.append(str).append('\n');
			return;
		}
		if(idx == C - 1) return;
		if(set.contains(arr[idx + 1])) {
			backTracking(idx + 1, length + 1, mo + 1, str + arr[idx + 1]);
		}
		else backTracking(idx + 1, length + 1, mo, str + arr[idx + 1]);
		backTracking(idx + 1, length, mo, str);
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];
		set.add('a');
		set.add('e');
		set.add('i');
		set.add('o');
		set.add('u');
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < C ; i ++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		T.backTracking(-1, 0, 0, "");
		System.out.println(sb);
	}
}
