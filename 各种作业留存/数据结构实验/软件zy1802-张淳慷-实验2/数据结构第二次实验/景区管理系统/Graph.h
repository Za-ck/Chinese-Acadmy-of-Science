#ifndef GRAPH_H
#define GRAPH_H
#include<string>
#include<list>
using namespace std;
void output_menu();//给出菜单
class Path {
public:
	int vexs[20];	//保存一条路径
	int size;		//路径长度
};

class Vex {
private:
	int num;		//景点的编号
	string name;	//景点的名字
	string desc;	//景点的介绍
public:
	Vex(int num, string name, string desc) {
		this->num = num;
		this->name = name;
		this->desc = desc;
	}
	Vex() {}
	string GetName() {
		return name;
	}
	string GetDesc() {
		return desc;
	}
};
class Edge
{
private:
	int vex1, vex2;
	int weight;
public:
	int GetWeight() {
		return weight;
	}
	Edge() {}
	int GetVex1() {
		return vex1;
	}
	int GetVex2() {
		return vex2;
	}
	Edge(int vex1, int vex2, int weight) {
		this->vex1 = vex1;
		this->vex2 = vex2;
		this->weight = weight;
	}
};

class Graph {
private:
	int Gedge[20][20];						//图的边
	Vex Gvex[20];							//图的顶点
	int size;								//图的顶点数目
public:
	void init();																//初始化图结构
	bool insertVex(Vex v);														//将顶点添加到图中
	bool insertEdge(Edge e);													//将边添加到图中
	Vex GetVex(int index);														//得到顶点信息
	int* FindEdge(int nVex);													//查找与特定顶点相连的边
	int GetVexnum();															//获取当前顶点数
	void DFS(int aVex, bool aVisited[], int& adeep, Path& path, list<Path>& pathlist);		//深度优先搜索
	void DFSTraverse(int nVex, list<Path>& pathlist);							//使用深度优先搜索遍历结果
	//得到两点之间的权重最小之和，以及最优路径
	void getBestPath(int startpoint, int endpoint, Path& Bestpath, int& Mweight);
	//合并路径
	int* pathMerge(int* temp, int i);
	void Prim(Edge* Circuit[], int& Minway);

	void SetSize(int n) {
		this->size = n;
	}
	Graph() {
		this->init();
	}
};


#endif //DEBUG
