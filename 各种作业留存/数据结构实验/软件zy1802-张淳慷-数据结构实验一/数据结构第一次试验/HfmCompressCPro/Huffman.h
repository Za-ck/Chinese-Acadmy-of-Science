#ifndef HUFFMAN_H
#define HUFFMAN_H

//Huffman树节点
typedef struct 
{
	int weight;	//权值
	int parent;	//父节点
	int lchild;	//左孩子
	int rchild;	//右孩子
}HTNode, *HuffmanTree;

typedef char **HuffmanCode;		//Huffman编码

//生成Huffman树
int CreateHuffmanTree(HuffmanTree pHT,int weight[],int n);

//生成Huffman编码
int HuffmanCoding(HuffmanCode &pHC, HuffmanTree  &pHT);


int Select(HuffmanTree pHT, int nSize);
void Select(HuffmanTree &HT, int i, int&s1, int&s2);

void CreatHC(int n, HuffmanCode& pHC, HuffmanTree& pHT);//编码表

#endif