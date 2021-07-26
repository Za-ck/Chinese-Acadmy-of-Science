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
	Date& operator+(int number);  //�������ڼ�����number��õ���������
	Date& operator-(int number);//�������ڼ�����number��õ���������
	int operator-(Date& date);//������������������
	Date& operator ++();
	Date& operator--();
	void printDate();//��ӡ���� 
	int tyear, tmonth, tday, tsum;
};

bool large_year(int thisyear)//�ж�����
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
}//��ʼ��
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
	}//�õ�����������

}

//����+��
Date& Date::operator+(int number)
{
	int year = this->tyear;
	int sum = this->tsum;
	sum += number;//�õ���������֮���������
	int i = 1;
	if (large_year(this->tyear))
	{
		if (sum >= large_)
		{
			int j = sum / small_;//�ж�����
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
			int j = sum / small_;//�ж�����
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
	if (large_year(year))//�ж�������
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
	for (int i = 1; i <= K; i++)//�жϴ��������˼��꣬���Կ������sum�����ѭ������ִ�У���ʵ�ʴ���ݴ�һ��
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

	if (large_year(year))//�ж�����
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
	int i = 0, j = abs(date.tyear - this->tyear);//�ж����
	int year = date.tyear;
	if (large_year(date.tyear))
	{
		if (j==0) {
			sum_date += (this->tsum - date.tsum);//�жϼ����ַǡ���һ����ʱ�����ڲ�
		}
		else {
			sum_date += (this->tsum - date.tsum+large_);
		}
	}
	else
	{
		if (j==0) {
			sum_date += (this->tsum - date.tsum);//�жϼ����ַǡ���һ����ʱ�����ڲ�
		}
		else {
			sum_date += (this->tsum - date.tsum+small_);
		}
	}
	if (j)
		for (i = 1; i < j; i++)
		{
			if (large_year(year + i))//������������ڲ�
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
	cout << "����������Ҫ�������գ�";
	cin >> x >> y >> z;
	Date Day1(x, y, z);
	cout << "Day1:";
	Day1.printDate();
	Date Day2;
	cout << "����������Ҫ���ӵ�������";
	cin >> m;
	Day2 = Day1 + m;
	cout << "Day2:" ;
	Day2.printDate();
	int minus1 = Day2 - Day1;
	cout << "Day1 Day2��" ;
	cout << minus1<<endl;
	Date Day3;
	cout << "����������Ҫ���ٵ�������";
	cin >> n;
	Day3 = Day1 - n;
	cout << "Day3:" ;
	Day3.printDate();
	int minus2 = Day1 - Day3;
	cout << "Day1 Day3��" ;
	cout << minus2 << endl;
	int minus3 = Day2 - Day3;
	cout << "Day2 Day3��" ;
	cout << minus3;
	return 0;
}

