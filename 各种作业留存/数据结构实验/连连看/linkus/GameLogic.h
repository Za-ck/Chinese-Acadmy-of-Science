#pragma once
#include"global.h"


//�ж��߼���
class CGameLogic {
public:
	CGameLogic();
	~CGameLogic();
	int** InitMap();

	//�����ڽ��������ж�ʱ�������Ķ���
	Vertex m_avPath[4]; 

	//������
	int m_nVexNum;     

	void ReleaseMap(int** &pGameMap);
	
	//�ж��Ƿ���ͨ
	bool IsLink(int** pGameMap, Vertex v1, Vertex v2); 

	// ����
	void Clear(int** pGameMap, Vertex v1, Vertex v2); 

	//�õ�·�������ص��Ƕ�����
	int GetVexPath(Vertex avPath[4]); 

	//�жϺ����Ƿ���ͨ
	bool LinkInRow(int** pGameMap, Vertex v1, Vertex v2); 

	//�ж������Ƿ���ͨ
	bool LinkInCol(int** pGameMap, Vertex v1, Vertex v2); 

	//һ���յ���ͨ�ж�
	bool OneCornerLink(int** pGameMap, Vertex v1, Vertex v2); 

	//����ֱ�������ж�
	bool TwoCornerLink(int** pGameMap, Vertex v1, Vertex v2);

	//ֱ����ͨ Y ��
	bool LineY(int** pGameMap, int nRow1, int nRow2, int nCol); 

	//ֱ����ͨ X ��
	bool LineX(int** pGameMap, int nRow, int nCol1, int nCol2); 

	//���һ��·������
	void PushVertex(Vertex v);	

	//ȡ��һ������
	void PopVertex(); 

	//���ջ
	void ClearStack(); 

	// �ж�ͼ�ж����ǲ���ȫΪ��
	bool IsBlank(int** pGameMap); 
};