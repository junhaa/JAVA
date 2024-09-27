import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// BOJ #8982 수족관 1
class Hole implements Comparable<Hole>{
	int depth;
	int mapIdx;

	public Hole(int depth, int mapIdx) {
		this.depth = depth;
		this.mapIdx = mapIdx;
	}

	@Override
	public int compareTo(Hole o) {
		return o.depth - this.depth;
	}
}
public class Main {
	/**
	 * 가장 깊은 구멍부터 좌 우 탐색
	 */

	static int[] depth, length, curDepth; // 깊이, 길이, 현재 깊이;
	static Map<Integer, Integer> startX = new HashMap<>();
	static List<Hole> holeList = new ArrayList<>();

	static int idxLen;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Main main = new Main();
		main.createMap(br);
		main.addHole(br);

		main.openHole();

		System.out.println(main.calcRemainWater());

	}

	private int calcRemainWater(){
		int sum = 0;
		for(int i = 0 ; i < idxLen ; i ++){
			sum += length[i] * (depth[i] - curDepth[i]);
		}
		return sum;
	}

	// 구멍 오픈 (양쪽으로 확장)
	private void openHole(){
		for (Hole hole : holeList) {
			extendRightFromHole(hole);
			extendLeftFromHole(hole);
		}
	}

	private void extendRightFromHole(Hole hole){
		int moveDepth = hole.depth;
		for(int i = hole.mapIdx ; i < idxLen ; i ++){
			// 이미 더 깊은 경우 break;
			if(curDepth[i] >= moveDepth) break;
			curDepth[i] = moveDepth > depth[i] ? depth[i] : Math.min(moveDepth, depth[i]);
			moveDepth = curDepth[i];
		}
	}

	private void extendLeftFromHole(Hole hole){
		int moveDepth = hole.depth;
		for(int i = hole.mapIdx - 1 ; i >= 0 ; i --){
			if(curDepth[i] >= moveDepth) break;
			curDepth[i] = moveDepth > depth[i] ? depth[i] : Math.min(moveDepth, depth[i]);
			moveDepth = curDepth[i];
		}
	}

	// 구멍 리스트 추가
	private void addHole(BufferedReader br)throws IOException{
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++){
			st = new StringTokenizer(br.readLine());
			int idx = startX.get(Integer.parseInt(st.nextToken()));
			holeList.add(new Hole(depth[idx], idx));
		}

		Collections.sort(holeList);
	}

	// map 생성
	private void createMap(BufferedReader br) throws IOException {
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		idxLen = (N - 2) / 2;
		depth = new int[idxLen];
		length = new int[idxLen];
		curDepth = new int[idxLen];
		int x = 0;
		int y = 0;
		br.readLine();
		for(int i = 1 ; i < N - 1 ; i ++){
			int arrIdx = (i - 1) / 2;
			st = new StringTokenizer(br.readLine());
			// 깊이 변경
			if(i % 2 == 1){
				st.nextToken();
				y = Integer.parseInt(st.nextToken());
			}
			// 너비 변경
			else{
				int nx = Integer.parseInt(st.nextToken());
				depth[arrIdx] = y;
				length[arrIdx] = nx - x;
				startX.put(x, arrIdx);
				x = nx;
			}
		}
		br.readLine();
	}
}
