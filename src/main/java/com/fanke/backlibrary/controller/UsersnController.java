package com.fanke.backlibrary.controller;

import com.fanke.backlibrary.pojo.sales;
import com.fanke.backlibrary.pojo.usersn;
import com.fanke.backlibrary.service.SalesService;
import com.fanke.backlibrary.service.UsersnService;
import com.fanke.backlibrary.util.email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class UsersnController {

    @Autowired
    @Qualifier("usersnServiceImpl")
    private UsersnService usersnService;

    @Autowired
    @Qualifier("salesServiceImpl")
    private SalesService salesService;
    Logger logger = LoggerFactory.getLogger(getClass());

    // 实现跳转到登录页
    @RequestMapping(value = "login.html")
    public String login() {
        logger.info("用户跳转到登录页====================>");
        return "login";
    }

    /**
     * 页面跳转
     * @param
     * @return
     */
    @RequestMapping("{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    // 主页面
    @RequestMapping(value = "index.html")
    public String hetmain() {
        logger.info("用户跳转到主页面====================>");
        return "index";
    }

    // 实现登录
//    @RequestMapping(value = "dologin.html", method = RequestMethod.POST)
//    public String doLogin(String userName, String userPwd, HttpSession session,
//                          HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
//        logger.info("登录中======================>");
//        usersn user=new usersn();
//        user=userService.login(userName,userPwd);
//        if (null != user) {//登陆成功
//            session.setAttribute("userId",user.getUserId());
//            attr.addFlashAttribute("u1", user);
//            model.addAttribute("user", user);
//            List<TimeTable> timeTableList=timeTableService.selectTable(0,50,null,null,null, DateTools.getWeekDate().get("mondayDate"), DateTools.getWeekDate().get("sundayDate"));
//            session.setAttribute("timeTableList",timeTableList);
//            return "index";
//        } else {
//            request.setAttribute("error", "密码错误了哦");
//            return "login";
//        }
//
//    }

    /**
     * 进入添加用户页面
     * @param usersn
     * @param model
     * @return
     */
    @RequestMapping(value="addUser.html",method=RequestMethod.GET)
    public String add(@ModelAttribute("usersn") usersn usersn, Model model){
        logger.info("进入用户添加页面");
        return "usersn-add";
    }

    /**
     * 添加用户
     *
     *
     */
//    @RequestMapping("/addUser")
//    public String addUser(usersn usersn,Model model){
//        logger.info("用户添加");
//        String pwd=user.getUserPwd();
//        user.setUserPwd(MD5Utils.MD5Encode(pwd,"utf-8"));
//        user.setUserTime(new Date());
//        int result=userService.insertUser(user);
//        if(result>=1){
//            model.addAttribute("mess", "添加成功");
//            return "admin-add";
//        }else{
//            model.addAttribute("mess", "添加失败");
//            return "admin-add";
//        }
//    }

    /**
     * 进入修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateUser.html")
    public String updateUser(@RequestParam int id, Model model){
        usersn user=new usersn();
        user=usersnService.selectUsersnById(id);
        model.addAttribute("user", user);
        return "usersn-update";
    }

    /**
     * 拉黑用户
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value="deleteUser",method=RequestMethod.POST)
    @ResponseBody
    public String modifyUserSave(int id, HttpSession session,Model model){
        logger.info("拉黑用户");
        usersn usersn=new usersn();
        usersn.setUid(id);
        usersn.setUps("已拉黑");
        int result=usersnService.modifUsersnPhone(usersn);
        logger.info("修改用户"+result);
        if(result>=1){
            return "删除成功";
        }else{
            return "删除失败";
        }

    }

    /**
     * 查询用户
     * @param model
     * @return
     */
    @RequestMapping(value="userlist.html",method = RequestMethod.GET)
    public String getUserList(Model model){
        logger.info("查询用户======");
        int result=usersnService.selectUserCount();
        model.addAttribute("userNum", result);
        List<usersn> userList=new ArrayList<usersn>();
        userList=usersnService.selectUsersn();
        model.addAttribute("userList", userList);
        model.addAttribute("userNum",result);
        return "usersn-list";
    }

    /**
     * 查询用户名称是否重复
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value="getuserName.html",method = RequestMethod.GET)
//    public String getUserName(String userName){
//        int result=userService.selectUserCount(userName);
//        if(result>=1){
//            return "nomiss";
//        }else{
//            return "miss";
//        }
//    }

    /**
     * 查询用户
     * @param model
     * @return
     */
    @RequestMapping(value="getUser.html",method = RequestMethod.GET)
    public String getUser(int id,Model model){
        usersn user=new usersn();
        user=usersnService.selectUsersnById(id);
        model.addAttribute("user", user);
        return "member-show";
    }


    /**
     * 提醒归还图书
     * @param session
     * @return
     */
    @RequestMapping(value="sendMail",method=RequestMethod.POST)
    @ResponseBody
    public String sendMail(HttpSession session,Model model){
        List<sales> salesList=new ArrayList<sales>();
        try {
            salesList=salesService.selectUps(new Date());
            for (sales sales : salesList) {
                email.sendEmail(sales.getUsersn().getUname(),sales.getDoubanbookInfo().getTitle(),sales.getUsersn().getMail());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
            return "删除成功";
    }

}
