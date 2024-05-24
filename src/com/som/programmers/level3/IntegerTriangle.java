public class IntegerTriangle {
    // [정수 삼각형] https://school.programmers.co.kr/learn/courses/30/lessons/43105

    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] addTriangle = new int[triangle.length][triangle[triangle.length - 1].length];
        addTriangle[0][0] = triangle[0][0];     // 1행은 그냥 업데이트

        // height = 1: 두 번째 행부터 검사
        // numberOfRows: 해당 행에 존재하는 요소 개수
        for (int height = 1, numberOfRows = 2; height < triangle.length; height++, numberOfRows++) {
            int[] aboveAddTriangleLine = addTriangle[height - 1];
            for (int num = 0; num < numberOfRows; num++) {
                int left = 0;
                if(num != 0) {
                    left = aboveAddTriangleLine[num - 1];
                }
                int right = aboveAddTriangleLine[num];

                int largerValue = Integer.max(left, right);
                addTriangle[height][num] = largerValue + triangle[height][num];

                // if last line, update answer
                if(height == triangle.length - 1) {
                    answer = Integer.max(answer, addTriangle[height][num]);
                }
            }
        }
        return answer;
    }
}
