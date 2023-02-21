import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #17089 세 친구
class Friend implements Comparable<Friend>{
	int num, cnt = 0;
	public Friend(int num) {
		this.num = num;
	}
	@Override
	public int compareTo(Friend o) {
		return this.cnt - o.cnt;
	}
}
public class Main {

	static boolean[][] ch;
	static Friend[] arr; 
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int min = Integer.MAX_VALUE;
		ch = new boolean[N][N];
		arr = new Friend[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = new Friend(i);
		}
		
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			arr[a].cnt ++;
			arr[b].cnt ++;
			ch[a][b] = true;
			ch[b][a] = true;
		}
		Arrays.sort(arr);
		for(int i = 0 ; i < N ; i ++) {
			for(int j = i + 1 ; j < N ; j ++) {
				if(ch[arr[i].num][arr[j].num]) {
					for(int k = j + 1 ; k < N ; k ++) {
						if(ch[arr[i].num][arr[j].num] && ch[arr[j].num][arr[k].num] && ch[arr[i].num][arr[k].num]) {
							min = Math.min(min, arr[i].cnt + arr[j].cnt + arr[k].cnt);
						}
					}
				}
			}
		}
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else System.out.println(min - 6);
	}
}
