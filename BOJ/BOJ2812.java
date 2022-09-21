import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #2812 크게 만들기
public class Main {
	static ArrayList<Integer> list = new ArrayList<>();

	static StringBuilder solution(int N, int K, String input) {
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for(int i = 0 ; i < N ; i ++) {
			int tmp = input.charAt(i) - '0';
			int length = list.size() - 1;
			if(list.isEmpty()) list.add(tmp);
			else if(list.get(length) >= tmp) {
				list.add(tmp);
			}
			else {
				for(int k = length ; k >= 0 ; k --) {
					if(list.get(k) < tmp) {
						list.remove(k);
						K --;
						if(K == 0) {
							for(int j = i ; j < N ; j ++) {
								list.add(input.charAt(j) - '0');
							}
							for(int l = 0 ; l < list.size() ; l ++) {
								sb.append(list.get(l));
							}
							return sb;
						}
					}
					else {
						break;
					}
				}
				list.add(tmp);
			}
		}
		for(int l = 0 ; l < list.size() - K ; l ++) {
			sb.append(list.get(l));
		}
		return sb;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String input = br.readLine();
		System.out.println(T.solution(N, K, input));
	}
}
