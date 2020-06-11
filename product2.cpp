#include <iostream>
#include "product.h"

string tovar::getname()
{
    return name;
}

int tovar::getprice()
{
    return price;
}

string fruct::getcountry()
{
    return country;
}

int milk_production::getfat_content()
{
    return fat_content;
}

int milk_production::getvolume()
{
    return volume;
}

void tovar::setname(string name)
{
    this->name = name;
}

void tovar::setprice(int price)
{
    this->price = price;
}

void fruct::setcountry(string country)
{
    this->country = country;
}

void milk_production::setfat_content(int fat_content)
{
    this->fat_content = fat_content;
}

void milk_production::setvolume(int volume)
{
    this->volume = volume;
}

tovar& tovar::operator=(const tovar &tovar)
{
    if (this == &tovar)
        return *this;
    name = tovar.name;
    price = tovar.price;
    return *this;
}

fruct& fruct::operator=(const fruct &fruct)
{
    if (this == &fruct)
        return *this;
    country = fruct.country;
    return *this;
}

milk_production& milk_production::operator=(const milk_production &milk_production)
{
    if (this == &milk_production)
        return *this;
    fat_content = milk_production.fat_content;
    volume = milk_production.volume;
    return *this;
}

/*bakaleya& bakaleya::operator=(const bakaleya &bakaleya)
{
    if (this == &bakaleya)
        return *this;
    return *this;
}*/

std::ostream& operator<< (std::ostream &out, const tovar &tovar)
{
    out << "Name: " << tovar.name << ", " << tovar.price;
    return out;
}

std::istream& operator>> (std::istream &in, tovar &tovar)
{
    cout << "Name: ";
    in >> tovar.name;
    cout << "Price: ";
    in >> tovar.price;
    return in;
}

std::ostream& operator<< (std::ostream &out, const fruct &fruct)
{
    out << "Country: " << fruct.country;
    return out;
}

std::istream& operator>> (std::istream &in, fruct &fruct)
{
    cout << "Country: ";
    in >> fruct.country;
    return in;
}

std::ostream& operator<< (std::ostream &out, const milk_production &milk_production)
{
    out << "Fat_content: " << milk_production.fat_content << ", " << "\nVolume:" << milk_production.volume;
    return out;
}

std::istream& operator>> (std::istream &in, milk_production &milk_production)
{
    cout << "Fat content: ";
    in >> milk_production.fat_content;
    cout << "Volume: ";
    in >> milk_production.volume;
    return in;
}
