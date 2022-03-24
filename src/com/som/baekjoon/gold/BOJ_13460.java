package com.baekjoon;

import java.io.*;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/13460
 * [삼성 SW 역량 테스트 기출 문제]
 * 구슬 탈출 2
 * */
public class BOJ_13460 {
    static int n,m, INF = 987654321;
    static char[][] map;
    static Point startRed,startBlue;
    public static void main(String[] args) throws IOException {
//		InputReader reader = new InputReader("testcase.txt");
        InputReader reader = new InputReader();
        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for (int row = 0; row < n; row++) {
            String rowLine = reader.readLine();
            for (int col = 0; col < m; col++) {
                map[row][col] = rowLine.charAt(col);
                if(map[row][col] == 'R') startRed = new Point(row, col);
                if(map[row][col] == 'B') startBlue = new Point(row, col);
            }
        }
        int ret = findPath(startRed, startBlue, 0);

        if(ret == INF) {
            System.out.println(-1); return;
        }
        System.out.println(ret);
    }
    static int minCnt = INF;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    public static int findPath(Point red, Point blue, int count){
        if(minCnt <= count || count > 10 || map[blue.row][blue.col] == 'O') return INF;
        if(map[red.row][red.col] == 'O') return count;
        // 0 : 상, 1 : 하, 2: 우, 3: 좌
        for (int i = 0; i < 4; i++) {
            //공 각자 이동, 상대공 위치 신경 쓰지 않는다.
            Point nextRed = go(red,dr[i],dc[i], true);
            Point nextBlue = go(blue,dr[i],dc[i], false);

            //겹치는 경우 공 위치 수정
            if(nextBlue.isSame(nextRed) && map[nextBlue.row][nextBlue.col] != 'O') {
                updatePosition(nextRed, nextBlue, red, blue, dr[i], dc[i]);
            }

            //재귀
            minCnt = Math.min(findPath(nextRed, nextBlue,count+1), minCnt);
            //위치 복귀
            recoverPosition(red, nextRed, blue, nextBlue);
        }
        return minCnt;
    }
    /*
        공들이 벽에 막혀 겹친 경우
     */
    private static void updatePosition(Point nextRed, Point nextBlue, Point prevRed, Point prevBlue, int dr, int dc) {
        int redDistance = nextRed.distance(prevRed);
        int blueDistance = nextBlue.distance(prevBlue);
        //파란 공이 앞에 있었던 경우
        if(redDistance > blueDistance) {
            map[nextBlue.row][nextBlue.col] = 'B';
            map[nextRed.row-dr][nextRed.col-dc] = 'R';
            nextRed.row -= dr; nextRed.col -= dc;
        }
        //빨간 공이 앞에 있었던 경우
        if(blueDistance > redDistance) {
            map[nextRed.row][nextRed.col] = 'R';
            map[nextBlue.row-dr][nextBlue.col-dc] = 'B';
            nextBlue.row -= dr; nextBlue.col -= dc;
        }
    }
    /*
    백트래킹 - 위치 복구
    다음 위치와 이전 위치가 같은 경우엔 복구x
     */
    private static void recoverPosition(Point red, Point nextRed, Point blue, Point nextBlue) {
        if(!red.isSame(nextRed)){
            if(map[nextRed.row][nextRed.col] == 'R') map[nextRed.row][nextRed.col] = '.';
            if(map[red.row][red.col] == '.') map[red.row][red.col] = 'R';
        }
        if(!blue.isSame(nextBlue)){
            if(map[nextBlue.row][nextBlue.col] == 'B') map[nextBlue.row][nextBlue.col] = '.';
            if(map[blue.row][blue.col] == '.') map[blue.row][blue.col] = 'B';
        }
    }


    /*
    다른 공은 신경쓰지 않고 이동시킨다.
    벽이 있는 경우 이전 위치 반환, 구멍이 있는 경우 구멍의 위치 반환
     */
    private static Point go(Point here, int dr, int dc, boolean isRed) {
        int row = here.row; int col = here.col;

        while(true) {
            int nextRow = row+dr; int nextCol = col+dc;
            //빈 공간일 경우 이동한다
            if(map[nextRow][nextCol] == '.' || map[nextRow][nextCol] == 'R'
                    || map[nextRow][nextCol] == 'B') {
                row = nextRow; col = nextCol;
                continue;
            }
			/*
			탈출구를 만나면 이전 위치를 지우고 위치를 반환한다.
			 */
            if(map[nextRow][nextCol] == 'O') {
                map[here.row][here.col] = '.';
                return new Point(nextRow, nextCol);
            }
            //벽을 만난 경우
            map[here.row][here.col] = '.';
            map[row][col] = isRed ? 'R' : 'B';
            return new Point(row, col);
        }
    }

}
class Point {
    int row, col;
    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isSame(Point other) {
        if(other.row == this.row && other.col == this.col) return true;
        return false;
    }

    public int distance(Point other) {
        return Math.abs(this.row - other.row) + Math.abs(this.col - other.col);
    }
    @Override
    public String toString() {
        return "{row=" + row +
                ", col=" + col +
                '}';
    }
}
class InputReader {
    private BufferedReader br;

    public InputReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public InputReader(String filepath) {
        try {
            br = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readLine() throws IOException {
        return br.readLine();
    }
    public int readInt() throws IOException {
        return Integer.parseInt(readLine());
    }
    public Long readLong() throws IOException {
        return Long.parseLong(readLine());
    }
}