#ifndef __MY_BOOKS_DATABASE_HPP__
#define __MY_BOOKS_DATABASE_HPP__

#include "book.hpp"
#include <map>
#include <list>

using namespace std;

class MyBooksDatabase
{
protected:
    map<int, book_b> myBooksDatabase;

public:
    list<book_b> getAllBooks();
    void addBook(const book_b &b);
    book_b getBookById(const int id);
    void deleteBook(const int id);
};

#endif