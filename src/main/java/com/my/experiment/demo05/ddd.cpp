#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Action {
	int b;
	int e;
	bool operator<(const Action &s) const {
		return e <= s.e;
	}
};
int greedly(vector<Action> &a) {
	sort(a.begin(), a.end());
	int n = a.size();
	int ans = 0;
	int preend = 0;

	for (int i = 0; i < n; i++) {
		if (a[i].b >= preend) {
			ans++;
			preend = a[i].e;
		}
	}
	cout << "最多可以安排的会议数为：" << ans << endl;
	return ans;
}

int main() {
	vector<Action> a = {{1, 4}, {3, 5}, {0, 6}, {5, 7}, {3, 8}, {5, 9}, {6, 10}, {8, 11}, {8, 12}, {2, 13}, {12, 15}};

	greedly(a);
	return 0;
}
