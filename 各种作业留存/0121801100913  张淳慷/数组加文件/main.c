#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include"menu.h"


int main() 
{
	int nSelection = -1;
	printf("��ӭ����Ʒѹ���ϵͳ\n");
	printf("\n");
	do {
		outputMenu();
		nSelection = -1;
		scanf("%d", &nSelection);
		switch (nSelection) {
		case 1://��ӿ�
			add();
			break;
		case 2:
			query();
			break;
		case 3:
			printf("--------�� ��--------\n");
     		break;
		case 4:
			printf("--------�� ��--------\n");
			break;
		case 5:
			printf("--------�� ֵ--------\n");
			break;
		case 6:
			printf("--------�� ��--------\n");
			break;
		case 7:
			printf("--------��ѯͳ��--------\n");
			break;
		case 8:
			printf("--------ע����--------\n");
			break;
		case 0:
			exitApp();
			break;
		default:
			printf("����Ĳ˵���Ŵ���\n");
			break;
		}
		printf("\n");
	} while (nSelection != 0);

	return 0;
}