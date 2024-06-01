import java.util.*;

public class PathFindingGame {

    public void test(){
        // [[5,3],[11,5],[13,3],[3,5],[6,1],[1,3],[8,6],[7,2],[2,2]]	[[7,4,6,9,1,8,5,2,3],[9,6,5,8,1,4,3,2,7]]
        int[][] nodeinfo = new int[][]{{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
        int[][] solution = solution(nodeinfo);

        nodeinfo = new int[][]{{0, 0}};
        int[][] solution1 = solution(nodeinfo);
        System.out.println("test");
    }

    public int[][] solution(int[][] nodeinfo) {
        // 한개의 노드만 있을 경우 예외처리
        if(nodeinfo[0].length == 1) {
            return new int[][]{{1}, {1}};
        }

        Map<Integer, List<Node>> levelMap = new HashMap<>();
        List<Integer> levelList = new ArrayList<>();
        Node root = null;
        for (int num = 0; num < nodeinfo.length; num++) {
            int[] node = nodeinfo[num];

            int x = node[0];
            int y = node[1];
            if(! levelMap.containsKey(y)) {
                levelMap.put(y, new ArrayList<>());
            }
            Node newNode = new Node(num + 1, x, y);
            levelMap.get(y).add(newNode);
            if(! levelList.contains(y)){
                levelList.add(y);
            }

            if(root == null) root = newNode;
            else if(y > root.y) root = newNode;
        }

        // y desc
        levelList.sort(Comparator.reverseOrder());
        for (int i = 1; i < levelList.size(); i++) {
            Integer previousLevel = levelList.get(i - 1);
            Integer level = levelList.get(i);
            List<Node> rootNodes = levelMap.get(previousLevel);
            List<Node> leafNodes = levelMap.get(level);

            for (Node rootNode : rootNodes) {
                for (Node leafNode : leafNodes) {
                    rootNode.addLeaf(leafNode);
                }
            }
        }

        int[][] answer = new int[2][nodeinfo.length];

        List<Integer> preorderExplore = new ArrayList<>();
        preorderTraversal(root, preorderExplore);
        answer[0] = preorderExplore.stream().mapToInt(i -> i).toArray();

        List<Integer> postorderExplore = new ArrayList<>();
        postorderTraversal(root, postorderExplore);
        answer[1] = postorderExplore.stream().mapToInt(i -> i).toArray();

        return answer;
    }

    // 전위 순회 - 루트 노드를 먼저 탐색하고, 자식 노드(왼 -> 오)를 탐색하는 방식이다.
    private void preorderTraversal(Node root, List<Integer> explore){
        explore.add(root.num);

        if(root.left != null) {
            preorderTraversal(root.left, explore);
        }
        if(root.right != null) {
            preorderTraversal(root.right, explore);
        }
    }

    // 후위 순회 - 왼쪽 자식 노드를 탐색하고, 오른쪽 자식 노드를 탐색하고, 루트 노드를 탐색하는 방식이다
    private void postorderTraversal(Node root, List<Integer> explore) {
        if(root.left != null) {
            postorderTraversal(root.left, explore);
        }
        if(root.right != null){
            postorderTraversal(root.right, explore);
        }

        explore.add(root.num);
    }

    int X_LENGTH = 100000;
    class Node {
        int num, x, y;
        int xStart, xEnd; // Range where child nodes can exist
        Node root, left, right;
        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.xStart = 0;
            this.xEnd = X_LENGTH;
        }
        private void addLeaf(Node addNode){
            if(! isInAddNode(addNode)) {
                return;
            }

            addNode.root = this;
            if(this.x > addNode.x) {
                addNode.xStart = this.xStart;
                addNode.xEnd = this.x - 1;
                this.left = addNode;
            } else {
                addNode.xStart = this.x + 1;
                addNode.xEnd = this.xEnd;
                this.right = addNode;
            }
        }

        private boolean isInAddNode(Node addNode) {
            int addNodeX = addNode.x;
            return (addNodeX >= this.xStart && addNodeX <= this.xEnd);
        }
    }
}
