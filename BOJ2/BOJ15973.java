import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #15973 두 박스

class Rect {
	public Rect(int lx, int rx, int ly, int ry) {
		this.lx = lx;
		this.rx = rx;
		this.ly = ly;
		this.ry = ry;
	}

	int lx, rx, ly, ry;
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Rect[] rects = new Rect[2];
		int ax1 = Integer.parseInt(st.nextToken());
		int ay1 = Integer.parseInt(st.nextToken());
		int ax2 = Integer.parseInt(st.nextToken());
		int ay2 = Integer.parseInt(st.nextToken());
		rects[0] = new Rect(ax1, ax2, ay1, ay2);
		st = new StringTokenizer(br.readLine());

		int bx1 = Integer.parseInt(st.nextToken());
		int by1 = Integer.parseInt(st.nextToken());
		int bx2 = Integer.parseInt(st.nextToken());
		int by2 = Integer.parseInt(st.nextToken());
		rects[1] = new Rect(bx1, bx2, by1, by2);

		// 가로 체크
		if (rects[0].lx > rects[1].lx) {
			Rect tmp = rects[0];
			rects[0] = rects[1];
			rects[1] = tmp;
		}

		// x축 들어오는 경우
		if (rects[0].rx >= rects[1].lx) {
			// rects[0]의 ly가 더 작은 경우
			if (rects[0].ly <= rects[1].ly) {
				if (rects[0].ry >= rects[1].ly) {
					if (rects[0].ry == rects[1].ly) {
						if (rects[0].rx == rects[1].lx) {
							System.out.println("POINT");
						} else {
							System.out.println("LINE");
						}
					} else {
						if (rects[0].rx == rects[1].lx) {
							System.out.println("LINE");
						} else {
							System.out.println("FACE");
						}
					}
				} else {
					System.out.println("NULL");
				}
			} else {
				if (rects[1].ry >= rects[0].ly) {
					if (rects[1].ry == rects[0].ly) {
						if (rects[1].lx == rects[0].rx) {
							System.out.println("POINT");
						} else {
							System.out.println("LINE");
						}
					} else {
						if (rects[0].rx == rects[1].lx) {
							System.out.println("LINE");
						} else {
							System.out.println("FACE");
						}
					}
				} else {
					System.out.println("NULL");
				}
			}
		} else {
			System.out.println("NULL");
		}
	}
}
