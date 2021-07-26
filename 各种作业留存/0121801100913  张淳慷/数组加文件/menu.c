#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<time.h>
#include<string.h>

#include"model.h"
#include"card_service.h"
#include"tool.h"
#include "global.h"

void outputMenu() 
{
	printf("--------菜单--------\n");
	printf("1.添加卡\n");
	printf("2.查询卡\n");
	printf("3.上机\n");
	printf("4.下机\n");
	printf("5.充值\n");
	printf("6.退费\n");
	printf("7.查询统计\n");
	printf("8.注销卡\n");
	printf("0.退出\n");
	printf("请选择菜单项编号（0~8）：");
}

void add()
{
	struct tm* startTime;        //开卡时间
	struct tm* endTime;        //截至时间
	struct Card card;
	char aName[32] = { '\0' };   // 输入的卡号
	char aPwd[20] = { '\0' };    // 输入的密码     
	int nNameSize = 0;
	int nPwdSize = 0;

	printf("-------添加卡-------\n");
	printf("请输入卡号(0~18)：");
	scanf("%s", aName);
	printf("请输入密码(0~8)：");
	scanf("%s", aPwd);

	//判断卡号或者密码长度
	nNameSize = strlen(aName);
	nPwdSize = strlen(aPwd);
	if (nNameSize > 18 || nPwdSize > 8)
	{
		printf("卡号或者密码超过规定长度");
		return;
	}
	strcpy(card.aName, aName);
	strcpy(card.aPwd, aPwd);

	printf("请输入开卡金额：");
	scanf("%lf", &card.fBalance);

	card.fTotalUse = card.fBalance;		//累计使用
	card.nStatus = 0;       //卡状态
	card.nUseCount = 0;     //使用次数
	card.nDel = 0;          //删除标识

	//开卡时间，以及截至时间
	card.tStart = card.tEnd = card.tLast = time(NULL);
	startTime = localtime(&card.tStart);
	endTime = localtime(&card.tEnd);
	endTime->tm_year = startTime->tm_year + 1;
	card.tEnd = mktime(endTime);

	addCard(card);
	printf("添加成功\n");
	printf("卡号\t状态\t余额\t累计使用\t使用次数\t\n");
	printf("%s\t%d\t%0.1f\t%0.1f\t\t%d\t", card.aName, card.nStatus, card.fBalance,
		card.fTotalUse, card.nUseCount );



}

void query()
{
	printf("--------查询卡--------\n");
	printf("文件中一共有%d张卡\n", getCardCount(CARDPATH));
	char Name[18] = { '\0' };
	char pws[20] = { '\0' };
	Card* pCard = NULL;
	char aLastTime[20] = { 0 };
	int nIndex = 0;
	int u,i = 0;

	printf("请输入查询的卡号(长度1-18):");
	scanf("%s", Name);

	//查询卡
	pCard = queryCard(Name,&i);


	if (pCard == NULL )
	{
		printf("没有该卡的信息！");
	}
	else {
		timeToString(pCard->tLast, aLastTime);
		printf("卡号\t状态\t余额\t累计使用\t使用次数\t上次使用时间\n");
		printf("%s\t%d\t%0.1f\t%0.1f\t\t%d\t\t%s\n", pCard->aName, pCard->nStatus, pCard->fBalance,
			pCard->fTotalUse, pCard->nUseCount, aLastTime);
		printf("1.修改密码\n");
		printf("0.继续进行其他操作\n");
		scanf("%d", &u);
		if (u == 1) {
			printf("请输入原密码");
			scanf("%s", pws);
			if (strcmp(pws, pCard->aPwd) == 0) {
				printf("输入新密码");
				scanf("%s", pCard->aPwd);
				UpdateCard(pCard, CARDPATH, i);

			}
			else {
				printf("密码错误");
			}

		}

	}


}

void exitApp()
{
	return;
}
