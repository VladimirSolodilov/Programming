#ifndef PRODUCT_H_INCLUDED
#define PRODUCT_H_INCLUDED

class tovar
{
    private:
        std::string name;
        int price;
    public:
        tovar();
        tovar(std::string name);
        ~tovar();
        tovar(const Tovar &t);


};

class fruct :: public tovar
{
    private:
        std::string country;
    public:
        fruct() : tovar();
        ~fruct() : ~tovar();
        fruct(std::string country) : tovar(std::string name);
        fruct(const Fruct &f) : tovar(const Tovar &t);

};

class milk_production :: public fruct
{
    private:
        int fat_content;
        int volume;
    public:
        milk_production() : fruct();
        milk_production(int fat_content, int volume) : fruct(std::string country);
        ~milk_production() : ~fruct();
        milk_production(const Milk_production &m) : fruct(const Fruct &f);

};

class bakaleya :: public milk_production
{
    public:
        bakaleya() : milk_production();
        ~bakaleya() : ~milk_production();
        bakaleya(const Bakaleya &p) : milk_production(const Milk_production &m);
};

#endif // PRODUCT_H_INCLUDED
