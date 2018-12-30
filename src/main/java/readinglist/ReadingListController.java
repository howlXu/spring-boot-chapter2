package readinglist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ReadingListController {
	private ReadingListRepository readingListRepository;
	
	//自動裝配持久化對象
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}
	
	//接受get请求，并从请求中读取reader变量
	//方法形参接收reader变量,后续将使用reader变量获取的books集合放入model的books变量，通过books变量带到前端页面中
	@RequestMapping(value="/{reader}", method=RequestMethod.GET)
	public String readersBook(@PathVariable("reader") String reader, Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader);
		if(readingList!=null) {
			model.addAttribute("books", readingList);
		}
		return "readingList";
	}
	
	@RequestMapping(value="/{reader}", method=RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/{reader}";
	}
	
	
}
