#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#include"model.h"
#include"global.h"
#include"tool.h"

#define CARDCHARNUM 256

int saveCard(const Card* pCard, const char* pPath)
{
	FILE* fp = NULL;

	//打开文件
	fp = fopen(pPath, "ab");
	if (fp == NULL)
	{
		return FALSE;
	}

	//将数据写进二进制文件
	fwrite(pCard, sizeof(Card), 1, fp);


	//关闭文件
	fclose(fp);

	return TRUE;

}

int readCard(Card* pCard, const char* pPath)
{
	FILE* fp = NULL;
	int i = 0;

	//打开文件
	fp = fopen(pPath, "rb");
	if (fp == NULL)
	{
		return FALSE;
	}

	//读取文件
	while (!feof(fp))
	{
		fread(&pCard[i], sizeof(Card), 1, fp);
		i++;
	}

	//文件关闭
	fclose(fp);

	return TRUE;
}

int getCardCount(const char* pPath)
{
	FILE* fp = NULL;
	int nCount = 0;
	char aBuf[CARDCHARNUM] = { 0 };
	//打开文件
	fp = fopen(pPath, "rb");
	if (fp == NULL)
	{
		return FALSE;
	}


	//读取文件
	while (!feof(fp))
	{
		memset(aBuf, 0, CARDCHARNUM);//清空数组
		if (fread(aBuf, sizeof(Card), 1, fp) != NULL)
		{
			if (strlen(aBuf) > 0)
			{
				nCount++;
			}
		}
	}

	//文件关闭
	fclose(fp);

	return nCount;
}

int UpdateCard(const Card* pCard, const char* pPath, int nIndex)
{
	FILE* fp = NULL;
	int nLine = 0;
	long lPosition = 0;
	Card bBuf;

	if ((fp = fopen(pPath, "rb+")) == NULL) {
		return FALSE;
	}
	while ((!feof(fp)) && (nLine < nIndex - 1)) {
		if (fread(&bBuf, sizeof(Card), 1, fp) != 0) {
			lPosition = ftell(fp);
			nLine++;
		}
	}
	fseek(fp, lPosition, 0);
	fwrite(pCard, sizeof(Card), 1, fp);
	fclose(fp);
	return TRUE;
}
