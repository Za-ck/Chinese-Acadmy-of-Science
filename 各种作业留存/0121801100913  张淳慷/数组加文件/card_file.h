#ifndef CARD_FILE_H
#define CARD_FILE_H

int saveCard(const Card* pCard, const char* pPath);
int getCardCount(const char* pPath);
int readCard(Card* pCard, const char* pPath);
int GetCardCount(const char* pPath);
int UpdateCard(const Card* pCard, const char* pPath, int nIndex);
#endif
