#include "catch.hpp"
#include "../src/my_books_db.hpp"
#include <map>
#include <list>
#include <iostream>

using namespace std;
using namespace Catch::Matchers;

class Database : public MyBooksDatabase
{
public:
    void setDb(map<int, book_b> d)
    {
        myBooksDatabase = d;
    };
};

TEST_CASE("Getting all data from the database", "[myBooksDatabase][getAllBooks]")
{
    SECTION("GetAllBooks method is present")
    {
        REQUIRE_NOTHROW([]() {MyBooksDatabase db; db.getAllBooks(); }());
    }

    SECTION("GetAllBooks method should return empy list when database is empty")
    {
        MyBooksDatabase db;
        list<book_b> result = db.getAllBooks();
        REQUIRE(result == list<book_b>{});
    }

    SECTION("GetAllBooks method should return all books from database")
    {
        Database db;
        map<int, book_b> expected =
            {
                {1, {1, "Lalka", 1995}},
                {2, {2, "Chłopi", 2000}},
                {3, {3, "Persepolis", 2005}}};
        db.setDb(expected);
        list<book_b> expected_list;
        for (auto e : expected)
        {
            expected_list.push_back(e.second);
        }
        REQUIRE(db.getAllBooks() == expected_list);
    }
}

TEST_CASE("Adding data to the database", "[myGamesDatabase][addGame]")
{
    SECTION("The database object can be created")
    {
        Database db;
        map<int, book_b> allBooks =
            {
                {1, {1, "Lalka", 19955}},
                {2, {2, "Chłopi", 2000}}};
        db.setDb(allBooks);
        list<book_b> all_books_list;
        for (auto e : allBooks)
        {
            all_books_list.push_back(e.second);
        }
        REQUIRE(db.getAllBooks() == all_books_list);

        SECTION("AddBook method should add a book to the database")
        {
            REQUIRE_NOTHROW(db.addBook({3, "Persepolis", 2005}));

            SECTION("The database should contain element with the title Persepolis")
            {
                all_books_list.push_back({3, "Persepolis", 2005});
                REQUIRE(db.getAllBooks() == all_books_list);
            }
        }
    }
}

TEST_CASE("Getting data from database by id", "[myBooksDatabase][getBookById]")
{
    SECTION("The database object can be created")
    {
        Database db;
        map<int, book_b> allBooks =
            {
                {1, {1, "Lalka", 1995}},
                {2, {2, "Chłopi", 2000}},
                {3, {3, "Persepolis", 2005}}};
        db.setDb(allBooks);

        SECTION("GetBookById method should get a game with id 2")
        {
            REQUIRE_NOTHROW(db.getBookById(2));

            SECTION("database return product with id 2")
            {
                book_b book_to_compare = {2, "Chłopi", 2000};
                REQUIRE(db.getBookById(2) == book_to_compare);
            }
        }
    }
}

SCENARIO("Deleting data from database", "[myBooksDatabase][bdd][deleteBook]")
{
    GIVEN("We have some data in database")
    {
        Database db;
        map<int, book_b> allBooks =
            {
                {1, {1, "Lalka", 1995}},
                {2, {2, "Chłopi", 2000}},
                {3, {3, "Persepolis", 2005}}};
        db.setDb(allBooks);
        list<book_b> all_books_list;
        for (auto e : allBooks)
        {
            all_books_list.push_back(e.second);
        }
        CHECK(db.getAllBooks() == all_books_list);

        WHEN("We remove book with id 2 from the database")
        {
            REQUIRE_NOTHROW(db.deleteBook(2));
            THEN("The database shouldn't contain a book with id 2")
            {
                for (auto g : db.getAllBooks())
                {
                    CHECK(g.id != 2);
                }
            }
        }

        WHEN("We try to remove book that doesn't exist in the database")
        {
            THEN("The exception should be thrown")
            {
                REQUIRE_THROWS_AS(db.deleteBook(10), invalid_argument);
            }

            THEN("The exception should contain suitable message")
            {
                REQUIRE_THROWS_WITH(db.deleteBook(10), EndsWith("not found") || StartsWith("book"));
            }
        }
    }
}
