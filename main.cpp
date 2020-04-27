#include <iostream>
#include "product.h"
#include <locale>

using namespace std; <CodeBlocks_project_file>

int main()
{
    setlocale(LC_ALL, "Rus");
    tovar t("Apple", 20);
    fruct f("Rus");
    bakaleya b;
    milk_production m(0, 20);
    t.print();
    cout << "\n" << endl;
    f.print();
    cout << "\n" << endl;
    m.print();
    cout << "\n" << endl;
    t.print();
    cout << "\n" << endl;
    t.tovar::print();
    cout << "\n" << endl;
    f.fruct::print();
    cout << "\n" << endl;
    m.milk_production::print();
    return 0;
}
