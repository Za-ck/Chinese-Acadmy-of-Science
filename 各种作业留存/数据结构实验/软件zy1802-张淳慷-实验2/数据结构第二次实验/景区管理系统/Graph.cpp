#include"Graph.h"
#include<iostream>
using namespace std;


//��ʼ��ͼ
void Graph::init() {
	this->size = 0;
	//��ʼ����
	for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 20; j++) {
			Gedge[i][j] = 0;
		}
	}
}

//���붥��
bool Graph::insertVex(Vex v) {
	this->Gvex[size++] = v;
	return false;
}

//�����
bool Graph::insertEdge(Edge e) {
	int vex1 = e.GetVex1();
	int vex2 = e.GetVex2();
	int weight = e.GetWeight();

	this->Gedge[vex1][vex2] = weight;
	this->Gedge[vex2][vex1] = weight;
	return false;
}

//��ѯ������Ϣ
Vex Graph::GetVex(int index) {
	return Gvex[index];
}

//��ѯ��ָ�����������ı�
int* Graph::FindEdge(int index) {
	return Gedge[index];
}

//��ȡ��ǰ������
int Graph::GetVexnum() {
	return this->size;
}

void Graph::DFS(int aVex, bool aVisited[], int& adeep, Path& path, list<Path>& pathlist) {
	//�жϵݹ�����Ƿ�ﵽ���
	if (adeep >= this->GetVexnum() - 1) {
		aVisited[aVex] = false;
		pathlist.push_back(path);
		//�ݹ���ȼ�һ
		adeep--;
		return;
	}

	//�ų����б������õ�
	aVisited[aVex] = true;

	//�������ڱ�
	int* edge = this->FindEdge(aVex);
	for (int i = 0; i < size; i++) {
		if (edge[i] != 0 && !aVisited[i]) {
			path.vexs[adeep++] = i;
			DFS(i, aVisited, adeep, path, pathlist);
		}
	}

	//�ݹ���ȼ�һ
	aVisited[aVex] = false;
	adeep--;
}

void Graph::getBestPath(int startpoint, int endpoint, Path& Bestpath, int& Mweight) {
	int* path[20];//�洢���·��
	int weight[20];
	bool visited[20] = { false };
	//��ʼ��path
	for (int i = 0; i < 20; i++) {
		path[i] = new int[this->GetVexnum()];
		for (int j = 0; j < this->GetVexnum(); j++) {
			path[i][j] = this->GetVexnum() + 1;
		}
	}
	//weight��ʼ��
	for (int i = 0; i < size; i++) {
		weight[i] = Gedge[startpoint][i];
		if (Gedge[startpoint][i] != 0) {
			path[i][0] = i;
		}
	}
	visited[startpoint] = true;//�ж��ѷ���
	int count = 1;
	while (count != this->GetVexnum()) {
		int temp = 0;
		int min = 1000000;
		for (int i = 0; i < this->GetVexnum(); i++) {
			if (!visited[i] && weight[i] < min && weight[i] != 0) {
				min = weight[i];
				temp = i;
			}
		}
		visited[temp] = true;//�ж��ѷ���
		count++;
		for (int i = 0; i < this->GetVexnum(); i++) {
			if (!visited[i] && Gedge[temp][i] != 0) {
				if (weight[i] == 0) {
					//�ϲ�path
					delete[] path[i];
					path[i] = pathMerge(path[temp], i);
					weight[i] = Gedge[temp][i] + weight[temp];
					continue;
				}
				if ((weight[temp] + Gedge[temp][i]) < weight[i]) {
					//�ϲ�path
					delete[] path[i];
					path[i] = pathMerge(path[temp], i);
					weight[i] = weight[temp] + Gedge[temp][i];
					continue;
				}
			}
		}
	}
	//�õ�·��֮�󽫽�����浽path����
	Bestpath.vexs[0] = startpoint;
	for (int i = 0; i < this->GetVexnum(); i++) {
		Bestpath.vexs[i + 1] = path[endpoint][i];
		if (path[endpoint][i + 1] == (this->GetVexnum() + 1)) {
			Bestpath.size = i + 2;
			break;
		}
	}
	//�õ���С����
	Mweight = weight[endpoint];
}


void Graph::DFSTraverse(int nVex, list<Path>& pathlist) {
	bool visited[20] = { false };
	Path path;
	int deep = 0;
	this->DFS(nVex, visited, deep, path, pathlist);
}



void Graph::Prim(Edge* Circuit[], int& Minway) {
	bool visited[20] = { false };//�����ж��Ƿ��Ѿ�����
	int mincost[20][2];//�洢��СȨֵ
	int index = 0;
	//��ʼ��
	for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 2; j++) {
			mincost[i][j] = 5000;
		}
	}
	//��ʼ��
	Minway = 0;
	visited[0] = true;
	for (int i = 0; i < this->GetVexnum(); i++) {
		//�ж��Ƿ��б߿ɴ�
		if (Gedge[0][i] != 0) {
			mincost[i][0] = 0;
			mincost[i][1] = Gedge[0][i];
		}
	}
	for (int i = 0; i < this->GetVexnum() - 1; i++) {
		int min = 100000;
		//�ҵ���С�ı�
		for (int j = 0; j < this->GetVexnum(); j++) {
			if (!visited[j] && min > mincost[j][1]) {
				index = j;
				min = mincost[j][1];
			}
		}
		Circuit[i] = new Edge(mincost[index][0], index, mincost[index][1]);
		visited[index] = true;//�ж��ѷ���
		Minway += min;
		//��������
		for (int j = 0; j < this->GetVexnum(); j++) {
			if (!visited[j] && Gedge[index][j] != 0 && mincost[j][1] > Gedge[index][j]) {
				mincost[j][0] = index;
				mincost[j][1] = Gedge[index][j];
			}
		}
	}
}
//�ϲ�·��
int* Graph::pathMerge(int* temp, int i) {
	int* path_i = new int[this->GetVexnum()];

	int index = 0;
	bool flag = true;
	for (int i = 0; i < this->GetVexnum(); i++) {
		path_i[i] = temp[i];
		if (temp[i] == this->GetVexnum() + 1 && flag) {
			index = i;
			flag = false;
		}
	}
	path_i[index] = i;
	return path_i;
}
