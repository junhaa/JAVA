import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// BOJ #1114 통나무 자르기
public class Main {
	static int min = Integer.MAX_VALUE, start = Integer.MIN_VALUE, L;
	static List<Integer> cutList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		Set<Integer> set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K ; i ++){
			set.add(Integer.parseInt(st.nextToken()));
		}

		for(int key : set){
			cutList.add(L - key);
		}
		cutList.add(L);

		Collections.sort(cutList);

		C ++;
		main.binarySearch(L, C);
		System.out.println(min + " " + start);
	}

	private void binarySearch(int L, int C){
		int lt = 1;
		int rt = L;

		while(lt <= rt){
			int mid = (lt + rt) / 2;
			int curIdx = -1;
			for(int i = C ; i > 0 ; i --) {
				int nextIdx = findNextIdx(curIdx, mid);
				if(nextIdx == cutList.size() - 1 && mid < min) {
					min = mid;
					start = i > 1 ? L - cutList.get(cutList.size() - 2) : L - cutList.get(curIdx);
				}
				curIdx = nextIdx;
				if(curIdx == cutList.size() - 1 || curIdx == -1) break;
			}

			if(curIdx == cutList.size() - 1){
				rt = mid - 1;
			}
			else{
				lt = mid + 1;
			}
		}
	}

	private int findNextIdx(int curIdx, int len){
		int curLen = curIdx == -1 ? 0 : cutList.get(curIdx);
		int nextIdx = -1;
		for(int i = curIdx + 1 ; i < cutList.size() ; i ++){
			if(cutList.get(i) - curLen > len) return nextIdx;
			if(i == cutList.size() - 1){
				start = L - curLen;
			}
			nextIdx = i;
		}
		return nextIdx;
	}
}
