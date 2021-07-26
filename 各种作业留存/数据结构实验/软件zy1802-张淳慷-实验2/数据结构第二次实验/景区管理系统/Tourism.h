#ifndef TOURISM_H
#define TOURISM_H


#include"Graph.h"

using namespace std;
class Tourism {
private:
	Graph g;
public:
	void CreateGraph();			//�������ξ���ͼ
	void GetSpotInfo();			//�õ�ĳ�������Ϣ
	void TravelPath();			//��ĳ���㿪ʼ������·��
	void FindBestPath();		//�õ�����֮�����̾���;��볤��
	void f4();
	void f5();
	void output_Vex();
	void output_edge();
	void output_travel_path(int n, list<Path>::iterator iter);
	void Circuit_planning();
	Tourism() {}
};

#endif // TOURISM_H