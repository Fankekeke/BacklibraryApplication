package com.fanke.backlibrary.controller;

import com.fanke.backlibrary.pojo.doubanbookInfo;
import com.fanke.backlibrary.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 进入添加图书页面
     * @param doubanbookInfo
     * @param model
     * @return
     */
    @RequestMapping(value="addBook.html",method= RequestMethod.GET)
    public String add(@ModelAttribute("doubanbookInfo")doubanbookInfo doubanbookInfo, Model model){
        logger.info("进入图书添加页面");
        return "book-add";
    }

    /**
     * 添加图书
     * @param doubanbookInfo
     * @param model
     * @return
     */
    @RequestMapping("/addBook")
    public String addUser(doubanbookInfo doubanbookInfo,Model model){
        logger.info("图书添加");
        int result=bookService.insertBook(doubanbookInfo);
        if(result>=1){
            model.addAttribute("mess", "添加成功");
        }else{
            model.addAttribute("mess", "添加失败");
        }
        return "book-list";

    }

    /**
     * 进入图书修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateBool.html")
    public String updateUser(@RequestParam String id, Model model){
        doubanbookInfo doubanbookInfo=new doubanbookInfo();
        doubanbookInfo=bookService.selectBookByIsbn(id);
        model.addAttribute("doubanbookInfo", doubanbookInfo);
        return "book-update";
    }

    /**
     * 修改图书信息
     * @param doubanbookInfo
     * @param session
     * @return
     */
    @RequestMapping(value="updateBook",method=RequestMethod.POST)
    public String modifyUserSave(doubanbookInfo doubanbookInfo, HttpSession session, Model model){
        int result=bookService.modifBook(doubanbookInfo);
        logger.info("修改课程"+result+"----");
        if(result>=1){
            model.addAttribute("mess", "修改成功");
        }else{
            model.addAttribute("mess", "修改失败");
        }
        return "book-list";

    }

    /**
     * 查询图书
     * @param model
     * @return
     */
    @RequestMapping(value="booklist.html",method = RequestMethod.GET)
    public String getUserList(Model model){
        logger.info("查询图书======");
        int result=bookService.selectBookCount();
        HashOperations<String,String,String> hashOperations = redisTemplate.opsForHash();
        List<doubanbookInfo> bookList =bookService.selectAll();
        bookList= (List<doubanbookInfo>) this.redisTemplate.opsForValue().get("bookList");
        model.addAttribute("bookList", bookList);
        model.addAttribute("bookNum",result);
        return "book-list";
    }

    /**
     * 删除图书信息
     * @param session
     * @return
     */
    @RequestMapping(value="removeBook",method=RequestMethod.POST)
    @ResponseBody
    public String modifyClas(HttpSession session, Model model,String id){
        logger.info("删除的id"+id);
        doubanbookInfo doubanbookInfo=new doubanbookInfo();
        doubanbookInfo.setIsbn(id);
        doubanbookInfo.setUps("已删除");
        int result=bookService.deleteBook();
        logger.info("修改课程"+result);
        if(result>=1){
            return "删除成功";
        }else{
            return "删除失败";
        }

    }
}
