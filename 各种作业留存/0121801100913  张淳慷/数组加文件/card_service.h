#ifndef CARD_SERVICE_H
#define CARD_SERVICE_H

int addCard(Card card);//����
Card* queryCard(const char* pName, int* a);
Card* queryCards(const char* pName, int* pIndex);//����
void ShowCardFromFile();

#endif