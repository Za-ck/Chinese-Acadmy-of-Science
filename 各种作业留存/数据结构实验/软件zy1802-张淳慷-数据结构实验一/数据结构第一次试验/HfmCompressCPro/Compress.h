#ifndef COMPRESS_H
#define COMPRESS_H
//typedef char **HuffmanCode;

//文件头
struct HEAD
{
	char type[4];
	int length;
	int weight[256];
};

int Uncompress(const char* filename, const char* outputfile);

//实现文件压缩
int Compress(const char *pFilename);

//读取源文件和初始化头文件的信息
int InitHead(const char * pFilname, HEAD & sHead);

//利用Huffman编码 实现压缩编码
int Encode(const char *, char**, char *, const int);


//将二进制字符串转换成字节
char Str2byte(const char * pBinStr);

//生成压缩文件
int WriteFile(const char * pFilename, const HEAD sHead, const char * pBuffer, const int nSize);





#endif