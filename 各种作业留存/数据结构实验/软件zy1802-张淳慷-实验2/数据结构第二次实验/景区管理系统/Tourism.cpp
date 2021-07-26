#include<fstream>
#include<iostream>
#include<cstdio>
#include"Tourism.h"
using namespace std;

void Tourism::CreateGraph() {

	ifstream f_Vex("f:\\Vex.txt");
	ifstream f_Edge("f:\\Edge.txt");

	if (!f_Vex.is_open() || !f_Edge.is_open()) {
		cout << "�ļ���ʧ��" << endl;
	}

	//���ö�������
	int n;
	f_Vex >> n;

	//��Ӷ���
	while (!f_Vex.eof()) {
		int num;
		string name, desc;
		f_Vex >> num >> name >> desc;

		Vex v(num, name, desc);
		this->g.insertVex(v);
	}

	//��ӱ�
	while (!f_Edge.eof()) {
		int vex1, vex2, weight;
		f_Edge >> vex1 >> vex2 >> weight;

		Edge e(vex1, vex2, weight);
		this->g.insertEdge(e);
	}

	f_Vex.close();
	f_Edge.close();

	//�����ӽ������Ϣ
	output_Vex();
	output_edge();
}

void Tourism::GetSpotInfo() {
	//��������Ϣ
	output_Vex();


	cout << "��������Ҫ��ѯ�ľ����ţ�";
	int n;
	cin >> n;

	Vex v = this->g.GetVex(n);
	cout << v.GetName() << endl;
	cout << v.GetDesc() << endl;

	cout << "----- �ܱ߾��� ------" << endl;
	int* edge = g.FindEdge(n);
	for (int i = 0; i < g.GetVexnum(); i++) {
		int weight = edge[i];
		if (weight != 0) {
			cout << g.GetVex(i).GetName() << "==>" << v.GetName() << " " << weight << "m" << endl;
		}
	}
	cout << "---------------------" << endl;
}

//��ĳһ������ʼ��·��
void Tourism::TravelPath() {
	output_Vex();
	cout << "��������ʼ��㣺";
	int n;
	cin >> n;

	list<Path> pathlist;
	g.DFSTraverse(n, pathlist);

	//���·��
	cout << "----��������·��Ϊ----" << endl;
	list<Path>::iterator iter;
	for (iter = pathlist.begin(); iter != pathlist.end(); iter++) {
		output_travel_path(n, iter);
	}

}
//�õ��������ξ���֮������·���;���
void Tourism::FindBestPath() {
	output_Vex();
	int startpoint, endpoint;
	cout << "���������ı�ţ�";
	cin >> startpoint;
	cout << "�������յ�ı�ţ�";
	cin >> endpoint;

	Path Bestpath;
	int MinDis;
    //�õ���С����
	g.getBestPath(startpoint, endpoint, Bestpath, MinDis);
	cout << "---------------" << endl;
	printf("��%s��%s�����·��Ϊ��", g.GetVex(startpoint).GetName().c_str(), g.GetVex(endpoint).GetName().c_str());
	cout << g.GetVex(Bestpath.vexs[0]).GetName();
	for (int i = 1; i < Bestpath.size; i++) {
		cout << "->" << g.GetVex(Bestpath.vexs[i]).GetName();
	}
	cout << endl << "������Ҫ�ߣ�" << MinDis << "��" << endl;
	cout << "---------------" << endl;
}
//��·�滮
void Tourism::Circuit_planning() {
	int MinPlanning;
	Edge* CircuitEdge[7];
	g.Prim(CircuitEdge, MinPlanning);


	//�����Ϣ
	cout << "-------�����·�滮-------" << endl;
	cout << "��������������֮�������·��" << endl;
	for (int i = 0; i < g.GetVexnum() - 1; i++) {
		cout << g.GetVex(CircuitEdge[i]->GetVex1()).GetName() << " - " << g.GetVex(CircuitEdge[i]->GetVex2()).GetName() << "\t" << CircuitEdge[i]->GetWeight() << "m" << endl;
	}
	cout << "�����·�ܹ滮���ȣ�" << MinPlanning << endl;

}
//����·��
void Tourism::output_travel_path(int n, list<Path>::iterator iter) {
	cout << this->g.GetVex(n).GetName();
	Path path = (*iter);
	for (int i = 0; i < this->g.GetVexnum() - 1; i++) {
		cout << "->" << this->g.GetVex(path.vexs[i]).GetName();
	}
	cout << endl;
}

void Tourism::output_Vex() {
	cout << "----- ���� -----" << endl;
	for (int i = 0; i < g.GetVexnum(); i++) {
		cout << i << "-" << g.GetVex(i).GetName() << endl;
	}
	cout << "----------------" << endl;
}


void Tourism::output_edge() {
	cout << "----- �� ------" << endl;
	for (int i = 0; i < g.GetVexnum(); i++) {
		int* edge = g.FindEdge(i);
		for (int j = 0; j < g.GetVexnum(); j++) {
			if (edge[j] != 0) {
				printf("<v%d,v%d> %d\n", i, j, edge[j]);
			}
		}
	}
}