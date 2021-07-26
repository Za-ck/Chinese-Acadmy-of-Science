#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include"menu.h"


int main() 
{
	int nSelection = -1;
	printf("欢迎进入计费管理系统\n");
	printf("\n");
	do {
		outputMenu();
		nSelection = -1;
		scanf("%d", &nSelection);
		switch (nSelection) {
		case 1://添加卡
			add();
			break;
		case 2:
			query();
			break;
		case 3:
			printf("--------上 机--------\n");
     		break;
		case 4:
			printf("--------下 机--------\n");
			break;
		case 5:
			printf("--------充 值--------\n");
			break;
		case 6:
			printf("--------退 费--------\n");
			break;
		case 7:
			printf("--------查询统计--------\n");
			break;
		case 8:
			printf("--------注销卡--------\n");
			break;
		case 0:
			exitApp();
			break;
		default:
			printf("输入的菜单编号错误！\n");
			break;
		}
		printf("\n");
	} while (nSelection != 0);

	return 0;
}