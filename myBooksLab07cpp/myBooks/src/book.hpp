#ifndef __BOOK__
#define __BOOK__

#include <string>
#include <iostream>

using namespace std;

struct book_b
{
    int id;
    string title;
    int dateOfPublic;
};

inline bool operator==(const book_b &b1, const book_b &b2)
{
    return (b1.id == b2.id) && (b1.title == b2.title) && (b1.dateOfPublic == b2.dateOfPublic);
}

inline ostream &operator<<(std::ostream &out, const book_b &b)
{
    out << "{" << b.id << ", " << b.title << ", " << b.dateOfPublic << "}";
    return out;
}

#endif