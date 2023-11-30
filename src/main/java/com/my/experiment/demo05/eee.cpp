# include <iostream>
# include <queue>
# include <vector>
# include <string>
# include <map>
using namespace std;
//t MAX =101;
# define MAX 101
int n; //叶子结点个数
string str; //英文句子字符串

struct HTreeNode { //哈夫曼树结点类型
	char data; // 字符
	int weight; // 权值
	int parent; //双亲的位置
	int lchild; //左孩子的位置
	int rchild; //右孩子的位置
};

HTreeNode ht[MAX]; //哈夫曼树
map < char, string > htcode; //哈夫曼编码

struct QNode { //优先队列结点类型
	int no; //对应哈夫曼树中的位置
	char data; //字符
	int weight; // 权值
	bool operator < (const QNode &s) const {
		return s.weight < weight ;   //用于创建小根堆
	}
};


void Init() {   //初始化哈夫曼树
	int i;
	map < char, int > mp;

	for (i = 0; i < str.length(); i++) //累计 str 中各个字符出现的次数
		mp[str[i]]++;

	n = mp.size();
	map < char, int >:: iterator it;
	i = 0;

	for ( it = mp.begin(); it != mp.end(); ++it) { // 设置叶子结点的 data 和 weight
		ht[i].data = it->first;
		ht [i].weight = it->second;
		i++ ;
	}

	for (int j = 0; j < 2 * n - 1; j++) // 设置所有结点的指针域
		ht[j].lchild = ht[j].rchild = ht[j].parent = -1;
}


void CreateHTree() { //构造哈夫曼树
	QNode e, e1, e2;
	priority_queue < QNode > pqu;

	for (int i = 0; i < n; i++) { //将 n 个结点进队 qu
		e.no = i;
		e.data = ht[i].data;
		e.weight = ht[i].weight;
		pqu.push(e);
	}

	for (int j = n; j < 2 * n - 1; j++) { // 构造哈夫曼树的 n-1 个非叶子结点
		e1 = pqu.top();
		pqu.pop(); //出队权值最小的结点 el
		e2 = pqu.top();
		pqu.pop(); //出队权值次小的结点 e2
		ht[j].weight = e1.weight + e2.weight; //构造哈夫曼树的非叶子结点 j
		ht[j].lchild = e1.no;
		ht[j].rchild = e2.no;
		ht[e1.no].parent = j;   // 修改 el.no 的双亲为结点 j
		ht[e2.no].parent = j ; // 修改 e2.no 的双亲为结点 j
		e.no = j;  // 构造队列结点 e
		e.weight = e1.weight + e2.weight ;
		pqu.push(e);
	}
}

void CreateHCode() { // 构造哈夫曼编码
	string code;
//ut << "test here1"<< endl;
	code.reserve (MAX);

//ut << "test here2"<< endl ;
	for (int i = 0; i < n; i++ ) // 构造叶子结点 i 的哈夫曼编码，不断溯源，找到当前节点在
		//哈夫曼树中的根，然后看自己是根的左孩子还是右孩子，然后编码//0或者1 ; 接着继续向上溯源
	{
		code = "";
		int curno = i;
		int f = ht[curno].parent;

//ut << "test here3"<< endl ;
		while (f != -1) {
			if (ht[f].lchild == curno) {
				// curno 为双亲 f 的左孩子

				code = '0' + code;           // 那么curno对应的字符串的编码前面加一个0
			} else {
				code = '1' + code;
				//右孩子的话，应该怎么样编码呢？
			}

			curno = f;
			f = ht[curno].parent;
			//向上溯源，更新curno, 更新f
		}

		htcode [ht[i].data] = code;
	}

//ut << "test here" << endl;
}


void DispHCode() {   //输出各个字符的哈夫曼编码的结果
	map < char, string > :: iterator it ;

	for (it = htcode.begin () ; it != htcode.end (); ++it )
		cout << it->first << ": " << it->second << endl;
}


//编码
void EnCode(string str, string &enstr) { //通过编码字符串 str 得到 enstr
	for (int i = 0; i < str.length(); i++)
		enstr = enstr + htcode[str[i]];
}



//解码
void DeCode(string enstr, string &destr) { //通过解码字符串 enstr 得到 destr
	int r = 2 * n - 2, p; // 哈夫曼树的根结点为 ht[2*n-2] 结点
	int i = 0;

	while ( i < enstr.length( )) {
		p = r;

		while (true) {

//通过编码字符串 str 得到 enstr
// 哈夫曼树的根结点为 ht[2*n-2] 结点

			if (enstr [i] == '0')
				p = ht[p].lchild;
			else
				p = ht[p].rchild;

			if ( ht[p].lchild == -1 && ht[p].rchild == -1) //p 为叶子结点
				break;

//找到对应的字符
			i++ ;
		}

		destr = destr + ht[p].data;//在解码字符串中添加 ht [p].data
		i++ ;
	}
}

int main() {
	str = "Long live the Chinese Communist Party" ;
	Init();

	CreateHTree( );

	CreateHCode();

	cout << "哈夫曼编码:" << endl;

	DispHCode( );
	string enstr = "" ;
	EnCode(str, enstr);
	cout << "编码结果: " << endl;

	cout << enstr << endl;
	string destr = "" ;
	DeCode(enstr, destr);
	cout << "解码结果: " << endl;
	cout << destr << endl;
	return 0 ;
}
