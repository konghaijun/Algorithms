package com.my.experiment.demo05;
import java.util.*;

public class Solution {
    final static int MAX = 101;
    int n; // 叶子结点个数
    String str; // 英文句子字符串

    class HTreeNode { // 哈夫曼树结点类型
        char data; // 字符
        int weight; // 权值
        int parent; // 双亲的位置
        int lchild; // 左孩子的位置
        int rchild; // 右孩子的位置
    }

    HTreeNode[] ht = new HTreeNode[MAX]; // 哈夫曼树
    Map<Character, String> htcode = new HashMap<>(); // 哈夫曼编码

    class QNode { // 优先队列结点类型
        int no; // 对应哈夫曼树中的位置
        char data; // 字符
        int weight; // 权值
        QNode(int a, char b, int c) {
            this.no = a;
            this.data = b;
            this.weight = c;
        }
    }

    void Init() { // 初始化哈夫曼树
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < str.length(); i++) { // 累计 str 中各个字符出现的次数
            mp.put(str.charAt(i), mp.getOrDefault(str.charAt(i), 0) + 1);
        }
        n = mp.size();
        Iterator<Map.Entry<Character, Integer>> it = mp.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) { // 设置叶子结点的 data 和 weight
            Map.Entry<Character, Integer> entry = it.next();
            ht[i].data = entry.getKey();
            ht[i].weight = entry.getValue();
            i++;
        }

        for (int j = 0; j < 2 * n - 1; j++) // 设置所有结点的指针域
            ht[j].lchild = ht[j].rchild = ht[j].parent = -1;
    }

    void CreateHTree() { // 构造哈夫曼树
        PriorityQueue<QNode> pqu = new PriorityQueue<>(2 * n, Comparator.comparingInt(a -> a.weight));
        // 将 n 个结点进队 qu
        for (int i = 0; i < n; i++) {
            QNode e = new QNode(i, ht[i].data, ht[i].weight);
            pqu.offer(e);
        }
        // 构造哈夫曼树的 n-1 个非叶子结点
        for (int j = n; j < 2 * n - 1; j++) {
            QNode e1 = pqu.poll(); // 出队权值最小的结点 el
            QNode e2 = pqu.poll(); // 出队权值次小的结点 e2
            ht[j].weight = e1.weight + e2.weight; // 构造哈夫曼树的非叶子结点 j
            ht[j].lchild = e1.no;
            ht[j].rchild = e2.no;
            ht[e1.no].parent = j; // 修改 el.no 的双亲为结点 j
            ht[e2.no].parent = j; // 修改 e2.no 的双亲为结点 j
            QNode e = new QNode(j, ' ', e1.weight + e2.weight);
            pqu.offer(e);
        }
    }

    void CreateHCode() { // 构造哈夫曼编码
        String code;
        for (int i = 0; i < n; i++) { // 构造叶子结点 i 的哈夫曼编码，不断溯源，找到当前结点在
            // 哈夫曼树中的根，然后看自己是根的左孩子还是右孩子，然后编码0或者1;
            // 接着继续向上溯源
            code = "";
            int curno = i;
            int f = ht[curno].parent;
            while (f != -1) {
                if (ht[f].lchild == curno) // curno 为双亲 f 的左孩子
                    code = '0' + code; // 那么 curno 对应的字符串的编码前面加一个 0
                else
                    code = '1' + code; // 右孩子的话，应该怎么样编码呢？
                curno = f; // 向上溯源，更新 curno
                f = ht[curno].parent; // 更新 f
            }
            htcode.put(ht[i].data, code);
        }
    }

    void DispHCode() { // 输出各个字符的哈夫曼编码的结果
        for (Map.Entry<Character, String> entry : htcode.entrySet()) {
            System.out.println("\t" + entry.getKey() + ": " + entry.getValue());
        }
    }

    void EnCode(String str, StringBuilder enstr) { // 通过编码字符串 str 得到 enstr
        for (int i = 0; i < str.length(); i++)
            enstr.append(htcode.get(str.charAt(i)));
    }

    void DeCode(String enstr, StringBuilder destr) { // 通过解码字符串 enstr 得到 destr
        int r = 2 * n - 2, p; // 哈夫曼树的根结点为 ht[2*n-2] 结点
        int i = 0;
        while (i < enstr.length()) {
            p = r;
            while (true) {
                if (enstr.charAt(i) == '0')
                    p = ht[p].lchild;
                else
                    p = ht[p].rchild;
                if (ht[p].lchild == -1 && ht[p].rchild == -1) // p 为叶子结点
                    break;
                i++;
            }
            destr.append(ht[p].data); // 在解码字符串中添加 ht[p].data
            i++;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.str = "Long live the Chinese Communist Party";
        for (int i = 0; i < MAX; i++) {
            solution.ht[i] = solution.new HTreeNode();
        }
        solution.Init();
        solution.CreateHTree();
        solution.CreateHCode();
        System.out.println("哈夫曼编码:");
        solution.DispHCode();
        StringBuilder enstr = new StringBuilder();
        solution.EnCode(solution.str, enstr);
        System.out.println("编码结果: ");
        System.out.println(enstr.toString());
        StringBuilder destr = new StringBuilder();
        solution.DeCode(enstr.toString(), destr);
        System.out.println("解码结果: ");
        System.out.println(destr.toString());
    }
}