package com.fanke.backlibrary.controller;

import com.fanke.backlibrary.pojo.employee;
import com.fanke.backlibrary.service.BookService;
import com.fanke.backlibrary.service.EmployeeService;
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
public class EmployeeController {

    @Autowired
    @Qualifier("employeeServiceImpl")
    private EmployeeService employeeService;
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 进入添加员工页面
     * @param employee
     * @param model
     * @return
     */
    @RequestMapping(value="addEmp.html",method= RequestMethod.GET)
    public String add(@ModelAttribute("employee")employee employee, Model model){
        logger.info("进入员工添加页面");
        return "employee-add";
    }

    /**
     * 添加员工
     * @param employee
     * @param model
     * @return
     */
    @RequestMapping("/addEmp")
    public String addUser(employee employee,Model model){
        logger.info("员工添加");
        employee.setHireDate(new Date());
        int result=employeeService.insertEmployee(employee);
        if(result>=1){
            model.addAttribute("mess", "添加成功");
        }else{
            model.addAttribute("mess", "添加失败");
        }
        return "employee-list";

    }

    /**
     * 进入员工修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateEmp.html")
    public String updateUser(@RequestParam int id, Model model){
        employee employee=new employee();
        employee=employeeService.selectEmpById(id);
        model.addAttribute("employee", employee);
        return "employee-update";
    }

    /**
     * 修改员工信息
     * @param employee
     * @param session
     * @return
     */
    @RequestMapping(value="updateEmp",method=RequestMethod.POST)
    public String modifyUserSave(employee employee, HttpSession session, Model model){
        int result=employeeService.modifyEmployee(employee);
        logger.info("修改员工"+result+"----");
        if(result>=1){
            model.addAttribute("mess", "修改成功");
        }else{
            model.addAttribute("mess", "修改失败");
        }
        return "employee-list";

    }

    /**
     * 查询员工
     * @param model
     * @return
     */
    @RequestMapping(value="emplist.html",method = RequestMethod.GET)
    public String getUserList(Model model){
        logger.info("查询员工======");
        int result=employeeService.selectEmployeeCount();
        List<employee> empList=new ArrayList<employee>();
        empList=employeeService.selectEmployees();
        model.addAttribute("empList", empList);
        model.addAttribute("empNum",result);
        return "employee-list";
    }

    /**
     * 删除员工信息
     * @param session
     * @return
     */
    @RequestMapping(value="removeEmp",method=RequestMethod.POST)
    @ResponseBody
    public String modifyClas(HttpSession session, Model model,int id){
        logger.info("删除的id"+id);
        employee employee=new employee();
        employee.setEmpId(id);
        employee.setUps("已删除");
        int result=employeeService.modifyEmployee(employee);
        logger.info("修改课程"+result);
        if(result>=1){
            return "删除成功";
        }else{
            return "删除失败";
        }

    }
}
