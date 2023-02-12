import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #BOJ27466 - 그래서 대회 이름 뭐로 하죠

public class Main {

	static char[] strArr;
	static int N, M;
	static boolean flag = true;
	
	static void solution(int idx, int num, int remove) {
		if(remove == N - M) {
			int l = 1;
			for(int i = strArr.length - 1 ; i >= 0 ; i --) {
				if(strArr[i] == '-') continue;
				if(l == 1) {
					if(strArr[i] != 'A' && strArr[i] != 'E' && strArr[i] != 'I' && strArr[i] != 'O' && strArr[i] != 'U') l ++;
					else {
						flag = false;
						return;
					}
				}
				else if(l == 2 || l == 3) {
					if(strArr[i] == 'A') l ++;
					else {
						flag = false;
						return;
					}
				}
				if(l > 3) return;
			}
		}
		if(num == 1) {
			if(strArr[idx] != 'A' && strArr[idx] != 'E' && strArr[idx] != 'I' && strArr[idx] != 'O' && strArr[idx] != 'U') solution(idx - 1, num + 1, remove);
			else { 
				strArr[idx] = '-';
				solution(idx - 1, num, remove + 1);
			}
		}
		else if(num == 2 || num == 3) {
			if(strArr[idx] == 'A') solution(idx - 1, num + 1, remove);
			else {
				strArr[idx] = '-';
				solution(idx - 1, num, remove + 1);
			}
		}
		else {
			strArr[idx] = '-';
			solution(idx - 1, num, remove + 1);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		strArr = br.readLine().toCharArray(); 
		T.solution(strArr.length - 1, 1, 0);
		if(flag) {
			System.out.println("YES");
			for(int i = 0 ; i < strArr.length ; i ++) {
				if(strArr[i] == '-') continue;
				System.out.print(strArr[i]);
			}
		}
		else System.out.println("NO");
	}
}
