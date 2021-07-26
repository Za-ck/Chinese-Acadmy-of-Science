#include "Huffman.h"
#include<iostream>
#include<malloc.h>

#define OK 1
#define ERROR 0

using namespace std;

//生成Huffman树
int CreateHuffmanTree(HuffmanTree pHT,int weight[],int n){
	int s1, s2,i;
	int m = 2 * n - 1;

	for (i = 1; i <= n; i++) {
		pHT[i].weight = weight[i - 1];
		pHT[i].lchild = 0;
		pHT[i].rchild = 0;
		pHT[i].parent = 0;
	}
	for (i=n+1; i <= m; i++) {
		pHT[i].parent = 0;
		pHT[i].lchild = 0;
		pHT[i].rchild = 0;
		pHT[i].weight = 0;
	}



	for (i = n + 1; i <= m; i++)
	{
		//从pHT[1...i-1]中选择parent为0且weight最小的两个结点，其序号分别为s1和s2
		Select(pHT, i - 1, s1, s2);
		pHT[s1].parent = i;
		pHT[s2].parent = i; //修改s1和s2结点的父指

		pHT[i].lchild = s1;
		pHT[i].rchild = s2; //修改i结点的左右孩子指针

		pHT[i].weight = pHT[s1].weight + pHT[s2].weight; //修改权值

	}

	return OK;
}



//查找Huffman树节点数组中权值最小的节点
int Select(HuffmanTree pHT, int nSize) {
	int minValue = 0x7FFFFFFF;	//最小值
	int min = 0;				//元素序号

	//找到最小权值的元素序号
	for (int i = 1; i <= nSize; i++)
	{
		if (pHT[i].parent == 0 && pHT[i].weight<minValue)
		{
			minValue = pHT[i].weight;
			min = i;
		}
	}
	return min;
}

void Select(HuffmanTree &pHT, int i, int &s1, int &s2) {
	int minValue = 0x7FFFFFFF;

	//找到最小的一个权值
	for (int j = 1; j <= i; j++) {
		if (pHT[j].parent == 0 && pHT[j].weight<minValue) {
			minValue = pHT[j].weight;
			s1 = j;
		}
	}
//	cout << "最小权值：" << s1;

	minValue = 0x7FFFFFFF;
	//找到倒数第二小的权值
	for (int j = 1; j <= i; j++) {
		if (j != s1 && pHT[j].parent == 0 && pHT[j].weight < minValue) {
			minValue = pHT[j].weight;
			s2 = j;
		}
	}
//	cout << "\t次之权值：" << s2 << endl;
}

//生成Huffman编码 
int HuffmanCoding(HuffmanCode &pHC, HuffmanTree &pHT)
{
	//无栈非递归遍历Huffman树，求Huffman编码
	char cd[256] = { '\0' };	//记录访问路径
	int cdlen = 0;				//记录当前路径长度

	for (int i = 1; i < 512; i++) {
		pHT[i].weight = 0;	//遍历Huffman树时用做节点的状态标志
	}

	int p = 511;			//根节点
	while (p != 0) {
		//向左
		if (pHT[p].weight == 0) {
			pHT[p].weight = 1;
			if (pHT[p].lchild != 0) {
				p = pHT[p].lchild;
				cd[cdlen++] = '0';
			}
			//登记叶子节点的字符的编码
			else if(pHT[p].rchild==0){
				pHC[p] = (char*)malloc((cdlen + 1) * sizeof(char));
				cd[cdlen] = '\0';
				strcpy(pHC[p], cd);//复制编码
			}
		}
		//向右
		else if (pHT[p].weight == 1) {
			pHT[p].weight = 2;
			//右孩子为叶子节点
			if (pHT[p].rchild != 0) {
				p = pHT[p].rchild;
				cd[cdlen++] = '1';
			}
		}
		//退回父节点，编码长度减一
		else {
			pHT[p].weight = 0;
			p = pHT[p].parent;
			cdlen--;
		}
	}
	return OK;
}
void CreatHC(int n ,HuffmanCode &pHC, HuffmanTree &pHT)//建立编码表
{

	char* cd;
	int m = 2 * n - 1;
	cd = new char[n];//分配临时存放每个字符编码的动态数组空间 
	cd[n - 1] = '\0';
	pHC = (HuffmanCode)malloc((n + 1) * sizeof(char*));//分配储存n个字符编码的编码表空间 


	for (int i = 1; i <= n; i++)//逐个字符求哈夫曼编码 
	{
		int start = n - 1;//start开始时指向最后，即编码结束位置 
		int c = i;
		int f = pHT[i].parent;

		while (f != 0)
		{
			if (pHT[f].lchild == c)
			{
				cd[--start] = '0';
			}
			else
			{

				cd[--start] = '1';
			}

			c = f;
			f = pHT[f].parent;//继续向上回溯 

		}   //求出第i个字符的编码 

		pHC[i] = new char[n - start];//为第i个字符编码bc分配空间 
		strcpy(pHC[i], &cd[start]);

	}
	free(cd);//释放临时空间 
	cout << "____________编码表_______________" << endl;

	cout << "字符" << ":" << "编码" << endl;
	for (int i = 1; i <= 256; i++)
	{
		cout << i - 1 << ":" << pHC[i] << endl;
	}



}

