#pragma once
#pragma comment(lib, "winmm.lib")
#include <mmsystem.h>
#include"GameControl.h"
#include"WinDlg.h"
#include "LoseDlg.h"
#include "afxwin.h"
#define GAMEWND_WIDTH 800
#define GAMEWND_HEIGHT 600
// CGameDlg �Ի���

class CGameDlg : public CDialogEx
{
	DECLARE_DYNAMIC(CGameDlg)

public:
	CGameDlg(CWnd* pParent = NULL,int mode=0);   // ��׼���캯��
	virtual ~CGameDlg();

// �Ի�������
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_GAME_DIALOG };
#endif

protected:
	//ģʽ
	int mode;

	//����ID
	int bkg; 

	//Ԫ����Դ·��
	TCHAR elem[100]; 
	CBrush m_brush;
	
	//����
	CFont m_font;
	HICON m_hIcon;

	//�ڴ� DC
	CDC m_dcMem; 

	// ���� DC
	CDC m_dcBG;

	//Ԫ���ڴ� DC
	CDC m_dcElement;

	//�����ڴ� DC
	CDC m_dcMask; 

	//��Ϸ����ʼ��
	CPoint m_ptGame; 

	//Ԫ��ͼƬ�Ĵ�С
	CSize size_Elem; 

	//��Ϸ�����С
	CRect m_rtGameRect; 

	//��Ϸ������
	CGameControl m_GameC; 

	//�ж��Ƿ�Ϊ��һ����
	bool m_bFirstPoint; 

	//��Ϸ״̬��ʶ
	bool m_bPlaying;

	//��Ϸ��ͣ��ʶ
	bool m_bPause; 

	//��������Χ�Ͳ���
	int m_nMax, m_nStep;

	//��ʼ������
	void InitBackground();

	//���õȼ�
	void SetLevel(); 

	//��������
	void SetTheme(); 
	DECLARE_MESSAGE_MAP();

	virtual void DoDataExchange(CDataExchange* pDX);

public:
	static int level; //�ؿ�
	afx_msg void OnPaint();
	virtual BOOL OnInitDialog();
	HCURSOR OnQueryDragIcon();

	//���´��ڵ�ͼƬ
	void UpdateWindow();

	//��ʼ��Ԫ��
	void InitElement();

	//���µ�ͼ
	void UpdateMap();

	//����ʾ��
	void DrawTipLine(Vertex asvPath[4], int nVexnum);

	//����ʾ��
	void DrawTipFrame(int nRow, int nCol);

	//��������
	void Rearrange();
	CProgressCtrl m_pro;
	CStatic m_level;


	afx_msg void OnClickedBtnStart();
	afx_msg void OnLButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnClickedBtnRearrange();
	afx_msg void OnTimer(UINT_PTR nIDEvent);
	afx_msg void OnClickedPause();
	afx_msg void OnClickedTip();
	afx_msg void OnClickedSetting();
	afx_msg void OnClickedHelp();
	afx_msg HBRUSH OnCtlColor(CDC* pDC, CWnd* pWnd, UINT nCtlColor);
};
