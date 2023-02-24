import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

// BOJ #2668 숫자 고르기
public class Main {
	static int[] arr;
	static int[] ch; // 0 !visited 1 visited 2 cycle 3 cycle X
	static HashSet<Integer> list = new HashSet<>();
	
	static int DFS(int cur) {
		if(ch[cur] == 1) {
			ch[cur] = 2;
			list.add(cur);
			return cur;
		}
		ch[cur] = 1;
		if(ch[arr[cur]] == 3) { 
			ch[cur] = 3;
			return -1;
		}
		int tmp = DFS(arr[cur]);
		if(tmp != -1) {
			list.add(cur);
			ch[cur] = 2;
			if(tmp == cur) tmp = -1;
		}
		else ch[cur] = 3;
		return tmp;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		ch = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 1 ; i <= N ; i ++) {
			if(ch[i] == 0) {
				T.DFS(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> slist = new ArrayList<>(list);
		Collections.sort(slist);
		sb.append(slist.size() + "\n");
		for(int i = 0 ; i < slist.size() ; i ++) {
			sb.append(slist.get(i) + "\n");
		}
		System.out.println(sb);
	}
}
