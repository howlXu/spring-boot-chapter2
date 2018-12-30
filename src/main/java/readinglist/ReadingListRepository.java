package readinglist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingListRepository extends JpaRepository<Book, Long> {
	//根据读者id找到对应的书单
	List<Book> findByReader(String reader);
}
