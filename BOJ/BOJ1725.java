import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #1725 히스토그램
public class Main {
	static int[] arr;
	
	static long solution(int start, int end) {
		if(start == end) return (long)arr[start];
		
		int mid = (start + end) / 2;
		long answer = Math.max(solution(start, mid), solution(mid + 1, end));
		
		int lt = mid - 1, rt = mid + 1, h = arr[mid] ; 
		long w, maxW = Integer.MIN_VALUE;
		while(true){
			if(lt != start - 1 && rt != end + 1) {
				if(arr[lt] > arr[rt]) {
					h = Math.min(h, arr[lt]);
					w = ((long)rt - lt) * h;
					maxW = Math.max(w, maxW);
					lt --;
				}
				else {
					h = Math.min(h, arr[rt]);
					w = ((long)rt - lt) * h;
					maxW = Math.max(w, maxW);
					rt ++;
				}
			}
			else if(lt == start - 1 && rt != end + 1) {
				h = Math.min(h, arr[rt]);
				w = ((long)rt - lt) * h;
				maxW = Math.max(w, maxW);
				rt ++;
			}
			else if(lt != start - 1 && rt == end + 1) {
				h = Math.min(h, arr[lt]);
				w = ((long)rt - lt) * h;
				maxW = Math.max(w, maxW);
				lt --;
			}
			else break;
		}
		return Math.max(maxW, answer);
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		for(int i = 0 ; i < n ; i ++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(T.solution(0, n - 1));
	}
}
