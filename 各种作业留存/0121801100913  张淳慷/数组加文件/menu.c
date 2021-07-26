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
	printf("--------�˵�--------\n");
	printf("1.��ӿ�\n");
	printf("2.��ѯ��\n");
	printf("3.�ϻ�\n");
	printf("4.�»�\n");
	printf("5.��ֵ\n");
	printf("6.�˷�\n");
	printf("7.��ѯͳ��\n");
	printf("8.ע����\n");
	printf("0.�˳�\n");
	printf("��ѡ��˵����ţ�0~8����");
}

void add()
{
	struct tm* startTime;        //����ʱ��
	struct tm* endTime;        //����ʱ��
	struct Card card;
	char aName[32] = { '\0' };   // ����Ŀ���
	char aPwd[20] = { '\0' };    // ���������     
	int nNameSize = 0;
	int nPwdSize = 0;

	printf("-------��ӿ�-------\n");
	printf("�����뿨��(0~18)��");
	scanf("%s", aName);
	printf("����������(0~8)��");
	scanf("%s", aPwd);

	//�жϿ��Ż������볤��
	nNameSize = strlen(aName);
	nPwdSize = strlen(aPwd);
	if (nNameSize > 18 || nPwdSize > 8)
	{
		printf("���Ż������볬���涨����");
		return;
	}
	strcpy(card.aName, aName);
	strcpy(card.aPwd, aPwd);

	printf("�����뿪����");
	scanf("%lf", &card.fBalance);

	card.fTotalUse = card.fBalance;		//�ۼ�ʹ��
	card.nStatus = 0;       //��״̬
	card.nUseCount = 0;     //ʹ�ô���
	card.nDel = 0;          //ɾ����ʶ

	//����ʱ�䣬�Լ�����ʱ��
	card.tStart = card.tEnd = card.tLast = time(NULL);
	startTime = localtime(&card.tStart);
	endTime = localtime(&card.tEnd);
	endTime->tm_year = startTime->tm_year + 1;
	card.tEnd = mktime(endTime);

	addCard(card);
	printf("��ӳɹ�\n");
	printf("����\t״̬\t���\t�ۼ�ʹ��\tʹ�ô���\t\n");
	printf("%s\t%d\t%0.1f\t%0.1f\t\t%d\t", card.aName, card.nStatus, card.fBalance,
		card.fTotalUse, card.nUseCount );



}

void query()
{
	printf("--------��ѯ��--------\n");
	printf("�ļ���һ����%d�ſ�\n", getCardCount(CARDPATH));
	char Name[18] = { '\0' };
	char pws[20] = { '\0' };
	Card* pCard = NULL;
	char aLastTime[20] = { 0 };
	int nIndex = 0;
	int u,i = 0;

	printf("�������ѯ�Ŀ���(����1-18):");
	scanf("%s", Name);

	//��ѯ��
	pCard = queryCard(Name,&i);


	if (pCard == NULL )
	{
		printf("û�иÿ�����Ϣ��");
	}
	else {
		timeToString(pCard->tLast, aLastTime);
		printf("����\t״̬\t���\t�ۼ�ʹ��\tʹ�ô���\t�ϴ�ʹ��ʱ��\n");
		printf("%s\t%d\t%0.1f\t%0.1f\t\t%d\t\t%s\n", pCard->aName, pCard->nStatus, pCard->fBalance,
			pCard->fTotalUse, pCard->nUseCount, aLastTime);
		printf("1.�޸�����\n");
		printf("0.����������������\n");
		scanf("%d", &u);
		if (u == 1) {
			printf("������ԭ����");
			scanf("%s", pws);
			if (strcmp(pws, pCard->aPwd) == 0) {
				printf("����������");
				scanf("%s", pCard->aPwd);
				UpdateCard(pCard, CARDPATH, i);

			}
			else {
				printf("�������");
			}

		}

	}


}

void exitApp()
{
	return;
}
