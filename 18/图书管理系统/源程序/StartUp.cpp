// StartUp.cpp : implementation file
//

#include "stdafx.h"
#include "bbb.h"
#include "StartUp.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif
#define AW_BLEND                   0x00080000 //���뵭��
#define AW_HIDE                    0x00010000 //����

/////////////////////////////////////////////////////////////////////////////
// CStartUp dialog


CStartUp::CStartUp(CWnd* pParent /*=NULL*/)
	: CDialog(CStartUp::IDD, pParent)
{
	//{{AFX_DATA_INIT(CStartUp)
		// NOTE: the ClassWizard will add member initialization here
	//}}AFX_DATA_INIT
}


void CStartUp::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CStartUp)
		// NOTE: the ClassWizard will add DDX and DDV calls here
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CStartUp, CDialog)
	//{{AFX_MSG_MAP(CStartUp)
	ON_WM_TIMER()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CStartUp message handlers

BOOL CStartUp::OnInitDialog() 
{
	CDialog::OnInitDialog();
	
	// TODO: Add extra initialization here
	CenterWindow(); //�Ի���������λ��
	DWORD dwStyle = AW_BLEND;
	HINSTANCE hInst = LoadLibrary("User32.DLL");  //���붯̬���ӿ�User32.DLL
	typedef BOOL(WINAPI MYFUNC(HWND,DWORD,DWORD));
	MYFUNC* AnimateWindow;  //��������
	AnimateWindow = (MYFUNC *)::GetProcAddress(hInst,"AnimateWindow");
	AnimateWindow(this->m_hWnd,750,dwStyle);
	FreeLibrary(hInst);      //�ͷŶ�̬���ӿ�
	SetTimer(1,2000,NULL); //���ö�ʱ��

	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}

void CStartUp::OnTimer(UINT nIDEvent) 
{
	// TODO: Add your message handler code here and/or call default
	DWORD dwStyle = AW_BLEND;
	HINSTANCE hInst=LoadLibrary("User32.DLL"); //���붯̬���ӿ�User32.DLL
	typedef BOOL(WINAPI MYFUNC(HWND,DWORD,DWORD));
	MYFUNC* AnimateWindow; //��������
	AnimateWindow=(MYFUNC *)::GetProcAddress(hInst,"AnimateWindow");
	AnimateWindow(this->GetSafeHwnd(),750,AW_HIDE | dwStyle);
	FreeLibrary(hInst);   //�ͷŶ�̬���ӿ�
	KillTimer(1);
	CDialog::OnOK(); //�رնԻ���
	CDialog::OnTimer(nIDEvent);
}
