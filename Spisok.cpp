#include <iostream>
#include "Spisok.h"

List::List()
{
    Head = Tail = NULL;
    Count = 0;
}

List::List(const List &L)
{
    Head = Tail = NULL;
    Count = 0;
    Element *temp = L.Head;
    while (temp != 0)
    {
        InsertLast(temp->data);
        temp = temp->next;
    }
}

List::~List()
{
    Clear();
}

void List::InsertFirst(int n)
{
   Element *temp = new Element;
   temp->prev = 0;
   temp->data = n;
   temp->next = Head;
   if(Head != 0)
      Head->prev = temp;
   if(Count == 0)
      Head = Tail = temp;
   else
      Head = temp;
   Count++;
}

void List::InsertLast(int n)
{
   Element *temp = new Element;
   temp->next = 0;
   temp->data = n;
   temp->prev = Tail;
   if(Tail != 0)
      Tail->next = temp;
   if(Count == 0)
      Head = Tail = temp;
   else
   Tail = temp;
   Count++;
}

void List::insertAtPos(int position)
{
   if(position == 0)
   {
      cout << "Input position: ";
      cin >> position;
   }
   if(position < 1 || position > Count + 1)
   {
      cout << "Incorrect position !!!\n";
      return;
   }
    if(position == Count + 1)
   {
      int data;
      cout << "Input new number: ";
      cin >> data;
      InsertLast(data);
      return;
   }
   else if(position == 1)
   {
      int data;
      cout << "Input new number: ";
      cin >> data;
      InsertFirst(data);
      return;
   }
   int i = 1;
   Element *Ins = Head;
   while(i < position)
   {
      Ins = Ins->next;
      i++;
   }
   Element *PrevIns = Ins->prev;
   Element *temp = new Element;
   cout << "Input new number: ";
   cin >> temp->data;
   if(PrevIns != 0 && Count != 1)
      PrevIns->next = temp;
   temp->next = Ins;
   temp->prev = PrevIns;
   Ins->prev = temp;
   Count++;
}

void List::DeleteAtPos(int position)
{
   if(position == 0)
   {
       cout << "Input position: ";
       cin >> position;
   }
   if(position < 1 || position > Count)
   {
      cout << "Incorrect position !!!\n";
      return;
   }
   int i = 1;
   Element *DeleteAtPos = Head;
   while(i < position)
   {
      DeleteAtPos = DeleteAtPos->next;
      i++;
   }
   Element *PrevDel = DeleteAtPos->prev;
   Element *AfterDel = DeleteAtPos->next;
   if(PrevDel != 0 && Count != 1)
      PrevDel->next = AfterDel;
    if(AfterDel != 0 && Count != 1)
      AfterDel->prev = PrevDel;
   if(position == 1)
       Head = AfterDel;
   if(position == Count)
       Tail = PrevDel;
   delete DeleteAtPos;
   Count--;
}

void List::PrintPosition(int position)
{
   if(position < 1 || position > Count)
   {
      cout << "Incorrect position !!!\n";
      return;
   }
   Element *temp;
   if(position <= Count / 2)
   {
      temp = Head;
      int i = 1;
      while(i < position)
      {
         temp = temp->next;
         i++;
      }
   }
   else
   {
      temp = Tail;
      int i = 1;
      while(i <= Count - position)
      {
         temp = temp->prev;
         i++;
      }
   }
   cout << position << " element: ";
   cout << temp->data << endl;
}

void List::print()
{
   if(Count != 0)
   {
      Element * temp = Head;
      cout << "( ";
      while(temp->next != 0)
      {
          cout << temp->data << ", ";
          temp = temp->next;
      }
      cout << temp->data << " )\n";
   }
}

void List::Clear()
{
   while(Count != 0)
      DeleteAtPos(1);
}

int List::size()
{
    return Count;
}

Element *List::GetElement(int position)
{
   Element *temp = Head;
   if(position < 1 || position > Count)
   {
      cout << "Incorrect position !!!\n";
      return 0;
   }
   int i = 1;
   while(i < position && temp != 0)
   {
      temp = temp->next;
      i++;
   }
   if(temp == 0)
      return 0;
   else
      return temp;
}

List &List::operator = (const List &L)
{
    if(this == &L)
       return *this;
   this->~List();
   Element *temp = L.Head;
   while(temp != 0)
   {
      InsertLast(temp->data);
      temp = temp->next;
   }
   return *this;
}

List &List::operator + (const List& L)
{
   List Result(*this);
   Element *temp = L.Head;
   while(temp != 0)
   {
      Result.InsertLast(temp->data);
      temp = temp->next;
   }
   return Result;
}

bool List::operator == (const List& L)
{
   if(Count != L.Count)
      return false;
   Element *t1, *t2;
   t1 = Head;
   t2 = L.Head;
   while(t1 != 0)
   {
      if(t1->data != t2->data)
         return false;
      t1 = t1->next;
      t2 = t2->next;
  }
   return true;
}

bool List::operator != (const List& L)
{
   return !(*this == L);
}

bool List::operator >= (const List& L)
{
   if(Count > L.Count)
      return true;
   if(*this == L)
      return true;
   return false;
}

bool List::operator <= (const List& L)
{
   if(Count < L.Count)
      return true;
   if(*this == L)
       return true;
   return false;
}

bool List::operator > (const List& L)
{
   if(Count > L.Count)
     return true;
   return false;
}

bool List::operator < (const List& L)
{
   if(Count < L.Count)
       return true;
   return false;
}

List List::operator - ()
{
   List Result;
   Element *temp = Head;
   while(temp != 0)
   {
       Result.InsertFirst(temp->data);
       temp = temp->next;
   }
   return Result;
}

 Element *List::GetFirst()
{
    return Head;
}

Element *List::GetLast()
{
   return Tail;
}
