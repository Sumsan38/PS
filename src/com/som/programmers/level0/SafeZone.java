public class SafeZone {
    // [안전 지대] https://school.programmers.co.kr/learn/courses/30/lessons/120866

    public int solution(int[][] board) {
        // n * n 행렬
        int n = board.length;
        int answer = n * n;
        boolean[][] confirmedLandmine = new boolean[n][n];

        for (int h = 0; h < n; h++) {
            for (int r = 0; r < board[0].length; r++) {
                if(board[r][h] == 1) {
                    int dangerArea = getDangerArea(r, h, board, confirmedLandmine);
                    answer -= dangerArea;
                }
            }
        }

        return answer;
    }

    // 좌상, 좌, 좌하, 상, 하, 우상, 우, 우하
    static int[][] fourDirections =
            new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    private static int getDangerArea(int row, int height, int[][] board, boolean[][] confirmedLandmine) {
        int dangerArea = 0;
        if(! confirmedLandmine[row][height]) {
            confirmedLandmine[row][height] = true;
            dangerArea = 1;
        }

        for (int[] fourDirection : fourDirections) {
            int plusRow = row + fourDirection[0];
            int plusHeight = height + fourDirection[1];

            if(plusRow < 0 || plusRow >= board.length || plusHeight < 0 || plusHeight >= board.length) {
                continue;
            }
            if(confirmedLandmine[plusRow][plusHeight]) {
                continue;
            }

            confirmedLandmine[plusRow][plusHeight] = true;
            dangerArea ++;
        }

        return dangerArea;
    }
}
