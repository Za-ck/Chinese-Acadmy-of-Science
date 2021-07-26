#pragma once
#define WIN_WIDTH 300
#define WIN_HEIGHT 200

// CWinDlg 对话框

class CWinDlg : public CDialogEx
{
	DECLARE_DYNAMIC(CWinDlg)

public:
	CWinDlg(CWnd* pParent = NULL);   // 标准构造函数
	virtual ~CWinDlg();

// 对话框数据
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_WIN_DIALOG };
#endif

protected:
	virtual void DoDataExchange(CDataExchange* pDX);   

	//内存 DC
	CDC m_dcMem;

	//更新窗口
	void UpdateWindow(); 

	//初始化背景
	void InitBackground(); 
	DECLARE_MESSAGE_MAP()
public:
	virtual BOOL OnInitDialog();
	afx_msg void OnPaint();
	afx_msg void OnMButtonUp(UINT nFlags, CPoint point);
};
