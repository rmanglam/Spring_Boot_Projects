package com.demo.service;


import com.demo.dao.BookRepository;
import com.demo.entites.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBook(){
        List<Book> list  =(List<Book>)this.bookRepository.findAll();
        return list;
    }



    // get single book by id
    public Book getBookById(int id){
        Book book=null;
        try {
         book =   this.bookRepository.findById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }


    public Book findBookByTitle(String title){
        Book book = null;
        try {
            book=this.bookRepository.findByTitle(title);
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return book;
    }




public Book addbook(Book b){
    Book result = bookRepository.save(b);
    return result;
    }

    public void deleteBook(int bid){
        bookRepository.deleteById(bid);
    }


    public void updateBook(Book book,int bookId){

        book.setId(bookId);
        bookRepository.save(book);
    }
}
