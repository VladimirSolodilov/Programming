#ifndef SPISOK_H_INCLUDED
#define SPISOK_H_INCLUDED

using namespace std;

struct Element
{
    int data;
    Element *next, *prev;
};

class List
{
    private:
        Element *Head, *Tail;
        int Count;
    public:
        List();
        List(const List &L);
        ~List();
        int size();
        Element *GetFirst();
        Element *GetLast();
        Element *GetElement(int position);
        void Clear();
        void InsertLast(int n);
        void InsertFirst(int n);
        void insertAtPos(int position = 0);
        void DeleteLast(int n);
        void DeleteFirst(int n);
        void DeleteAtPos(int position = 0);
        void print();
        void PrintPosition (int position);
        bool operator == (const List&);
        bool operator != (const List&);
        bool operator <= (const List&);
        bool operator >= (const List&);
        bool operator < (const List&);
        bool operator > (const List&);
        List &operator = (const List &L);
        List &operator + (const List& L);
        List operator - ();
};

#endif // SPISOK_H_INCLUDED
