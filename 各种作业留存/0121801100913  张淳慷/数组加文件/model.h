#ifndef MODEL_H
#define MODEL_H
#include<stdio.h>
#include<time.h>

typedef struct Card
{
	char aName[18];         // ����
	char aPwd[8];           // ����
	int  nStatus;           // ��״̬(0-δ�ϻ���1-�����ϻ���2-��ע��)
	time_t tStart;          // ����ʱ��
	time_t tEnd;            // ���Ľ�ֹʱ�� 
	double fTotalUse;        // �ۼƽ��
	time_t tLast;           // ���ʹ��ʱ��
	int nUseCount;          // ʹ�ô���
	double fBalance;         // ���
	int nDel;               // ɾ����ʶ 0-δɾ��,1-ɾ��
}Card;

typedef struct CardNode
{
	Card data;
	struct CardNode* next;
}CardNode, *lpCardNode;

#endif