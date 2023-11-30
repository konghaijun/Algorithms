package com.my.experiment.demo05;

  import java.util.*;

class HTreeNode {
    char data;
    int weight;
    int parent;
    int lchild;
    int rchild;
}

class QNode implements Comparable<QNode> {
    int no;
    char data;
    int weight;

    public int compareTo(QNode s) {
        return weight - s.weight; // 用于创建小根堆
    }
}

public class HuffmanCoding {
    private static final int MAX = 101;
    private static int n; // 叶子结点个数
    private static String str; // 英文句子字符串
    private static HTreeNode[] ht = new HTreeNode[MAX]; // 哈夫曼树
    private static Map<Character, String> htcode = new HashMap<>(); // 哈夫曼编码

    private static void Init() {
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            mp.put(c, mp.getOrDefault(c, 0) + 1); // 累计 str 中各个字符出现的次数
        }
        n = mp.size();
        int i = 0;
        for (Map.Entry<Character, Integer> entry : mp.entrySet()) {
            ht[i] = new HTreeNode();
            ht[i].data = entry.getKey(); // 设置叶子结点的 data
            ht[i].weight = entry.getValue(); // 设置叶子结点的 weight
            i++;
        }
        for (int j = 0; j < 2 * n - 1; j++) {
            ht[j] = new HTreeNode(); // 初始化哈夫曼树的结点
            ht[j].parent = ht[j].lchild = ht[j].rchild = -1; // 设置所有结点的指针域
        }
    }

    private static void CreateHTree() {
        PriorityQueue<QNode> pqu = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            QNode e = new QNode();
            e.no = i;
            e.data = ht[i].data;
            e.weight = ht[i].weight;
            pqu.add(e);
        }
        for (int j = n; j < 2 * n - 1; j++) {
            QNode e1 = pqu.poll(); // 出队权值最小的结点 e1
            QNode e2 = pqu.poll(); // 出队权值次小的结点 e2
            ht[j] = new HTreeNode();
            ht[j].weight = e1.weight + e2.weight; // 构造哈夫曼树的非叶子结点 j
            ht[j].lchild = e1.no;
            ht[j].rchild = e2.no;
            ht[e1.no].parent = j; // 修改 e1.no 的双亲为结点 j
            ht[e2.no].parent = j; // 修改 e2.no 的双亲为结点 j
            QNode e = new QNode(); // 构造队列结点 e
            e.no = j;
            e.weight = e1.weight + e2.weight;
            pqu.add(e);
        }
    }

    private static void CreateHCode() {
        for (int i = 0; i < n; i++) {
            StringBuilder code = new StringBuilder();
            int curno = i;
            int f = ht[curno].parent;
            while (f != -1) {
                if (ht[f].lchild == curno) { // curno 为双亲 f 的左孩子
                    code.insert(0, '0'); // 那么curno对应的字符串的编码前面加一个0
                } else {
                    code.insert(0, '1'); // 右孩子的话，编码前面加1
                }
                curno = f; // 更新curno
                f = ht[curno].parent; // 更新f
            }
            htcode.put(ht[i].data, code.toString());
        }
    }

    private static void DispHCode() {
        for (Map.Entry<Character, String> entry : htcode.entrySet()) {
            System.out.println("\t" + entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void EnCode(String str, StringBuilder enstr) {
        for (int i = 0; i < str.length(); i++) {
            enstr.append(htcode.get(str.charAt(i)));
        }
    }

    private static void DeCode(String enstr, StringBuilder destr) {
        int r = 2 * n - 2;
        int p;
        int i = 0;
        while (i < enstr.length()) {
            p = r;
            while (true) {
                if (enstr.charAt(i) == '0') {
                    p = ht[p].lchild;
                } else {
                    p = ht[p].rchild;
                }
                if (ht[p].lchild == -1 && ht[p].rchild == -1) { // p 为叶子结点
                    break;
                }
                i++;
            }
            destr.append(ht[p].data); // 在解码字符串中添加 ht[p].data
            i++;
        }
    }

    public static void main(String[] args) {
        str = "Long live the Chinese Communist Party";
        Init();
        CreateHTree();
        CreateHCode();
        System.out.println("哈夫曼编码:");
        DispHCode();
        StringBuilder enstr = new StringBuilder();
        EnCode(str, enstr);
        System.out.println("编码结果:");
        System.out.println(enstr);
        StringBuilder destr = new StringBuilder();
        DeCode(enstr.toString(), destr);
        System.out.println("解码结果:");
        System.out.println(destr);
    }
}
