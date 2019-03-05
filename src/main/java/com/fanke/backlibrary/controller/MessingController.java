package com.fanke.backlibrary.controller;

import com.fanke.backlibrary.pojo.messing;
import com.fanke.backlibrary.service.MessingService;
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
public class MessingController {

    @Autowired
    @Qualifier("messingServiceImpl")
    private MessingService messingService;
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 进入添加公告页面
     * @param messing
     * @param model
     * @return
     */
    @RequestMapping(value="addMess.html",method= RequestMethod.GET)
    public String add(@ModelAttribute("messing")messing messing, Model model){
        logger.info("进入作者添加页面");
        return "mess-add";
    }

    /**
     * 添加公告
     * @param messing
     * @param model
     * @return
     */
    @RequestMapping("/addMess")
    public String addUser(messing messing, Model model){
        logger.info("公告添加");
        messing.setMesTime(new Date());
        messing.setMesBy("管理员");
        int result=messingService.insertMess(messing);
        if(result>=1){
            model.addAttribute("mess", "添加成功");
        }else{
            model.addAttribute("mess", "添加失败");
        }
        return "mess-list";

    }

    /**
     * 进入公告修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateMess.html")
    public String updateUser(@RequestParam int id, Model model){
        messing messing=new messing();
        messing=messingService.selectMessById(id);
        model.addAttribute("messing", messing);
        return "mess-update";
    }

    /**
     * 修改公告信息
     * @param messing
     * @param session
     * @return
     */
    @RequestMapping(value="updateMess",method=RequestMethod.POST)
    public String modifyUserSave(messing messing, HttpSession session, Model model){
        int result=messingService.updateMess(messing);
        logger.info("修改公告"+result+"----");
        if(result>=1){
            model.addAttribute("mess", "修改成功");
        }else{
            model.addAttribute("mess", "修改失败");
        }
        return "mess-list";

    }

    /**
     * 查询公告
     * @param model
     * @return
     */
    @RequestMapping(value="messlist.html",method = RequestMethod.GET)
    public String getUserList(Model model){
        logger.info("查询公告======");
        int result=messingService.selectCount();
        List<messing> messList=new ArrayList<messing>();
        messList=messingService.selectAll();
        model.addAttribute("messList", messList);
        model.addAttribute("messNum",result);
        return "mess-list";
    }

    /**
     * 删除员工信息
     * @param session
     * @return
     */
//    @RequestMapping(value="removeAuth",method=RequestMethod.POST)
//    @ResponseBody
//    public String modifyClas(HttpSession session, Model model,int id){
//        logger.info("删除的id"+id);
//        authors authors=new authors();
//        authors.setAuId(id);
//        authors.setContract(0);
//        int result=authorService.updateAuthor(authors);
//        logger.info("修改作者"+result);
//        if(result>=1){
//            return "删除成功";
//        }else{
//            return "删除失败";
//        }
//
//    }
}
