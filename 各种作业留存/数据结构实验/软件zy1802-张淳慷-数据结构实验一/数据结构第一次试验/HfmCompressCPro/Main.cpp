// Main.cpp: 定义控制台应用程序的入口点。
//
#include "stdafx.h"
#include <iostream>
#include "Compress.h"
#include"Huffman.h"


using namespace std;

int main()
{
	int i = 0;
	char filename[256];
	char filename1[256];
	cout << "=========Huffman文件压缩=======" << endl;
	cout << "请输入文件名：";
	cin >> filename;
	if (Compress(filename) == 1) {
		cout << "\n压缩完成！" << endl;
		}
		else {
			cout << "\n压缩失败" << endl;
		}
    return 0;
}

