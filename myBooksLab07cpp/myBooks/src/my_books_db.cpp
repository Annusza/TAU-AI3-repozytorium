#include "my_books_db.hpp"
#include "book.hpp"

using namespace std;

list<book_b> MyBooksDatabase::getAllBooks()
{
    list<book_b> result;
    for (auto &e : myBooksDatabase)
    {
        result.push_back(e.second);
    }
    return result;
}

void MyBooksDatabase::addBook(const book_b &b)
{
    myBooksDatabase[b.id] = b;
}

book_b MyBooksDatabase::getBookById(const int id)
{
    for (auto &e : myBooksDatabase)
    {
        if (e.second.id == id)
        {
            return e.second;
        }
    }
}

void MyBooksDatabase::deleteBook(const int id)
{
    if (myBooksDatabase.count(id) == 0)
    {
        throw invalid_argument("book not found");
    }
    myBooksDatabase.erase(id);
}
