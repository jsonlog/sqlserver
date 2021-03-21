// MyCoolMenu.cpp: implementation of the CMyCoolMenu class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
#include "MyCoolMenu.h"
#include "bbb.h"
#ifdef _DEBUG
#undef THIS_FILE
static char THIS_FILE[]=__FILE__;
#define new DEBUG_NEW
#endif
extern CBbbApp theApp;
//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

CMyCoolMenu::CMyCoolMenu()
{
	index = 0;
}

CMyCoolMenu::~CMyCoolMenu()
{
	index = 0;
}

/**********************************************************
��  ��  ��: ChangeMenuStyle
��      ��: �ı����˵��ķ��
��  ��  ��: HMENU hMenu, BOOL bTop
��	��  ֵ: BOOL,dndn�ɹ�ʱ�����棬ʧ��ʱ����
***********************************************************/
BOOL CMyCoolMenu::ChangeMenuStyle(HMENU hMenu, BOOL bTop = FALSE)
{
	CMenu *pMenu = CMenu::FromHandle(hMenu);
	if(pMenu != NULL)
	{
		for(UINT i = 0; i < pMenu->GetMenuItemCount(); i ++)
		{
			lpMenu[index].nMenuID = pMenu->GetMenuItemID(i);
			if(lpMenu[index].nMenuID < 0 && bTop)
			{
				lpMenu[index].nMenuID = -2;
			}
			pMenu->GetMenuString(i, lpMenu[index].strText, MF_BYPOSITION);
			pMenu->ModifyMenu(i, MF_OWNERDRAW|MF_BYPOSITION |MF_STRING,
				lpMenu[index].nMenuID,LPCTSTR(&lpMenu[index]));
			CMenu *pSubMenu = pMenu->GetSubMenu(i);
			if(pSubMenu && lpMenu[index].nMenuID != -2 && !bTop)
			{
				lpMenu[index].nMenuID = -1;
			}
			index += 1;
			if(pSubMenu)
			{
				ChangeMenuStyle(pSubMenu->GetSafeHmenu());
			}
		}
	}
	return TRUE;
}
/**********************************************************
��  ��  ��: AttachMenu
��      ��: ����ǰ���ڵĲ˵����ҵĲ˵�����
��  ��  ��: HMENU hMenu,UINT uToolBarID,CSize sz
��	��  ֵ: BOOL,dndn�ɹ�ʱ�����棬ʧ��ʱ����UINT uToolBarID,
***********************************************************/
BOOL CMyCoolMenu::AttachMenu(HMENU hMenu,CSize sz)
{
	Attach(hMenu);
	ChangeMenuStyle(hMenu, TRUE);
	return TRUE;
}

/**********************************************************
��  ��  ��: DrawBestRect
��      ��: ������������Ч����
��  ��  ��: CDC *pDC, CRect rect, COLORREF cr1,COLORREF cr2, BOOL bHor
��	��  ֵ: NULL
***********************************************************/
void CMyCoolMenu::DrawBestRect(CDC *pDC, CRect rect, COLORREF cr1,COLORREF cr2, BOOL bHor)
{
	int r1 = GetRValue(cr1);
	int g1 = GetGValue(cr1);
	int b1 = GetBValue(cr1);
	int r2 = GetRValue(cr2);
	int g2 = GetGValue(cr2);
	int b2 = GetBValue(cr2);
	float dr = ((float)(r2 - r1))/(float)(rect.Width());
	float dg = ((float)(g2 - g1))/(float)(rect.Width());
	float db = ((float)(b2 - b1))/(float)(rect.Width());
	for(int i = rect.left; i < rect.right; i ++)
	{
		int r = r1 + (int)(dr*((float)(i - rect.left)));
		int g = g1 + (int)(dg*((float)(i - rect.left)));
		int b = b1 + (int)(db*((float)(i - rect.left)));
		CPen pen(PS_SOLID, 1, RGB(r, g, b));
		CPen *old = pDC->SelectObject(&pen);
		pDC->MoveTo(i, rect.top);
		pDC->LineTo(i, rect.bottom);
	}
}
/**********************************************************
��  ��  ��: DrawTop
��      ��: ����˲˵�
��  ��  ��: CDC *pDC,CRect rect,BOOL bSelected
��	��  ֵ: NULL
***********************************************************/
void CMyCoolMenu::DrawTop(CDC *pDC,CRect rect,BOOL bSelected)
{	
	if(bSelected)
	{
		pDC->SelectStockObject(BLACK_PEN);
		pDC->Rectangle(&rect);
		rect.DeflateRect(1, 1);
		pDC->FillSolidRect(&rect, RGB(185, 185, 255));
	}
	else
	{		
		CRect rtWnd;		
		AfxGetMainWnd()->GetClientRect(&rtWnd);
		CRect rcGray(rect);
		rcGray.left = 300;
		rcGray.right = rtWnd.right + 4;		
		pDC->FillSolidRect(&rcGray,RGB(255, 176, 55));
		CRect rcWhite(rect);		
		pDC->FillSolidRect(&rcWhite,RGB(255, 176, 55));	
	}
}
/**********************************************************
��  ��  ��: DrawBGColor
��      ��: �˵���ı������������
��  ��  ��: CDC* pDC, CRect rect, BOOL bSelected
��	��  ֵ: NULL
***********************************************************/
void CMyCoolMenu::DrawBGColor(CDC* pDC, CRect rect, BOOL bSelected)
{
	if(bSelected)
	{
		pDC->SelectStockObject(NULL_BRUSH);
		pDC->SelectStockObject(BLACK_PEN);
		pDC->Rectangle(&rect);
		rect.DeflateRect(1, 1);
		DrawBestRect(pDC,rect,100,255,TRUE);
	}
	else
	{
		CRect rcGray(rect);
		rcGray.right = rcGray.left + Public_Area;
		pDC->FillSolidRect(&rcGray, RGB(0, 0, 0));
		CRect rcWhite(rect);
		rcWhite.left = rcGray.right;
		pDC->FillSolidRect(&rcWhite,RGB(255, 255, 255));
	}
}
/**********************************************************
��  ��  ��: DrawText
��      ��: �����˵�����ı�
��  ��  ��: CDC* pDC, CRect rect, CString sText
��	��  ֵ: NULL
***********************************************************/
void CMyCoolMenu::DrawText(CDC* pDC, CRect rect, CString sText)
{
	pDC->DrawText(sText, &rect, DT_LEFT | DT_VCENTER | DT_SINGLELINE);
}
/**********************************************************
��  ��  ��: DrawGray
��      ��: �����Ч�˵�����ı�
��  ��  ��: CDC* pDC, CRect rect, CString sText
��	��  ֵ: NULL
***********************************************************/
void CMyCoolMenu::DrawGray(CDC* pDC, CRect rect, CString sText)
{
	int nMode =pDC->SetBkMode(TRANSPARENT);
	pDC->SetTextColor(RGB(127,127,127));
	pDC->DrawText("   "+sText,&rect,DT_EXPANDTABS|DT_VCENTER|DT_SINGLELINE);
	
}
/**********************************************************
��  ��  ��: DrawItem
��      ��: �ֿ���ÿһ�˵���
��  ��  ��: LPDRAWITEMSTRUCT  lpDIS���˵�������
��	��  ֵ: NULL
***********************************************************/
void CMyCoolMenu::DrawItem(LPDRAWITEMSTRUCT  lpDIS)
{	
	CDC* pDC = CDC::FromHandle(lpDIS->hDC);	
	VERIFY(pDC);pDC->SetBkMode(TRANSPARENT);
	CRect rcItem = lpDIS->rcItem;
	UINT uState  = lpDIS->itemState;	
	if(lpDIS->itemData == NULL)	return;
	CString strText = ((CMenuItemContext*)(lpDIS->itemData))->strText;
	UINT nMenuID    = ((CMenuItemContext*)(lpDIS->itemData))->nMenuID;
	CRect rcText(rcItem);
	switch(nMenuID)
	{
	case -2:// -2:����˵���   
		if(uState&ODS_SELECTED)
		{
			DrawTop(pDC,rcItem,TRUE);
		}
		else
		{
			DrawTop(pDC,rcItem,FALSE);
		}
		DrawText(pDC,rcItem,strText);
		break;
	case -1://-1:�����˵�  
		strText= "\t   "+strText;
		if(uState&ODS_SELECTED)
			DrawBestRect(pDC,rcItem,RGB(255,0,255),RGB(255,255,255),TRUE);
		else
			DrawTop(pDC,rcItem,TRUE);
		DrawText(pDC,rcText,strText);
		break;
	case 0://0:�ָ���  
		rcText.top    = rcText.Height()/2+rcText.top ;
		rcText.bottom = rcText.top +2;
		rcText.left  += 2;
		rcText.right -= 2;
		pDC->Draw3dRect(rcText,RGB(64,0,128),RGB(255,255,255));
		break;
	default://>0:һ��Ĳ˵�	
		if(uState&ODS_SELECTED)//��ѡ��
		{
			rcItem.left  -= 20;
			rcItem.right -= 0;
			DrawBGColor(pDC,rcItem,TRUE);
		}
		else
		{
			rcItem.left  -= 20;
			rcItem.right -= 0;
			DrawBGColor(pDC,rcItem,FALSE);//
		}
		if(uState&ODS_GRAYED)
		{
			DrawGray(pDC,rcText,strText);
			break;
		}
		if(uState&ODS_CHECKED)//�Ǳ�check����,
		{
			DrawText(pDC,rcText,"   "+strText);//��ǰ״̬��check!
			break;
		}
		else
		{
			DrawText(pDC,rcText,"      "+strText);//δѡȡ
			break;
		}
		break;
	}
}
/**********************************************************
��  ��  ��: MeasureItem
��      ��: ����ÿһ��ķ���
��  ��  ��: LPMEASUREITEMSTRUCT lpMISҪ�õ��Ľṹ
��	��  ֵ: NULL
***********************************************************/
void CMyCoolMenu::MeasureItem(LPMEASUREITEMSTRUCT lpMIS)
{	
	lpMIS->itemWidth = 130;
	lpMIS->itemHeight = 26;
	UINT nMenuID= ((CMenuItemContext*)(lpMIS->itemData))->nMenuID;
	switch(nMenuID)
	{
	case -2:// -2:����˵���  
		lpMIS->itemWidth =((CMenuItemContext*)(lpMIS->itemData))->strText.GetLength()*5;
		break;
	case -1://-1:�����˵�  
		lpMIS->itemWidth =((CMenuItemContext*)(lpMIS->itemData))->strText.GetLength()*10;
		break;
	case 0://0:�ָ���
		lpMIS->itemHeight =10;
		break;
	default://>0:һ��Ĳ˵�		
		break;	
	}
}


