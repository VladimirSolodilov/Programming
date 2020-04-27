#ifndef PRODUCT_H_INCLUDED
#define PRODUCT_H_INCLUDED<CodeBlocks_project_file>

using namespace std;

class tovar
{
    private:
        string name;
        int price;
    public:
        tovar();
        tovar(string name, int price);
        tovar(const tovar &t);
        void print();
        string getname(); int getprice();
        void setname(string name); void setprice(int price);
        tovar& operator=(const tovar &tovar);
        friend std::ostream& operator<< (std::ostream &out, const tovar &tovar);
        friend std::istream& operator>> (std::istream &in, tovar &tovar);
};

class fruct : public tovar
{
    private:
        string country;
    public:
        fruct();
        fruct(string country);
        fruct(const fruct &f);
        void print();
        string getcountry(); void setcountry(string country);
        fruct& operator=(const fruct &fruct);
        friend std::ostream& operator<< (std::ostream &out, const fruct &fruct);
        friend std::istream& operator>> (std::istream &in, fruct &fruct);
};

class milk_production : public fruct
{
    private:
        int fat_content;
        int volume;
    public:
        milk_production();
        milk_production(int fat_content, int volume);
        milk_production(const milk_production &m);
        void print();
        int getfat_content(); int getvolume();
        void setfat_content(int fat_content); void setvolume(int volume);
        milk_production& operator=(const milk_production &milk_production);
        friend std::istream& operator>> (std::istream &in, milk_production &milk_production);
        friend std::ostream& operator<< (std::ostream &out, const milk_production &milk_production);
};

class bakaleya : public milk_production
{
    public:
        void print();
        bakaleya& operator=(const bakaleya &bakaleya);
};

#endif // PRODUCT_H_INCLUDED
