package com.fanke.backlibrary.controller;

import com.fanke.backlibrary.pojo.authors;
import com.fanke.backlibrary.pojo.employee;
import com.fanke.backlibrary.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class AuthorController {

    @Autowired
    @Qualifier("authorServiceImpl")
    private AuthorService authorService;
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 进入添加作者页面
     * @param authors
     * @param model
     * @return
     */
    @RequestMapping(value="addAuth.html",method= RequestMethod.GET)
    public String add(@ModelAttribute("authors")authors authors, Model model){
        logger.info("进入作者添加页面");
        return "author-add";
    }

    /**
     * 添加作者
     * @param authors
     * @param model
     * @return
     */
    @RequestMapping("/addAuth")
    public String addUser(authors authors, Model model){
        logger.info("作者添加");
        int result=authorService.insertAuthor(authors);
        if(result>=1){
            model.addAttribute("mess", "添加成功");
        }else{
            model.addAttribute("mess", "添加失败");
        }
        return "author-list";

    }

    /**
     * 进入作者修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateAuth.html")
    public String updateUser(@RequestParam int id, Model model){
        authors authors=new authors();
        authors=authorService.selectAuthorById(id);
        model.addAttribute("authors", authors);
        return "author-update";
    }

    /**
     * 修改作者信息
     * @param authors
     * @param session
     * @return
     */
    @RequestMapping(value="updateAuth",method=RequestMethod.POST)
    public String modifyUserSave(authors authors, HttpSession session, Model model){
        int result=authorService.updateAuthor(authors);
        authors.setContract(1);
        logger.info("修改员工"+result+"----");
        if(result>=1){
            model.addAttribute("mess", "修改成功");
        }else{
            model.addAttribute("mess", "修改失败");
        }
        return "author-list";

    }

    /**
     * 查询作者
     * @param model
     * @return
     */
    @RequestMapping(value="authlist.html",method = RequestMethod.GET)
    public String getUserList(Model model){
        logger.info("查询作者======");
        int result=authorService.selectAuCount();
        List<authors> authList=new ArrayList<authors>();
        authList=authorService.selectAll();
        model.addAttribute("authList", authList);
        model.addAttribute("authNum",result);
        return "author-list";
    }

    /**
     * 删除员工信息
     * @param session
     * @return
     */
    @RequestMapping(value="removeAuth",method=RequestMethod.POST)
    @ResponseBody
    public String modifyClas(HttpSession session, Model model,int id){
        logger.info("删除的id"+id);
        authors authors=new authors();
        authors.setAuId(id);
        authors.setContract(0);
        int result=authorService.updateAuthor(authors);
        logger.info("修改作者"+result);
        if(result>=1){
            return "删除成功";
        }else{
            return "删除失败";
        }

    }
}
