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
	cout << "===== ������Ϣ����ϵͳ =====" << endl;
	cout << "1. ������������ͼ" << endl;
	cout << "2. ��ѯ������Ϣ" << endl;
	cout << "3. ���ξ��㵼��" << endl;
	cout << "4. �������·��" << endl;
	cout << "5. �����·�滮" << endl;
	cout << "6.�˳�" << endl;
	cout << "�����������ţ�0~5����";
}