import java.util.*;

class Solution {
    // https://school.programmers.co.kr/learn/courses/30/lessons/87377?language=java
    // 1. 교점을 구한다.
    // 2. 배열의 크기를 잡는다.
    // 3. 그래프(x, y) 좌표를 배열 좌표로 치환한다.
    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public String[] solution(int[][] line) {
        List<Point> points = new ArrayList<>();
        List<Point> coordPoints = new ArrayList<>();
        // 1. 교점을 구한다
        for ( int i = 0; i < line.length; ++i ) {
            for ( int j = i + 1; j < line.length; ++j ) {
                // AD - BC 
                long adbc = ((long)line[i][0] * line[j][1]) - (line[i][1] * line[j][0]);
                
                if (adbc == 0) {
                    continue;
                }
                // BF - ED
                long bfed = ((long)line[i][1] * line[j][2]) - (line[i][2] * line[j][1]);
                
                // EC - AF
                long ecaf = ((long)line[i][2] * line[j][0]) - (line[i][0] * line[j][2]);
                
                // 정수 좌표 판별: adbc로 나누어 떨어지는지 확인
                if (bfed % adbc == 0 && ecaf % adbc == 0) {
                    int X = (int)(bfed / adbc);
                    int Y = (int)(ecaf / adbc);
                    points.add(new Point(X, Y));
                }
                
//                 double x = (double) bfed / adbc;
//                 double y = (double) ecaf / adbc;
                
//                 // 정수인 것만 취급
//                 int x2 = (int) x;
//                 int y2 = (int) y; 
//                 if ( ((x - x2) == 0) && ((y - y2) == 0) ) {
//                     points.add(new Point(x2, y2));
//                 }
            } 
        }
        
        // 2. 배열의 크기를 잡는다.
        // int maxX = -100001, minX = 100001;
        // int maxY = -100001, minY = 100001;
        int maxX = Integer.MIN_VALUE, minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE, minY = Integer.MAX_VALUE;
        for (Point p : points) {
            // x -> 열
            maxX = Math.max(maxX, p.x);
            minX = Math.min(minX, p.x);
            // y -> 행
            maxY = Math.max(maxY, p.y);
            minY = Math.min(minY, p.y);
        }
        
        int col = maxX - minX;
        int row = maxY - minY;
        for ( Point p : points ) {
            // 3. 그래프(x, y) 좌표를 배열 좌표로 치환한다.
            // (maxY - y, x - minX)
            coordPoints.add(new Point((p.x - minX), (maxY - p.y)));
        }
        
        String[][] matrix = new String[row+1][col+1];
        for (String[] m : matrix) {
            Arrays.fill(m, ".");
        }
        
        for (Point p : coordPoints) { 
            matrix[p.y][p.x] = "*";
        }
        
         
        String[] answer = new String[row + 1];
        for ( int i = 0; i < matrix.length; ++i ) {
            String str = "";
            for ( int j = 0; j < matrix[i].length; ++j ) {
                str += matrix[i][j];
            }
            answer[i] = str;
        }
        
        return answer;
    }
    
}