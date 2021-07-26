#pragma once
#include"GameLogic.h"
class CGameControl
{
public:
	static int s_nRows;
	static int s_nCols;
	static int s_nPicNum;
	Vertex m_svSelFst; 
	Vertex m_svSelSec; 
	
	
	//方法
	CGameControl();
	~CGameControl();

	// 游戏地图数组指针
	int** m_pGameMap ;

	// 游戏逻辑操作对象
	CGameLogic m_GameLogic;

	// 开始游戏
	void StartGame(void); 

	// 得到某一个元素
	int GetElement(int nRow, int nCol); 

	// 设置第一个点
	void SetFirstPoint(int nRow, int nCol); 

	// 设置第二个点
	void SetSecPoint(int nRow, int nCol);    

	// 消子判断
	bool Link(Vertex avPath[4], int &nVexnum); 
	
	// 判断是否获胜
	bool IsWin(void); 
};

