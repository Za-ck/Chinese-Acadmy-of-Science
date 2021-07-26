#include"Graph.h"
#include<iostream>
using namespace std;


//初始化图
void Graph::init() {
	this->size = 0;
	//初始化边
	for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 20; j++) {
			Gedge[i][j] = 0;
		}
	}
}

//插入顶点
bool Graph::insertVex(Vex v) {
	this->Gvex[size++] = v;
	return false;
}

//插入边
bool Graph::insertEdge(Edge e) {
	int vex1 = e.GetVex1();
	int vex2 = e.GetVex2();
	int weight = e.GetWeight();

	this->Gedge[vex1][vex2] = weight;
	this->Gedge[vex2][vex1] = weight;
	return false;
}

//查询顶点信息
Vex Graph::GetVex(int index) {
	return Gvex[index];
}

//查询与指定顶点相连的边
int* Graph::FindEdge(int index) {
	return Gedge[index];
}

//获取当前顶点数
int Graph::GetVexnum() {
	return this->size;
}

void Graph::DFS(int aVex, bool aVisited[], int& adeep, Path& path, list<Path>& pathlist) {
	//判断递归深度是否达到最大
	if (adeep >= this->GetVexnum() - 1) {
		aVisited[aVex] = false;
		pathlist.push_back(path);
		//递归深度减一
		adeep--;
		return;
	}

	//排除所有遍历过得点
	aVisited[aVex] = true;

	//返回相邻边
	int* edge = this->FindEdge(aVex);
	for (int i = 0; i < size; i++) {
		if (edge[i] != 0 && !aVisited[i]) {
			path.vexs[adeep++] = i;
			DFS(i, aVisited, adeep, path, pathlist);
		}
	}

	//递归深度减一
	aVisited[aVex] = false;
	adeep--;
}

void Graph::getBestPath(int startpoint, int endpoint, Path& Bestpath, int& Mweight) {
	int* path[20];//存储最短路径
	int weight[20];
	bool visited[20] = { false };
	//初始化path
	for (int i = 0; i < 20; i++) {
		path[i] = new int[this->GetVexnum()];
		for (int j = 0; j < this->GetVexnum(); j++) {
			path[i][j] = this->GetVexnum() + 1;
		}
	}
	//weight初始化
	for (int i = 0; i < size; i++) {
		weight[i] = Gedge[startpoint][i];
		if (Gedge[startpoint][i] != 0) {
			path[i][0] = i;
		}
	}
	visited[startpoint] = true;//判定已访问
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
		visited[temp] = true;//判定已访问
		count++;
		for (int i = 0; i < this->GetVexnum(); i++) {
			if (!visited[i] && Gedge[temp][i] != 0) {
				if (weight[i] == 0) {
					//合并path
					delete[] path[i];
					path[i] = pathMerge(path[temp], i);
					weight[i] = Gedge[temp][i] + weight[temp];
					continue;
				}
				if ((weight[temp] + Gedge[temp][i]) < weight[i]) {
					//合并path
					delete[] path[i];
					path[i] = pathMerge(path[temp], i);
					weight[i] = weight[temp] + Gedge[temp][i];
					continue;
				}
			}
		}
	}
	//得到路径之后将结果储存到path里面
	Bestpath.vexs[0] = startpoint;
	for (int i = 0; i < this->GetVexnum(); i++) {
		Bestpath.vexs[i + 1] = path[endpoint][i];
		if (path[endpoint][i + 1] == (this->GetVexnum() + 1)) {
			Bestpath.size = i + 2;
			break;
		}
	}
	//得到最小距离
	Mweight = weight[endpoint];
}


void Graph::DFSTraverse(int nVex, list<Path>& pathlist) {
	bool visited[20] = { false };
	Path path;
	int deep = 0;
	this->DFS(nVex, visited, deep, path, pathlist);
}



void Graph::Prim(Edge* Circuit[], int& Minway) {
	bool visited[20] = { false };//用于判定是否已经访问
	int mincost[20][2];//存储最小权值
	int index = 0;
	//初始化
	for (int i = 0; i < 20; i++) {
		for (int j = 0; j < 2; j++) {
			mincost[i][j] = 5000;
		}
	}
	//初始化
	Minway = 0;
	visited[0] = true;
	for (int i = 0; i < this->GetVexnum(); i++) {
		//判断是否有边可达
		if (Gedge[0][i] != 0) {
			mincost[i][0] = 0;
			mincost[i][1] = Gedge[0][i];
		}
	}
	for (int i = 0; i < this->GetVexnum() - 1; i++) {
		int min = 100000;
		//找到最小的边
		for (int j = 0; j < this->GetVexnum(); j++) {
			if (!visited[j] && min > mincost[j][1]) {
				index = j;
				min = mincost[j][1];
			}
		}
		Circuit[i] = new Edge(mincost[index][0], index, mincost[index][1]);
		visited[index] = true;//判定已访问
		Minway += min;
		//更新数组
		for (int j = 0; j < this->GetVexnum(); j++) {
			if (!visited[j] && Gedge[index][j] != 0 && mincost[j][1] > Gedge[index][j]) {
				mincost[j][0] = index;
				mincost[j][1] = Gedge[index][j];
			}
		}
	}
}
//合并路径
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
