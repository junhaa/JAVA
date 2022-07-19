import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #1092 น่
public class Main {
	
	public static int solution(ArrayList<Integer> crane, ArrayList<Integer> box) {
		Collections.sort(crane, Collections.reverseOrder());
		Collections.sort(box, Collections.reverseOrder());
		if(crane.get(0) < box.get(0)) return -1;
		int time = 0;
		while(!box.isEmpty()) {
			int bIndex = 0;
			int cIndex = 0;
			int length = box.size();
			for(int i = 0 ; i < length ; i ++) {
				if(box.get(bIndex) <= crane.get(cIndex)) { 
					box.remove(bIndex);
					cIndex ++;
					if(cIndex == crane.size()) break;
				}
				else { 					
					bIndex ++;
				}
			}
			time ++;
		}
		return time;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> crane = new ArrayList<>();
		ArrayList<Integer> box = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			crane.add(Integer.parseInt(st.nextToken()));
		}
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			box.add(Integer.parseInt(st.nextToken()));
		}
		System.out.println(T.solution(crane, box));
	}
}
