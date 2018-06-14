package com.shareworx.ezfm.device.list.action;

import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService;
import com.shareworx.ezfm.problem.details.vo.ProblemDetailsVo;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



/**
 * Created by zhi.zhang on 2017/6/20.
 */
@Controller
@RequestMapping("ezfm/mobile")
public class MobileController {
    final static Logger log = Logger.getLogger(YJWYListAction.class);

    /** 跳转页面 */
    public final static String PAGE_FORWARD = "ezfm/device/mobile/index";

    /**
     * 成功后的页面
     */
    public final static String PAGE_SUCCESS = "ezfm/device/mobile/success";

    @Autowired
    @Qualifier(YJWYProblemDetailsBusinessService.ID)
    private YJWYProblemDetailsBusinessService service;
    @Autowired
    @Qualifier(YJWYEqDomainService.ID)
    YJWYEqDomainService eqDomainService;
    @Autowired
    @Qualifier(YJWYRoomDomainService.ID)
    YJWYRoomDomainService roomDomainService;
    /**
     * 转向列表页面
     *
     * @return
     */
    @RequestMapping(value = "/{eq_id}", method = RequestMethod.GET)
    public ModelAndView listForward(@PathVariable String eq_id) {
        ModelAndView mv = new ModelAndView(PAGE_FORWARD);
        YJWYEqModel yjwyEqModel = eqDomainService.queryById(eq_id);
        if(yjwyEqModel == null){
            return mv;
        }
        mv.addObject("name",yjwyEqModel.getName());
        mv.addObject("service_dept",yjwyEqModel.getService_dept());
        YJWYRoomModel yjwyRoomModel = roomDomainService.queryById(yjwyEqModel.getRm_id());
        if(yjwyRoomModel != null){
            mv.addObject("room",yjwyRoomModel.getName());
        }
        Query query = new Query();
        query.and(Condition.create("fk_repair_equipment",eq_id));
        query.and(Condition.create("state","1"));
        query.addFrom(YJWYProblemDetailsModel.META_ID);
        YJWYProblemDetailsModel[] query1 = service.query(query);
        if(query1.length >0){
            mv.addObject("hasProblem","0");
        }else{
            mv.addObject("hasProblem","1");
        }

        mv.addObject("eq_id",eq_id);
        return mv;
    }

    @RequestMapping(value = "/save/{eq_id}", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndResult save(@PathVariable String eq_id,ProblemDetailsVo vo) {

        YJWYProblemDetailsModel[] yjwyProblemDetailsModels = service.saveDetailsByEq(vo);
       return new ModelAndResult();
    }
    @RequestMapping(value = "/upload/success", method = RequestMethod.GET)
    public ModelAndView success(ProblemDetailsVo vo) {
       return new ModelAndView(PAGE_SUCCESS);
    }






}
