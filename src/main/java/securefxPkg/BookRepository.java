package securefxPkg;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer>{

public Book findOneById(int id);
}
