#include<fstream>
#include<iostream>
#include<cstdio>
#include"Tourism.h"
using namespace std;

void Tourism::CreateGraph() {

	ifstream f_Vex("f:\\Vex.txt");
	ifstream f_Edge("f:\\Edge.txt");

	if (!f_Vex.is_open() || !f_Edge.is_open()) {
		cout << "文件打开失败" << endl;
	}

	//设置顶点总数
	int n;
	f_Vex >> n;

	//添加顶点
	while (!f_Vex.eof()) {
		int num;
		string name, desc;
		f_Vex >> num >> name >> desc;

		Vex v(num, name, desc);
		this->g.insertVex(v);
	}

	//添加边
	while (!f_Edge.eof()) {
		int vex1, vex2, weight;
		f_Edge >> vex1 >> vex2 >> weight;

		Edge e(vex1, vex2, weight);
		this->g.insertEdge(e);
	}

	f_Vex.close();
	f_Edge.close();

	//输出添加进入的信息
	output_Vex();
	output_edge();
}

void Tourism::GetSpotInfo() {
	//输出编号信息
	output_Vex();


	cout << "请输入想要查询的景点编号：";
	int n;
	cin >> n;

	Vex v = this->g.GetVex(n);
	cout << v.GetName() << endl;
	cout << v.GetDesc() << endl;

	cout << "----- 周边景区 ------" << endl;
	int* edge = g.FindEdge(n);
	for (int i = 0; i < g.GetVexnum(); i++) {
		int weight = edge[i];
		if (weight != 0) {
			cout << g.GetVex(i).GetName() << "==>" << v.GetName() << " " << weight << "m" << endl;
		}
	}
	cout << "---------------------" << endl;
}

//从某一景区开始的路径
void Tourism::TravelPath() {
	output_Vex();
	cout << "请输入起始结点：";
	int n;
	cin >> n;

	list<Path> pathlist;
	g.DFSTraverse(n, pathlist);

	//输出路径
	cout << "----可游览的路径为----" << endl;
	list<Path>::iterator iter;
	for (iter = pathlist.begin(); iter != pathlist.end(); iter++) {
		output_travel_path(n, iter);
	}

}
//得到两个旅游景点之间的最短路径和距离
void Tourism::FindBestPath() {
	output_Vex();
	int startpoint, endpoint;
	cout << "请输入起点的编号：";
	cin >> startpoint;
	cout << "请输入终点的编号：";
	cin >> endpoint;

	Path Bestpath;
	int MinDis;
    //得到最小距离
	g.getBestPath(startpoint, endpoint, Bestpath, MinDis);
	cout << "---------------" << endl;
	printf("从%s到%s的最短路径为：", g.GetVex(startpoint).GetName().c_str(), g.GetVex(endpoint).GetName().c_str());
	cout << g.GetVex(Bestpath.vexs[0]).GetName();
	for (int i = 1; i < Bestpath.size; i++) {
		cout << "->" << g.GetVex(Bestpath.vexs[i]).GetName();
	}
	cout << endl << "最少需要走：" << MinDis << "米" << endl;
	cout << "---------------" << endl;
}
//电路规划
void Tourism::Circuit_planning() {
	int MinPlanning;
	Edge* CircuitEdge[7];
	g.Prim(CircuitEdge, MinPlanning);


	//输出信息
	cout << "-------铺设电路规划-------" << endl;
	cout << "在以下两个景点之间铺设电路：" << endl;
	for (int i = 0; i < g.GetVexnum() - 1; i++) {
		cout << g.GetVex(CircuitEdge[i]->GetVex1()).GetName() << " - " << g.GetVex(CircuitEdge[i]->GetVex2()).GetName() << "\t" << CircuitEdge[i]->GetWeight() << "m" << endl;
	}
	cout << "铺设电路总规划长度：" << MinPlanning << endl;

}
//旅游路线
void Tourism::output_travel_path(int n, list<Path>::iterator iter) {
	cout << this->g.GetVex(n).GetName();
	Path path = (*iter);
	for (int i = 0; i < this->g.GetVexnum() - 1; i++) {
		cout << "->" << this->g.GetVex(path.vexs[i]).GetName();
	}
	cout << endl;
}

void Tourism::output_Vex() {
	cout << "----- 顶点 -----" << endl;
	for (int i = 0; i < g.GetVexnum(); i++) {
		cout << i << "-" << g.GetVex(i).GetName() << endl;
	}
	cout << "----------------" << endl;
}


void Tourism::output_edge() {
	cout << "----- 边 ------" << endl;
	for (int i = 0; i < g.GetVexnum(); i++) {
		int* edge = g.FindEdge(i);
		for (int j = 0; j < g.GetVexnum(); j++) {
			if (edge[j] != 0) {
				printf("<v%d,v%d> %d\n", i, j, edge[j]);
			}
		}
	}
}