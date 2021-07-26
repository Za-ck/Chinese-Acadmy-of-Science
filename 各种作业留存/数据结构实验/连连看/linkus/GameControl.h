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
	
	
	//����
	CGameControl();
	~CGameControl();

	// ��Ϸ��ͼ����ָ��
	int** m_pGameMap ;

	// ��Ϸ�߼���������
	CGameLogic m_GameLogic;

	// ��ʼ��Ϸ
	void StartGame(void); 

	// �õ�ĳһ��Ԫ��
	int GetElement(int nRow, int nCol); 

	// ���õ�һ����
	void SetFirstPoint(int nRow, int nCol); 

	// ���õڶ�����
	void SetSecPoint(int nRow, int nCol);    

	// �����ж�
	bool Link(Vertex avPath[4], int &nVexnum); 
	
	// �ж��Ƿ��ʤ
	bool IsWin(void); 
};

