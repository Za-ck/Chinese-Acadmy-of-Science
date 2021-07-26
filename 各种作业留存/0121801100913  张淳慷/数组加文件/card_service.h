#ifndef CARD_SERVICE_H
#define CARD_SERVICE_H

int addCard(Card card);//ÉùÃ÷
Card* queryCard(const char* pName, int* a);
Card* queryCards(const char* pName, int* pIndex);//ÉùÃ÷
void ShowCardFromFile();

#endif