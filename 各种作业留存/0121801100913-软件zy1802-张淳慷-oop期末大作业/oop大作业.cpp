#include<iostream>
#include<cmath>
using namespace std;
const int large_ = 366;
const int small_ = 365;
const int Month_small[13] =
{ 0,31,28,31,30,31,30,31,31,30,31,30,31 };
const int Month_large[13] =
{ 0,31,29,31,30,31,30,31,31,30,31,30,31 };
class Date
{
public:
	Date();
	Date(int year, int month, int day);
	Date& operator+(int number);  //返回日期加天数number后得到的新日期
	Date& operator-(int number);//返回日期减天数number后得到的新日期
	int operator-(Date& date);//返回两日期相差的天数
	Date& operator ++();
	Date& operator--();
	void printDate();//打印日期 
	int tyear, tmonth, tday, tsum;
};

bool large_year(int thisyear)//判定闰年
{
	if (thisyear % 4 == 0 && thisyear % 100 != 0 || thisyear % 400 == 0)
		return true;
	else
		return false;
}
Date::Date()
{
	this->tday = 0;
	this->tmonth = 0;
	this->tyear = 0;
	this->tsum = 0;
}//初始化
Date::Date(int year, int month, int day)
{
	this->tyear = year;
	this->tmonth = month;
	this->tday = day;
	this->tsum = 0;
	if (large_year(this->tyear))
	{
		for (int i = 1; i < month; i++)
			this->tsum += Month_large[i];
		this->tsum += day;
	}
	else
	{
		for (int i = 1; i < month; i++)
			this->tsum += Month_small[i];
		this->tsum += day;
	}//得到距离总天数

}

//重载+号
Date& Date::operator+(int number)
{
	int year = this->tyear;
	int sum = this->tsum;
	sum += number;//得到加上日期之后的总天数
	int i = 1;
	if (large_year(this->tyear))
	{
		if (sum >= large_)
		{
			int j = sum / small_;//判断年数
			if (sum == large_)
				j = 1;
			for (int i = 0; i < j; i++)
			{
				if (large_year(year + i))
					sum -= large_;
				else
					sum -= small_;
			}
			year += j;
		}
	}
	else
	{
		if (sum >= small_)
		{
			int j = sum / small_;//判断年数
			for (int i = 0; i < j; i++)
			{
				if (large_year(year + i))
					sum -= large_;
				else
					sum -= small_;
			}
			year += j;
		}
	}
	if (large_year(year))//判断月与日
	{
		while (sum > 0)
			sum -= Month_large[i++];
		i--;
		if (i == 0 && sum + Month_large[i] == 0) {
			year--;
			Date temp(year, 12, 31);
			return temp;
		}
		else {
			Date temp(year, i, sum + Month_large[i]);
			return temp;
		}
	}
	else
	{
		while (sum > 0)
			sum -= Month_small[i++];
		i--;
		if (i == 0 && sum + Month_large[i] == 0) {
			year--;
			Date temp(year, 12, 31);
			return temp;
		}
		else {
			Date temp(year, i, sum + Month_large[i]);
			return temp;
		}
	}
}
Date& Date::operator-(int number)
{
	int sum = this->tsum;
	int year = this->tyear;
	int True_sum = number >= sum ? (number - sum) : (sum - number);
	int i = 1, K = True_sum / small_;
	if (this->tmonth == 12) {
		if (this->tday == 30 && number == 0) {
			Date temp(year, 12, 30);
			return temp;
		}
		if (this->tday == 31) {
			if (number == 0) {
				Date temp(year, 12, 31);
				return temp;
			}
			if (number == 1) {
				Date temp(year, 12, 30);
				return temp;
			}


		}
	}
	for (int i = 1; i <= K; i++)//判断大体上少了几年，明显看出如果sum更大该循环不会执行，比实际答案年份大一年
		if (large_year(year - i))
		{
			True_sum -= large_;
		}
		else
		{
			True_sum -= small_;
		}
	year -= (number - sum) / small_;
	if (tsum <= number)
	{
		year--;
		if (large_year(year))
			sum = large_ - True_sum;
		else
			sum = small_ - True_sum;
	}
	else
	{
		sum = True_sum;
	}

	if (large_year(year))//判断月数
	{
		while (sum > 0)
			sum -= Month_large[i++];
		i--;
		Date temp(year, i, sum + Month_large[i]);
		return temp;
	}
	else
	{
		while (sum > 0)
			sum -= Month_small[i++];
		i--;
		Date temp(year, i, sum + Month_small[i]);
		return temp;
	}
}
int Date::operator-(Date& date)
{
	int sum_date = 0;
	int i = 0, j = abs(date.tyear - this->tyear);//判断年差
	int year = date.tyear;
	if (large_year(date.tyear))
	{
		if (j==0) {
			sum_date += (this->tsum - date.tsum);//判断假设地址恰相隔一年下时的日期差
		}
		else {
			sum_date += (this->tsum - date.tsum+large_);
		}
	}
	else
	{
		if (j==0) {
			sum_date += (this->tsum - date.tsum);//判断假设地址恰相隔一年下时的日期差
		}
		else {
			sum_date += (this->tsum - date.tsum+small_);
		}
	}
	if (j)
		for (i = 1; i < j; i++)
		{
			if (large_year(year + i))//根据年差换算成日期差
				sum_date += large_;
			else
				sum_date += small_;
		}

	return sum_date;
}
Date& Date::operator++() {

	*(this) = *(this) + 1;
	return *(this);
}
Date& Date::operator--() {
	(*this) = (*this) - 1;
	return *(this);
}
void Date::printDate()
{
	cout << this->tyear << "." << this->tmonth << "." << this->tday << endl;

}

class time :public Date {
	int thour, tmin, tsec;
	time(int year, int month, int day, int hour, int min, int sec) :Date(year, month, day) {
		thour = hour;
		tmin = min;
		tsec = sec;
	}
};
int main()
{
	int x, y, z, m, n;
	cout << "请输入你想要的年月日：";
	cin >> x >> y >> z;
	Date Day1(x, y, z);
	cout << "Day1:";
	Day1.printDate();
	Date Day2;
	cout << "请输入你想要增加的天数：";
	cin >> m;
	Day2 = Day1 + m;
	cout << "Day2:" ;
	Day2.printDate();
	int minus1 = Day2 - Day1;
	cout << "Day1 Day2相差：" ;
	cout << minus1<<endl;
	Date Day3;
	cout << "请输入你想要减少的天数：";
	cin >> n;
	Day3 = Day1 - n;
	cout << "Day3:" ;
	Day3.printDate();
	int minus2 = Day1 - Day3;
	cout << "Day1 Day3相差：" ;
	cout << minus2 << endl;
	int minus3 = Day2 - Day3;
	cout << "Day2 Day3相差：" ;
	cout << minus3;
	return 0;
}

