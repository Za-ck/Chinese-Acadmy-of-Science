#pragma once
#pragma comment(lib, "winmm.lib")
#include <mmsystem.h>
#include"GameControl.h"
#include"WinDlg.h"
#include "LoseDlg.h"
#include "afxwin.h"
#define GAMEWND_WIDTH 800
#define GAMEWND_HEIGHT 600
// CGameDlg 对话框

class CGameDlg : public CDialogEx
{
	DECLARE_DYNAMIC(CGameDlg)

public:
	CGameDlg(CWnd* pParent = NULL,int mode=0);   // 标准构造函数
	virtual ~CGameDlg();

// 对话框数据
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_GAME_DIALOG };
#endif

protected:
	//模式
	int mode;

	//背景ID
	int bkg; 

	//元素资源路径
	TCHAR elem[100]; 
	CBrush m_brush;
	
	//字体
	CFont m_font;
	HICON m_hIcon;

	//内存 DC
	CDC m_dcMem; 

	// 背景 DC
	CDC m_dcBG;

	//元素内存 DC
	CDC m_dcElement;

	//掩码内存 DC
	CDC m_dcMask; 

	//游戏区起始点
	CPoint m_ptGame; 

	//元素图片的大小
	CSize size_Elem; 

	//游戏区域大小
	CRect m_rtGameRect; 

	//游戏控制类
	CGameControl m_GameC; 

	//判断是否为第一个点
	bool m_bFirstPoint; 

	//游戏状态标识
	bool m_bPlaying;

	//游戏暂停标识
	bool m_bPause; 

	//进度条范围和步长
	int m_nMax, m_nStep;

	//初始化背景
	void InitBackground();

	//设置等级
	void SetLevel(); 

	//设置主题
	void SetTheme(); 
	DECLARE_MESSAGE_MAP();

	virtual void DoDataExchange(CDataExchange* pDX);

public:
	static int level; //关卡
	afx_msg void OnPaint();
	virtual BOOL OnInitDialog();
	HCURSOR OnQueryDragIcon();

	//更新窗口的图片
	void UpdateWindow();

	//初始化元素
	void InitElement();

	//更新地图
	void UpdateMap();

	//画提示线
	void DrawTipLine(Vertex asvPath[4], int nVexnum);

	//画提示框
	void DrawTipFrame(int nRow, int nCol);

	//进行重排
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
