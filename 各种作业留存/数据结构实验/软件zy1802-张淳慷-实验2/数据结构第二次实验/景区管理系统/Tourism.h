#ifndef TOURISM_H
#define TOURISM_H


#include"Graph.h"

using namespace std;
class Tourism {
private:
	Graph g;
public:
	void CreateGraph();			//创立旅游景区图
	void GetSpotInfo();			//得到某个点的信息
	void TravelPath();			//从某个点开始的旅游路线
	void FindBestPath();		//得到两点之间的最短距离和距离长度
	void f4();
	void f5();
	void output_Vex();
	void output_edge();
	void output_travel_path(int n, list<Path>::iterator iter);
	void Circuit_planning();
	Tourism() {}
};

#endif // TOURISM_H