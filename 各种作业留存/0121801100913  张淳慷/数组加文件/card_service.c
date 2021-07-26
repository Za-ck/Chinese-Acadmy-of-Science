#include<string.h>
#include<stdio.h>
#include<stdlib.h>
#include"model.h"
#include"global.h"
#include"card_file.h"
#include"card_service.h"

Card aCard[50];//卡信息结构体数组
int nCount = 0;//卡信息实际个数

int addCard(Card card)
{
	return saveCard(&card, CARDPATH);
}

//结构体数组查询卡
Card* queryCard(const char* pName, int* a)
{

	nCount = getCardCount(CARDPATH);

	if (TRUE == readCard(aCard,CARDPATH))
	{
		for (int i = 0; i < nCount; i++)
		{
			if (strcmp(aCard[i].aName, pName) == 0)
			{
				*a = i;
				return &aCard[i];
				
			}
		}
	}

}
