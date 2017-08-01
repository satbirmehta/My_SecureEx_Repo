package securefxPkg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
	
	private static boolean edit;
	private static boolean isLoggedIn;
	private static int id;

    @Autowired
    private BookRepository bookRepository;

    
    //  link /list brought control here and will take it to listbooks.html
    @RequestMapping("/list")
    public String listBooks(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "listbooks";
    }
    
    
    @GetMapping("/add")
    public String addBook(Model model)
    {
    	Book book = new Book();
        model.addAttribute("book", book);
        return "bookform";
    }
    
    
    @PostMapping("/add")
    public String processBook(@ModelAttribute Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors())
        
        {
            return "bookform";
        }
        
        if(edit)
        {
        	book.setId(id);
        	edit = false;
        }
        bookRepository.save(book);
        return "redirect:/books/list";
    }
    
    
    
    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id")int bookid, Model model)
    {
        Book book = bookRepository.findOneById(bookid);
        model.addAttribute("book", book);
        id = bookid;
        edit = true;
        return "bookform";
    }
    
    
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id")int bookid, Model model){
        bookRepository.delete(bookid);
       // model.addAttribute("books", bookRepository.findAll());
       // return "listbooks";
        return "redirect:/books/list";
    }
}