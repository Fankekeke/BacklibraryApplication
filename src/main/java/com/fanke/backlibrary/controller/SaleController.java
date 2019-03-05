package com.fanke.backlibrary.controller;

import com.alibaba.fastjson.JSON;
import com.fanke.backlibrary.pojo.doubanbookInfo;
import com.fanke.backlibrary.pojo.sales;
import com.fanke.backlibrary.pojo.usersn;
import com.fanke.backlibrary.service.BookService;
import com.fanke.backlibrary.service.MessingService;
import com.fanke.backlibrary.service.SalesService;
import com.fanke.backlibrary.service.UsersnService;
import com.fanke.backlibrary.util.DateTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
@Scope("singleton")
public class SaleController {

    @Autowired
    @Qualifier("salesServiceImpl")
    private SalesService salesService;
    @Autowired
    @Qualifier("usersnServiceImpl")
    private UsersnService usersnService;
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;
    Logger logger = LoggerFactory.getLogger(getClass());

    usersn user=new usersn();
    doubanbookInfo doubanbook=new doubanbookInfo();

    /**
     * 进入添加订单页面
     * @param sales
     * @param model
     * @return
     */
    @RequestMapping(value="addSale.html",method= RequestMethod.GET)
    public String add(@ModelAttribute("sales")sales sales, Model model){
        logger.info("进入订单添加页面");
        return "sale-add";
    }

    /**
     * 添加订单
     * @param sales
     * @param model
     * @return
     */
    @RequestMapping("/addSale")
    public String addUser(sales sales, Model model){
        logger.info("订单添加");
        sales.setOrdDate(new Date());
        sales.setReturnDate(DateTools.getNewDate(new Date()));
        int result=salesService.insertSale(sales);
        if(result>=1){
            model.addAttribute("mess", "添加成功");
        }else{
            model.addAttribute("mess", "添加失败");
        }
        return "sale-list";

    }

    /**
     * 进入订单修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateSale.html")
    public String updateUser(@RequestParam int id, Model model){
        sales sales=new sales();
        sales=salesService.selectSaleById(id);
        model.addAttribute("sales", sales);
        return "sale-update";
    }

    /**
     * 修改订单信息
     * @param sales
     * @param session
     * @return
     */
    @RequestMapping(value="updateSale",method=RequestMethod.POST)
    public String modifyUserSave(sales sales, HttpSession session, Model model){
        int result=salesService.updateSale(sales);
        logger.info("修改订单"+result+"----");
        if(result>=1){
            model.addAttribute("mess", "修改成功");
        }else{
            model.addAttribute("mess", "修改失败");
        }
        return "sale-list";

    }

    /**
     * 查询订单
     * @param model
     * @return
     */
    @RequestMapping(value="salelist.html",method = RequestMethod.GET)
    public String getUserList(Model model){
        logger.info("查询订单======");
        int result=salesService.selectSalesCount();
        List<sales> saleList=new ArrayList<sales>();
        saleList=salesService.selectSales();
        model.addAttribute("saleList", saleList);
        model.addAttribute("saleNum",result);
        return "sale-list";
    }

    /**
     * 归还订单信息
     * @param session
     * @return
     */
    @RequestMapping(value="removeSale",method=RequestMethod.POST)
    @ResponseBody
    public String modifyClas(HttpSession session, Model model,int id){
        logger.info("删除的id"+id);
        sales sales=new sales();
        sales.setUps(0);
        sales.setSaleId(id);
        int result=salesService.updateSale(sales);
        if(result>=1){
            return "归还成功";
        }else{
            return "归还失败";
        }

    }


    /**
     * 查询用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "getuserlist.html", method = RequestMethod.GET)
    @ResponseBody
    public String getClasList(String uname,Model model) {
        List<usersn> teaList = new ArrayList<usersn>();
        teaList = usersnService.selectUserByFenye(0,50,uname,"");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div class=\"mini-panel-border mini-grid-border\" style=\"border-left: 0px; border-right: 0px;\">\n" +
                "     <div class=\"mini-panel-header\" style=\"display: none;\">\n" +
                "      <div class=\"mini-panel-header-inner\">\n" +
                "       <span class=\"mini-panel-icon mini-icon mini-iconfont \" style=\"display: none;\"></span>\n" +
                "       <div class=\"mini-panel-title\">\n" +
                "        &nbsp;\n" +
                "       </div>\n" +
                "       <div class=\"mini-tools\">\n" +
                "        <span id=\"0\" class=\"mini-icon mini-iconfont fa mini-tools-collapse \" style=\";display:none;\"></span>\n" +
                "        <span id=\"1\" class=\"mini-icon mini-iconfont fa mini-tools-close \" style=\";display:none;\"></span>\n" +
                "       </div>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"mini-panel-viewport mini-grid-viewport\" style=\"height: 263px;\">\n" +
                "      <div class=\"mini-panel-toolbar\" style=\"display: none;\"></div>\n" +
                "      <div class=\"mini-grid-columns\" style=\"display: block;\">\n" +
                "       <div class=\"mini-grid-columns-lock\" style=\"left: -10px; width: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 0px; height: auto;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-topRightCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-columns-view\" style=\"margin-left: 0px; width: auto; padding-right: 17px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"height: auto; width: 100%;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "           <td id=\"mini-8$headerCell2$1\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              用户id\n" +
                "             </div>\n" +
                "             <div id=\"1\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$2\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              用户名称\n" +
                "             </div>\n" +
                "             <div id=\"2\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$3\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              状态\n" +
                "             </div>\n" +
                "             <div id=\"3\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$4\" class=\"mini-grid-headerCell    mini-grid-bottomCell  mini-grid-rightCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              会员卡\n" +
                "             </div>\n" +
                "             <div id=\"4\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-topRightCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "      </div>\n" +
                "      <div class=\"mini-grid-filterRow\" style=\"display: none;\">\n" +
                "       <div class=\"mini-grid-filterRow-lock\" style=\"height: 100%; left: -10px; width: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"position:absolute;top:0;left:0;height:100%;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-filterRow-view\" style=\"margin-left: 0px; width: auto; padding-right: 0px; height: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"position: absolute; top: 0px; left: 0px; height: 100%; width: 631px;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "           <td id=\"mini-8$filter$1\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$2\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$3\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$4\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "      </div>\n" +
                "      <div class=\"mini-panel-body mini-grid-rows\" style=\"height: 200px;\">\n" +
                "       <div class=\"mini-grid-rows-lock\" style=\"left: -10px; width: 0px; height: 100%;\">\n" +
                "        <div class=\"mini-grid-rows-content\">\n" +
                "         <table class=\"mini-grid-table mini-grid-rowstable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 0px;\">\n" +
                "          <tbody>\n" +
                "           <tr style=\"height:1px;\">\n" +
                "            <td style=\"height:0px;width:0;\"></td>\n" +
                "            <td style=\"width:0px;\"></td>\n" +
                "           </tr>\n" +
                "          </tbody>\n" +
                "         </table>\n" +
                "        </div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-rows-view\" style=\"margin-left: 0px; width: 100%;\">\n" +
                "        <div class=\"mini-grid-rows-content\">\n" +
                "         <table class=\"mini-grid-table mini-grid-rowstable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
                "          <tbody>\n" +
                "           <tr style=\"height:0px;\">\n" +
                "            <td style=\"height:0px;width:0;\"></td>\n" +
                "            <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td style=\"width:0px;\"></td>\n" +
                "           </tr>\n" +
                "           <tr id=\"mini-8$emptytext2\" style=\"display:none;\">\n" +
                "            <td style=\"width:0\"></td>\n" +
                "            <td class=\"mini-grid-emptyText\" colspan=\"4\">没有返回的数据</td>\n" +
                "           </tr>\n" +
                "           \n");
        for (usersn usersn : teaList) {
            stringBuffer.append("           <tr class=\"mini-grid-row\" id=\"row"+usersn.getUid()+"\" style=\" \" >\n" +
                    "            <td style=\"width:0;\"></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              <div>\n" +
                    "               "+usersn.getUid()+"\n" +
                    "              </div>\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"xucc198712@qq.com\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+usersn.getUname()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+usersn.getUps()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell  mini-grid-rightCell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+usersn.getUppwer()+"\n" +
                    "             </div></td>\n" +
                    "           </tr>\n" +
                    "       \n");
        }
        stringBuffer.append(
                "          </tbody>\n" +
                        "         </table>\n" +
                        "        </div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-vscroll\" style=\"display: none;\">\n" +
                        "        <div class=\"mini-grid-vscroll-content\"></div>\n" +
                        "       </div>\n" +
                        "      </div>\n" +
                        "      <div class=\"mini-grid-summaryRow\" style=\"display: none;\">\n" +
                        "       <div class=\"mini-grid-summaryRow-lock\" style=\"left: -10px; width: 0px; height: auto;\">\n" +
                        "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                        "         <tbody>\n" +
                        "          <tr style=\"height:0px;\">\n" +
                        "           <td style=\"height:0px;width:0;\"></td>\n" +
                        "           <td style=\"width:0px;\"></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "           <td style=\"width:0;\"></td>\n" +
                        "          </tr>\n" +
                        "         </tbody>\n" +
                        "        </table>\n" +
                        "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-summaryRow-view\" style=\"margin-left: 0px; width: auto; height: auto; padding-right: 17px;\">\n" +
                        "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
                        "         <tbody>\n" +
                        "          <tr style=\"height:0px;\">\n" +
                        "           <td style=\"height:0px;width:0;\"></td>\n" +
                        "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td style=\"width:0px;\"></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "           <td style=\"width:0;\"></td>\n" +
                        "           <td id=\"mini-8$summary$1_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$2_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$3_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$4_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "          </tr>\n" +
                        "         </tbody>\n" +
                        "        </table>\n" +
                        "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "      </div>\n" +
                        "      </div>\n" +
                        "      <div class=\"mini-panel-footer\" style=\"display: none;\"></div>\n" +
                        "      <div class=\"mini-resizer-trigger\" style=\"\"></div>\n" +
                        "     </div>\n" +
                        "     <a href=\"#\" class=\"mini-grid-focus\" style=\"position: absolute; left: 423px; top: 84px; width: 0px; height: 0px; outline: none;\" hidefocus=\"\" onclick=\"return false\"></a>\n" +
                        "    </div>");

        return stringBuffer.toString();
    }



    /**
     * 查询图书
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "getbooklist.html", method = RequestMethod.GET)
    @ResponseBody
    public String getBookList(String title,Model model) {
        List<doubanbookInfo> teaList = new ArrayList<doubanbookInfo>();
        teaList = bookService.selectBookByMessFenye(0,50,title,"");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div class=\"mini-panel-border mini-grid-border\" style=\"border-left: 0px; border-right: 0px;\">\n" +
                "     <div class=\"mini-panel-header\" style=\"display: none;\">\n" +
                "      <div class=\"mini-panel-header-inner\">\n" +
                "       <span class=\"mini-panel-icon mini-icon mini-iconfont \" style=\"display: none;\"></span>\n" +
                "       <div class=\"mini-panel-title\">\n" +
                "        &nbsp;\n" +
                "       </div>\n" +
                "       <div class=\"mini-tools\">\n" +
                "        <span id=\"0\" class=\"mini-icon mini-iconfont fa mini-tools-collapse \" style=\";display:none;\"></span>\n" +
                "        <span id=\"1\" class=\"mini-icon mini-iconfont fa mini-tools-close \" style=\";display:none;\"></span>\n" +
                "       </div>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"mini-panel-viewport mini-grid-viewport\" style=\"height: 263px;\">\n" +
                "      <div class=\"mini-panel-toolbar\" style=\"display: none;\"></div>\n" +
                "      <div class=\"mini-grid-columns\" style=\"display: block;\">\n" +
                "       <div class=\"mini-grid-columns-lock\" style=\"left: -10px; width: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 0px; height: auto;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-topRightCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-columns-view\" style=\"margin-left: 0px; width: auto; padding-right: 17px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"height: auto; width: 100%;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "           <td id=\"mini-8$headerCell2$1\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              图书ISBN\n" +
                "             </div>\n" +
                "             <div id=\"1\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$2\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              图书名称\n" +
                "             </div>\n" +
                "             <div id=\"2\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$3\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              作者\n" +
                "             </div>\n" +
                "             <div id=\"3\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$4\" class=\"mini-grid-headerCell    mini-grid-bottomCell  mini-grid-rightCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              精装/简装\n" +
                "             </div>\n" +
                "             <div id=\"4\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-topRightCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "      </div>\n" +
                "      <div class=\"mini-grid-filterRow\" style=\"display: none;\">\n" +
                "       <div class=\"mini-grid-filterRow-lock\" style=\"height: 100%; left: -10px; width: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"position:absolute;top:0;left:0;height:100%;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-filterRow-view\" style=\"margin-left: 0px; width: auto; padding-right: 0px; height: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"position: absolute; top: 0px; left: 0px; height: 100%; width: 631px;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "           <td id=\"mini-8$filter$1\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$2\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$3\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$4\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "      </div>\n" +
                "      <div class=\"mini-panel-body mini-grid-rows\" style=\"height: 200px;\">\n" +
                "       <div class=\"mini-grid-rows-lock\" style=\"left: -10px; width: 0px; height: 100%;\">\n" +
                "        <div class=\"mini-grid-rows-content\">\n" +
                "         <table class=\"mini-grid-table mini-grid-rowstable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 0px;\">\n" +
                "          <tbody>\n" +
                "           <tr style=\"height:1px;\">\n" +
                "            <td style=\"height:0px;width:0;\"></td>\n" +
                "            <td style=\"width:0px;\"></td>\n" +
                "           </tr>\n" +
                "          </tbody>\n" +
                "         </table>\n" +
                "        </div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-rows-view\" style=\"margin-left: 0px; width: 100%;\">\n" +
                "        <div class=\"mini-grid-rows-content\">\n" +
                "         <table class=\"mini-grid-table mini-grid-rowstable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
                "          <tbody>\n" +
                "           <tr style=\"height:0px;\">\n" +
                "            <td style=\"height:0px;width:0;\"></td>\n" +
                "            <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td style=\"width:0px;\"></td>\n" +
                "           </tr>\n" +
                "           <tr id=\"mini-8$emptytext2\" style=\"display:none;\">\n" +
                "            <td style=\"width:0\"></td>\n" +
                "            <td class=\"mini-grid-emptyText\" colspan=\"4\">没有返回的数据</td>\n" +
                "           </tr>\n" +
                "           \n");
        for (doubanbookInfo doubanbookInfo : teaList) {
            stringBuffer.append("           <tr class=\"mini-grid-row\" id=\"row"+doubanbookInfo.getIsbn()+"\" style=\" \" >\n" +
                    "            <td style=\"width:0;\"></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              <div>\n" +
                    "               "+doubanbookInfo.getIsbn()+"\n" +
                    "              </div>\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"xucc198712@qq.com\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+doubanbookInfo.getTitle()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+doubanbookInfo.getAuthor()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell  mini-grid-rightCell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+doubanbookInfo.getBinding()+"\n" +
                    "             </div></td>\n" +
                    "           </tr>\n" +
                    "       \n");
        }
        stringBuffer.append(
                "          </tbody>\n" +
                        "         </table>\n" +
                        "        </div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-vscroll\" style=\"display: none;\">\n" +
                        "        <div class=\"mini-grid-vscroll-content\"></div>\n" +
                        "       </div>\n" +
                        "      </div>\n" +
                        "      <div class=\"mini-grid-summaryRow\" style=\"display: none;\">\n" +
                        "       <div class=\"mini-grid-summaryRow-lock\" style=\"left: -10px; width: 0px; height: auto;\">\n" +
                        "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                        "         <tbody>\n" +
                        "          <tr style=\"height:0px;\">\n" +
                        "           <td style=\"height:0px;width:0;\"></td>\n" +
                        "           <td style=\"width:0px;\"></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "           <td style=\"width:0;\"></td>\n" +
                        "          </tr>\n" +
                        "         </tbody>\n" +
                        "        </table>\n" +
                        "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-summaryRow-view\" style=\"margin-left: 0px; width: auto; height: auto; padding-right: 17px;\">\n" +
                        "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
                        "         <tbody>\n" +
                        "          <tr style=\"height:0px;\">\n" +
                        "           <td style=\"height:0px;width:0;\"></td>\n" +
                        "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td style=\"width:0px;\"></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "           <td style=\"width:0;\"></td>\n" +
                        "           <td id=\"mini-8$summary$1_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$2_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$3_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$4_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "          </tr>\n" +
                        "         </tbody>\n" +
                        "        </table>\n" +
                        "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "      </div>\n" +
                        "      </div>\n" +
                        "      <div class=\"mini-panel-footer\" style=\"display: none;\"></div>\n" +
                        "      <div class=\"mini-resizer-trigger\" style=\"\"></div>\n" +
                        "     </div>\n" +
                        "     <a href=\"#\" class=\"mini-grid-focus\" style=\"position: absolute; left: 423px; top: 84px; width: 0px; height: 0px; outline: none;\" hidefocus=\"\" onclick=\"return false\"></a>\n" +
                        "    </div>");

        return stringBuffer.toString();
    }


    @RequestMapping(value="setuser",method=RequestMethod.POST)
    @ResponseBody
    public void setuser(Integer id, HttpSession session, Model model){
        user=usersnService.selectUsersnById(id);
        logger.info("用户保存======"+user.toString());
    }

    @RequestMapping(value="getuser",method=RequestMethod.POST)
    @ResponseBody
    public String getuser(HttpSession session, Model model){
        logger.info("用户======"+user.toString());
        return JSON.toJSONString(user);
    }

    @RequestMapping(value="setbook",method=RequestMethod.POST)
    @ResponseBody
    public void setbook(String id, HttpSession session, Model model){
        doubanbook=bookService.selectBookByIsbn(id);
        logger.info("图书保存======"+doubanbook.toString());
    }

    @RequestMapping(value="getbook",method=RequestMethod.POST)
    @ResponseBody
    public String getbook(HttpSession session, Model model){
        logger.info("图书======"+doubanbook.toString());
        return JSON.toJSONString(doubanbook);
    }



}
