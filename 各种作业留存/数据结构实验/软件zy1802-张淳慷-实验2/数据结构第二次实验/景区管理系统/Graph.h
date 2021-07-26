#ifndef GRAPH_H
#define GRAPH_H
#include<string>
#include<list>
using namespace std;
void output_menu();//�����˵�
class Path {
public:
	int vexs[20];	//����һ��·��
	int size;		//·������
};

class Vex {
private:
	int num;		//����ı��
	string name;	//���������
	string desc;	//����Ľ���
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
	int Gedge[20][20];						//ͼ�ı�
	Vex Gvex[20];							//ͼ�Ķ���
	int size;								//ͼ�Ķ�����Ŀ
public:
	void init();																//��ʼ��ͼ�ṹ
	bool insertVex(Vex v);														//��������ӵ�ͼ��
	bool insertEdge(Edge e);													//������ӵ�ͼ��
	Vex GetVex(int index);														//�õ�������Ϣ
	int* FindEdge(int nVex);													//�������ض����������ı�
	int GetVexnum();															//��ȡ��ǰ������
	void DFS(int aVex, bool aVisited[], int& adeep, Path& path, list<Path>& pathlist);		//�����������
	void DFSTraverse(int nVex, list<Path>& pathlist);							//ʹ��������������������
	//�õ�����֮���Ȩ����С֮�ͣ��Լ�����·��
	void getBestPath(int startpoint, int endpoint, Path& Bestpath, int& Mweight);
	//�ϲ�·��
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
