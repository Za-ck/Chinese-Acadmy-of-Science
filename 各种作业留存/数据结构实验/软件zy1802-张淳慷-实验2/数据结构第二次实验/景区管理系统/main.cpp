#include<iostream>
#include"Tourism.h"
using namespace std;
int main(void)
{
	Tourism t;
	while (true) {
		output_menu();
		int n;
		cin >> n;
		switch (n)
		{
		case 1: {
			t.CreateGraph();
			break;
		}
		case 2: {
			t.GetSpotInfo();
			break;
		}
		case 3: {
			t.TravelPath();
			break;
		}
		case 4: {
			t.FindBestPath();
			break;
		}
		case 5: {
			t.Circuit_planning();
			break;
		}
		case 6: {
			return 0;
		}
		default:
			break;
		}
	}
	return 0;
}

void output_menu() {
	cout << "===== 景区信息管理系统 =====" << endl;
	cout << "1. 创建景区景点图" << endl;
	cout << "2. 查询景点信息" << endl;
	cout << "3. 旅游景点导航" << endl;
	cout << "4. 搜索最短路径" << endl;
	cout << "5. 铺设电路规划" << endl;
	cout << "6.退出" << endl;
	cout << "请输入操作编号（0~5）：";
}