import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/68645

// 1. 초기값을 0으로 셋팅한다.
// 2. 아래(x, y) -> (x+1, y)
// 3. 옆(x, y) -> (x, y+1) 
// 4. 대각선(x, y) -> (x-1, y-1) 
// ** 인덱스 및 다음항목 0체크
// 2~4 반복 더이상 불가할 때까지

class Solution2 {
    int N = -1;
    int[][] matrix = null;
    Node node = null;
    
    public int[] solution(int n) {
        // n = 1인 경우는 바로 종료 시켜버린다.
        if ( n == 1 )  return new int[]{1};
        // 초기화
        init(n);
        // 로직        
        while ( true ) {
            if ( !moveDown() ) break;
            if ( !moveRight() ) break;
            if ( !moveDiag() ) break;
        }
        // 결과
        return getResult();
    }

    class Node {
        int x;
        int y;
        int value;
        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    
    // 초기화 메서드
    public void init(int n) {
        matrix = new int[n][n];
        // 1. 초기값을 0으로 셋팅한다.
        for ( int[] m : matrix ) {
            Arrays.fill(m, 0);
        }
        // 노드 초기화
        node = new Node(0, 0, 1);
        // global로..
        N = n;
    }
    
    // 아래(x, y) -> (x+1, y)
    public boolean moveDown() {
        int x = node.x, y = node.y, value = node.value;
        
        while ( x < N && matrix[x][y] == 0 ) {
            matrix[x][y] = value++;
            ++x;
        }
        
        --x;
        // 다음 요소 유효성 검사
        if ( matrix[x][y+1] != 0 ) {
            return false;
        }
        
        // 다음 요소를 가르킴
        node.x = x;
        node.y = y+1;
        node.value = value;
        
        return true;
    }
    
    // 옆(x, y) -> (x, y+1)
    public boolean moveRight() {
        int x = node.x, y = node.y, value = node.value;
        
        while ( y < N && matrix[x][y] == 0 ) {
            matrix[x][y] = value++;
            ++y;
        }
        
        --y;
        
        // 다음 요소 유효성 검사
        if ( matrix[x-1][y-1] != 0 ) { 
            return false;
        }
        
        // 다음 요소를 가르킴
        node.x = x-1;
        node.y = y-1;
        node.value = value;
        return true;
    }
    
    // 대각선(x, y) -> (x-1, y-1) 
    public boolean moveDiag() {
        int x = node.x, y = node.y, value = node.value;
        
        while ( x > 0 && y > 0 && matrix[x][y] == 0 ) { 
            matrix[x][y] = value++;
            --x;
            --y;
        }
        
        ++x;
        ++y;
        
        // 다음 요소 유효성 검사
        if ( matrix[x+1][y] != 0 ) {
            return false;
        }
        
        // 다음 요소를 가르킴
        node.x = x+1;
        node.y = y;
        node.value = value;
        
        return true;
    }
    
    // 결과 구하기
    public int[] getResult() {
        List<Integer> list = new ArrayList<>();
        for ( int[] ma : matrix ) {
            for ( int m : ma ) {
                if (m != 0) list.add(m);
            }
        }
         
        int[] answer = new int[list.size()];
        for ( int i = 0; i < answer.length; ++i ) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public void print() {
        for ( int[] ma : matrix ) {
            for ( int m : ma ) {
                System.out.print(m + " ");
            }
            System.out.println();
        }
    }
}