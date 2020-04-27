#include <iostream>
#include "product.h"
#include "product2.h" <CodeBlocks_project_file>

tovar::tovar()
{
    name = "Null";
    price = 0;
}

tovar::tovar(string name, int price)
{
    this->name = name;
    this->price = price;
}

tovar::tovar(const tovar &t)
{
    this->name = t.name;
    this->price = t.price;
}

fruct::fruct()
{
    country = "Null";
}

fruct::fruct(string country)
{
    this->country = country;
}

fruct::fruct(const fruct &f)
{
    this->country = f.country;
}

milk_production::milk_production()
{
    fat_content = 0;
    volume = 0;
}

milk_production::milk_production(int fat_content, int volume)
{
    this->fat_content = fat_content;
    this->volume = volume;
}

milk_production::milk_production(const milk_production &f)
{
    this->fat_content = f.fat_content;
    this->volume = f.volume;
}

void tovar::print()
{
    cout << "Name: " << name << "\nPrice: " << price << endl;
}

void fruct::print()
{
    cout << "Country: " << country << endl;
}

void milk_production::print()
{
    cout << "Fat_content: " << fat_content << "\nVolume: " << volume << endl;
}

void bakaleya::print()
{
    cout << "Bakaleya" << endl;
}
