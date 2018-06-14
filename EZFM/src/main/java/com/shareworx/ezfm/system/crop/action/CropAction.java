package com.shareworx.ezfm.system.crop.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.mail.model.AddressModel;
import com.shareworx.mail.model.MailModel;
import com.shareworx.mail.service.IMailService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.mvc.ShareworxAuthencatinException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.saas.payment.dao.PaymentDao;
import com.shareworx.ezfm.saas.payment.model.PaymentModel;
import com.shareworx.ezfm.saas.payment.service.PaymentDomainService;
import com.shareworx.ezfm.system.crop.dao.CropDao;
import com.shareworx.ezfm.system.crop.file.model.CropFileModel;
import com.shareworx.ezfm.system.crop.file.service.CropFileBusinessService;
import com.shareworx.ezfm.system.crop.model.CropModel;
import com.shareworx.ezfm.system.crop.pre.model.PreCropModel;
import com.shareworx.ezfm.system.crop.pre.service.PreCropBusinessService;
import com.shareworx.ezfm.system.crop.pre.vo.PreCropVo;
import com.shareworx.ezfm.system.crop.service.CropBusinessService;
import com.shareworx.ezfm.system.crop.service.CropDomainService;
import com.shareworx.ezfm.utils.ChargePaymentUtil;
import com.shareworx.ezfm.utils.ShortMessageUtil;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.areadetails.fileUpload.FilesUpload;

/**
 * 企业管理操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/system/crop")
public class CropAction {

	final static Logger log = Logger.getLogger(CropAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "system/crop";
	public final static String PRECROP_FORWARD = "system/pre_crop";
	public final static String APPLY_FORWARD = "ezfm/apply/apply";
	public final static String CHARGE_FORWARD = "ezfm/chargepayment/charge";
	public final static String PAYMENT_FORWARD = "ezfm/chargepayment/payment";
	public final static String AUTHORIZE_FORWARD = "ezfm/chargepayment/authorize";
	
	
	@Value("${system.mail.from}")
	private String host;
	
	@Autowired
	@Qualifier(CropBusinessService.ID)
	private CropBusinessService service;
	
	public void setService(CropBusinessService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(PreCropBusinessService.ID)
	private PreCropBusinessService preCropService;
	
	public void setPreCropService(PreCropBusinessService preCropService) {
		this.preCropService = preCropService;
	}
	
	@Autowired
	@Qualifier(CropFileBusinessService.ID)
	private CropFileBusinessService cropFileService;
	
	public void setCropFileService(CropFileBusinessService cropFileService) {
		this.cropFileService = cropFileService;
	}
	
	@Autowired
	@Qualifier("MailService")
	private IMailService mailService;
	
	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}
	
	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService userService;
	
	public void setUserService(YJWYUserBusinessService userService) {
		this.userService = userService;
	}
	
	@Autowired
	@Qualifier(CropDomainService.ID)
	private CropDomainService domainService;

	public void setDomainService(CropDomainService domainService) {
		this.domainService = domainService;
	}
	
	@Autowired
	@Qualifier(PaymentDomainService.ID)
	private PaymentDomainService paymentDomainService;
	
	public void setPaymentDomainService(PaymentDomainService paymentDomainService) {
		this.paymentDomainService = paymentDomainService;
	}
	
	/*@Autowired
	@Qualifier(ChargeDomainService.ID)
	private ChargeDomainService chargeDomainService;
	
	public void setChargeDomainService(ChargeDomainService chargeDomainService) {
		this.chargeDomainService = chargeDomainService;
	}*/
	
	/**
	 * 转向申请页面
	 * @return
	 */
	@RequestMapping(value="apply", method=RequestMethod.GET)
	public ModelAndView applyForward(){
		return new ModelAndView(APPLY_FORWARD);
	}
	
	@RequestMapping(value="precrop", method=RequestMethod.GET)
	public ModelAndView preForward(){
		if(!UserUtil.getCurrentPk().equals(LoginCommons.QYUSERPK)){
			return new ModelAndView();
		}
		return new ModelAndView(PRECROP_FORWARD);
	}
	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView listForward(){
		if(!UserUtil.getCurrentPk().equals(LoginCommons.QYUSERPK)){
			return new ModelAndView();
		}
		return new ModelAndView(PAGE_FORWARD);
	}
	
	/**
	 * 转向计费页面
	 * @return
	 */
	@RequestMapping(value="charge/index", method=RequestMethod.GET)
	public ModelAndView chargeForward(){
		return new ModelAndView(CHARGE_FORWARD);
	}
	
	/**
	 * 转向缴费页面
	 * @return
	 */
	@RequestMapping(value="payment/index", method=RequestMethod.GET)
	public ModelAndView paymentForward(){
		return new ModelAndView(PAYMENT_FORWARD);
	}
	
	@RequestMapping(value="authorize/index", method=RequestMethod.GET)
	public ModelAndView authorizeForward(){
		return new ModelAndView(AUTHORIZE_FORWARD);
	}
	
	/*@RequestMapping(value="charge/query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult chargeQuery(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		List<ChargeModel> models = chargeDomainService.queryListByCondition(query.where(Condition.create("pk_crop_", currUser.getPk_crop())));
		ChargeDao domainDao = SpringUtils.getBean(ChargeDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}*/
	
	@RequestMapping(value="payment/query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult paymentQuery(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		List<PaymentModel> models = paymentDomainService.queryListByCondition(query.where(Condition.create("pk_crop_", currUser.getPk_crop())));
		PaymentDao domainDao = SpringUtils.getBean(PaymentDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(String param) {
		Query query = JSON.parseObject(param, Query.class);
		CropModel[] models = service.query(query);
		CropDao domainDao = SpringUtils.getBean(CropDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 获得价格
	 * @return
	 */
	@RequestMapping(value="price", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult price(@RequestParam(required=true) long days) {
		
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		CropModel cropModel = domainService.queryById(currUser.getPk_crop());
		
		return new ModelAndResult(ChargePaymentUtil.getTimeToPrice(days*86400000l, cropModel.getCrop_chargebase() , cropModel.getAuthorize_count()));
	}
	
//	@RequestMapping(value="lastCharge", method=RequestMethod.GET)
//	public @ResponseBody ModelAndResult lastCharge() {
//		
//		YJWYUserModel currUser = UserUtil.getCurrentUser();
//		CropModel cropModel = domainService.queryById(currUser.getPk_crop());
//		AuthorizeModel authorizeModel = authorizeDomainService.queryOneByCondition(Query.from(AuthorizeModel.META_ID).where(Condition.create("pk_crop_", cropModel.getPk_crop())));
//		ChargeModel lastChargeModel = chargeDomainService.queryOneByCondition((Query.from(ChargeModel.META_ID).where(Condition.create("pk_crop_", cropModel.getPk_crop()).and(Condition.eq("remark_", "last")))));
//		
//		//获得现在日期作为结束日期
//		String endDate = DateTimeUtil.getSysdateTime();
//		
//		//判断是否已经过了截止日期
//		if(ChargePaymentUtil.getBetweenTime(endDate , cropModel.getCrop_deadline())<0l){
//			endDate = cropModel.getCrop_deadline();
//		}
//		BigDecimal price = ChargePaymentUtil.getTimeToPrice(ChargePaymentUtil.getBetweenTime(lastChargeModel.getCharge_begin() , endDate) , lastChargeModel.getCharge_base() ,authorizeModel.getAuthorize_count());
//
//		return new ModelAndResult(price);
//	}
	
	/**
	 * 获得余额
	 * @return
	 */
	@RequestMapping(value="surplusPrice", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult surplusPrice() {
		
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		CropModel cropModel = domainService.queryById(currUser.getPk_crop());
		
		return new ModelAndResult(ChargePaymentUtil.getSurplusPrice(cropModel.getCrop_deadline() , cropModel.getCrop_chargebase() , cropModel.getAuthorize_count()));
	}

//	/**
//	 * 获得所有计费单
//	 * @return
//	 */
//	@RequestMapping(value="allCharge", method=RequestMethod.GET)
//	public @ResponseBody ModelAndResult allCharge() {
//		
//		YJWYUserModel currUser = UserUtil.getCurrentUser();
//		CropModel cropModel = domainService.queryById(currUser.getPk_crop());
//		List<ChargeModel> list = chargeDomainService.queryListByCondition((Query.from(ChargeModel.META_ID).where(Condition.create("pk_crop_", cropModel.getPk_crop()).and(Condition.neq("remark_", "last")))));
//		BigDecimal price = new BigDecimal(0.0);
//		price.setScale(3, BigDecimal.ROUND_HALF_UP);
//		for(ChargeModel c : list){
//			price = price.add(c.getCharge_amount());
//		}
//		return new ModelAndResult(price);
//	}
	
	/**
	 * 获得所有缴费单
	 * @return
	 */
	@RequestMapping(value="allPayment", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult allPayment() {
		
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		CropModel cropModel = domainService.queryById(currUser.getPk_crop());
		List<PaymentModel> list = paymentDomainService.queryListByCondition((Query.from(PaymentModel.META_ID).where(Condition.create("pk_crop_", cropModel.getPk_crop()))));
		BigDecimal price = new BigDecimal(0.0);
		price.setScale(3, BigDecimal.ROUND_HALF_UP);
		for(PaymentModel p : list){
			price = price.add(p.getPayment_amount());
		}
		return new ModelAndResult(price);
	}
	
	/**
	 * 缴费操作
	 * @return
	 */
	@RequestMapping(value="payment", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult payment(@RequestParam(required=true) long days) {
		
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		
		//获得当前时间
		String dateString = DateTimeUtil.getSysdateTime();
		CropModel cropModel = domainService.queryById(currUser.getPk_crop());
//		ChargeModel lastChargeModel = chargeDomainService.queryOneByCondition(Query.from(ChargeModel.META_ID).where(Condition.create("pk_crop_", cropModel.getPk_crop()).and(Condition.create("remark_", "last"))));
		//获得剩余时间
		long surplusTime = ChargePaymentUtil.getSurplusTime(cropModel.getCrop_deadline());
		
//		//如果有最新计费单则更新最新计费单,并且新建一张计费单，一天内两次缴费则不更新
//		if(lastChargeModel!=null&&ChargePaymentUtil.getBetweenTime(lastChargeModel.getCharge_begin() , dateString)> 86400000l){
//
//			String endDate = dateString;
//			if(surplusTime<=0l){
//				endDate = cropModel.getCrop_deadline();
//			}
//			BigDecimal tempAmount = ChargePaymentUtil.getTimeToPrice(ChargePaymentUtil.getBetweenTime(lastChargeModel.getCharge_begin() , endDate) , lastChargeModel.getCharge_base() ,authorizeModel.getAuthorize_count());
//			lastChargeModel.setCharge_amount(tempAmount);
//			lastChargeModel.setCharge_end(endDate);
//			lastChargeModel.setRemark("reason:payment");
//			chargeDomainService.update(lastChargeModel);
//
//		}
		
		//更新截止日期
		cropModel.setCrop_deadline(ChargePaymentUtil.getDeadlineStr(surplusTime+days*86400000l));
		domainService.update(cropModel);
		
		//新建缴费单
		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setPk_crop(cropModel.getPk_crop());
		paymentModel.setPk_user(currUser.getPk_user());
		paymentModel.setPayment_date(dateString);
		paymentModel.setPayment_amount(ChargePaymentUtil.getTimeToPrice(days*86400000l, cropModel.getCrop_chargebase() , cropModel.getAuthorize_count()));
		paymentModel.setRemark("正常缴费");
		List<PaymentModel> rs = paymentDomainService.save(paymentModel);

//		if(lastChargeModel==null||ChargePaymentUtil.getBetweenTime(lastChargeModel.getCharge_begin() , dateString)> 86400000l){
//			//新建计费单
//			ChargeModel chargeModel = new ChargeModel();
//			chargeModel.setPk_crop(cropModel.getPk_crop());
//			chargeModel.setCharge_begin(dateString);
//			chargeModel.setCharge_base(cropModel.getCrop_chargebase());
//			chargeModel.setAuthorize_count(authorizeModel.getAuthorize_count());
//			chargeModel.setRemark("last");
//			chargeDomainService.save(chargeModel);
//		}

		return new ModelAndResult(rs);
	}
	
	@RequestMapping(value="authorizeCount", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult authorizeCount() {

		
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		CropModel cropModel = domainService.queryById(currUser.getPk_crop());
		
		return new ModelAndResult(cropModel.getAuthorize_count());
	}

	/**
	 * 获得更改最大授权数应补的差价
	 * @return
	 */
	@RequestMapping(value="authorizePrice", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult authorizePrice(@RequestParam(required=true) int count) {
		if(count<10||count>999){
			return new ModelAndResult(false,"授权数超出范围");
		}
		
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		CropModel cropModel = domainService.queryById(currUser.getPk_crop());
		
		long surplusTime = ChargePaymentUtil.getBetweenTime(DateTimeUtil.getSysdateTime(), cropModel.getCrop_deadline());
		
		if(count<cropModel.getAuthorize_count()&&surplusTime>0l){
			return new ModelAndResult(BigDecimal.ZERO);
		}
		
		return new ModelAndResult(ChargePaymentUtil.getTimeToPrice(surplusTime, cropModel.getCrop_chargebase(), count-cropModel.getAuthorize_count()));
	}
	/**
	 * 更改最大授权数
	 * @return
	 */
	@RequestMapping(value="changeAuthorize", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult changeAuthorize(@RequestParam(required=true) int count , BigDecimal amount) {
		if(count<10||count>999){
			return new ModelAndResult(false,"授权数超出范围");
		}
		
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		CropModel cropModel = domainService.queryById(currUser.getPk_crop());
		
		long surplusTime = ChargePaymentUtil.getBetweenTime(DateTimeUtil.getSysdateTime(), cropModel.getCrop_deadline());
		
		if(count<cropModel.getAuthorize_count()&&surplusTime>0l){
			return new ModelAndResult(false,"还有剩余时间，不能减少授权数");
		}
		
		//更新授权数
		cropModel.setAuthorize_count(count);
		cropModel.setAuthorize_surplus(cropModel.getAuthorize_surplus()+count-cropModel.getAuthorize_count());
		domainService.update(cropModel);
		
		if(amount.compareTo(BigDecimal.ZERO)<=0)
			return new ModelAndResult();
		
		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setPk_crop(cropModel.getPk_crop());
		paymentModel.setPk_user(currUser.getPk_user());
		paymentModel.setPayment_date(DateTimeUtil.getSysdateTime());
		paymentModel.setPayment_amount(amount);
		paymentModel.setRemark("变更授权数补差价");
		paymentDomainService.save(paymentModel);
		
		return new ModelAndResult();
	}
	
//	/**
//	 * 获得更改最大授权数后剩余的时间
//	 * @return
//	 */
//	@RequestMapping(value="authorizeTime", method=RequestMethod.GET)
//	public @ResponseBody ModelAndResult authorizeTime(@RequestParam(required=true) int count) {
//		if(count<10||count>999){
//			return new ModelAndResult(false,"授权数超出范围");
//		}
//		
//		YJWYUserModel currUser = UserUtil.getCurrentUser();
//		CropModel cropModel = domainService.queryById(currUser.getPk_crop());
//		AuthorizeModel authorizeModel = authorizeDomainService.queryOneByCondition(Query.from(AuthorizeModel.META_ID).where(Condition.create("pk_crop_", cropModel.getPk_crop())));
//
//		BigDecimal surplusPrice = ChargePaymentUtil.getSurplusPrice(cropModel.getCrop_deadline() , cropModel.getCrop_chargebase() , authorizeModel.getAuthorize_count());
//
//		//增加项目数后缩短的时间
//		long newTime = ChargePaymentUtil.getPriceToTime(surplusPrice, cropModel.getCrop_chargebase() , count);
//		
//		if(newTime<86400000l){
//			return new ModelAndResult("少于1天");
//		}
//
//		
//		return new ModelAndResult((newTime/86400000l)+"天");
//	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody CropModel model) {
		if(!doValidateUserCode(model.getCrop_code(), model.getPk_crop())){
			return new ModelAndResult(false,"企业编码重复，请换换个编码");
		}
		if(model.getCrop_deadline()!=null)
			return new ModelAndResult(false,"对不起，您发送了一个违例的请求");
		CropModel[] rs = service.save(new CropModel[]{model});
		
		return new ModelAndResult(true, "注册成功" , rs);
	}
	
	@RequestMapping(value="presave", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult presave(PreCropVo vo) {
		
		preCropService.apply(vo);

		return new ModelAndResult(true);
	}
	
	
	@RequestMapping(value="check", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult check(@RequestParam(value="pk_pre_crop",required=true) String pk_pre_crop , @RequestParam(value="type",required=true) String type) {
		
		PreCropModel preCropModel = preCropService.query(Query.from(PreCropModel.META_ID).and(Condition.create("pk_pre_crop_", pk_pre_crop)))[0];
		if(!preCropModel.getCheck_state().equals("待审核")){
			return new ModelAndResult(false,"该企业已审核！");
		}
		if(!doValidateUserCode(preCropModel.getCode() , null)&&!type.equals("2")){
			return new ModelAndResult(false,"企业编码重复！请确认该企业是否已经审核通过");
		}
		
		if(!UserUtil.getCurrentPk().equals(LoginCommons.QYUSERPK)){
			return new ModelAndResult(false,"对不起，您没有这个权限");
		}
		if(type.equals("1")){
			//新增企业
			CropModel cropModel = new CropModel();
			cropModel.setCrop_code(preCropModel.getCode());
			cropModel.setCrop_name(preCropModel.getName());
			cropModel.setCrop_address(preCropModel.getAddress());
			cropModel.setCrop_phone(preCropModel.getPhone());
			cropModel.setPostcode(preCropModel.getCode());
			cropModel.setCrop_contact(preCropModel.getContact());
			cropModel.setCrop_email(preCropModel.getEmail());
			CropModel[] cropModels = service.save(new CropModel[]{cropModel});
			

			//修改预申请表的信息
			String time = DateTimeUtil.getSysdateTime();
			preCropModel.setCheck_time(time);
			preCropModel.setCheck_state("已通过");
			preCropModel.setCheck_user(UserUtil.getCurrentUser().getPk_user());
			preCropService.update(new PreCropModel[]{preCropModel});
			
			//将文件复制一遍（包括数据库信息，oss）
			CropFileModel fileModel = cropFileService.query(Query.from(CropFileModel.META_ID).and(Condition.create("record_id", preCropModel.getPk_pre_crop())))[0];
			FilesUpload filesUpload = new FilesUpload();
			String path = filesUpload.copyUpload("yjwy_pub_crop",cropModels[0].getPk_crop() , fileModel.getFile_path());
			// 实例化文件记录实例
			CropFileModel newfileModel = new CropFileModel();
			// 填充文件名
			String[] array = path.split("/");
			newfileModel.setFile_name(array[array.length-1]);
			// 填充文件路径
			newfileModel.setFile_path(path);
			// 填充文件类型
			newfileModel.setFile_type("1");
			// 填充文件大小
			newfileModel.setFile_size(fileModel.getFile_size());
			// 文件所属表名
			newfileModel.setTable_name("yjwy_pub_crop");
			// 企业代码
			newfileModel.setCrop_code(fileModel.getCrop_code());
			// 上传时间
			newfileModel.setCreate_time(time);
			// 所属公司
			newfileModel.setPk_crop(cropModels[0].getPk_crop());
			// 记录ID
			newfileModel.setRecord_id(cropModels[0].getPk_crop());
			//保存图片信息
			cropFileService.save(new CropFileModel[]{newfileModel});
			
			//发送邮件
			MailModel message = new MailModel();
			message.setSubject("达美盛资产云服务");
			message.setHtmlText("您的申请已经通过！请等待后台工作人员将管理员账号和密码发送至您的邮箱。");
			message.setFrom(new AddressModel(host));
			message.setTo(new AddressModel[]{new AddressModel(cropModel.getCrop_email())});
			mailService.simpleMailSend(message);
			
		}
		else if(type.equals("2")){
			//修改预申请表的信息
			preCropModel.setCheck_time(DateTimeUtil.getSysdateTime());
			preCropModel.setCheck_state("已拒绝");
			preCropModel.setCheck_user(UserUtil.getCurrentUser().getPk_user());
			preCropService.update(new PreCropModel[]{preCropModel});
			//发送邮件
			MailModel message = new MailModel();
			message.setSubject("达美盛资产云服务");
			message.setHtmlText("您的申请没有通过！请确认信息正确性后再次申请。");
			message.setFrom(new AddressModel(host));
			message.setTo(new AddressModel[]{new AddressModel(preCropModel.getEmail())});
			mailService.simpleMailSend(message);
		}

		return new ModelAndResult(true);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody CropModel model) {
		if(!doValidateUserCode(model.getCrop_code(), model.getPk_crop())){
			return new ModelAndResult(false,"企业编码重复，请换换个编码");
		}
		CropModel databaseModel = domainService.queryById(model.getPk_crop());
		model.setCrop_deadline(databaseModel.getCrop_deadline());
		CropModel[] rs = service.update(new CropModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody CropModel[] models) {
		for(CropModel model : models){
			YJWYUserModel[] us = userService.query(Query.from(YJWYUserModel.META_ID).and(Condition.create("pk_crop_", model.getPk_crop())));
			if(us.length>0){
				return new ModelAndResult(false,"该企业已经被使用，不可删除");
			}
		}
		
		CropModel[] rs = service.delete(models);
		
		return new ModelAndResult(rs);
	}
	

	@RequestMapping(value="user/save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult userSave(HttpServletRequest req , @RequestBody YJWYUserModel model) {
		if(LoginCommons.QYUSERCODE.equals(model.getUser_code())){
			throw new ShareworxAuthencatinException("编码重复，请换个编码");
		}else if(LoginCommons.DMSUSERCODE.equals(model.getUser_code())){
			throw new ShareworxAuthencatinException("编码重复，请换个编码");
		}
		
		CropModel cm = domainService.queryById(model.getPk_crop());
		String tempPassword = new String(model.getPassword());
		model.setEmail(cm.getCrop_email());
		model.setPhone(cm.getCrop_phone());
		YJWYUserModel[] rs = service.userSave(new YJWYUserModel[]{model});
		
		//改变申请状态，设置计费基数
		cm.setAuthorize_count(ChargePaymentUtil.DEFAULTAUTHORIZE);
		cm.setAuthorize_current(0);
		cm.setAuthorize_surplus(cm.getAuthorize_count());
		cm.setCrop_chargebase(ChargePaymentUtil.DEFAULTBASE);
		cm.setCrop_deadline(ChargePaymentUtil.getDeadlineStr(3));
		service.update(new CropModel[]{cm});
		
		String dateString = DateTimeUtil.getSysdateTime();
		
//		//新建计费单
//		ChargeModel chargeModel = new ChargeModel();
//		chargeModel.setPk_crop(cm.getPk_crop());
//		chargeModel.setCharge_begin(dateString);
//		chargeModel.setCharge_base(cm.getCrop_chargebase());
//		chargeModel.setAuthorize_count(authorizeModel.getAuthorize_count());
//		chargeModel.setRemark("last");
//		chargeDomainService.save(chargeModel);
		
		PaymentModel paymentModel = new PaymentModel();
		paymentModel.setPk_crop(cm.getPk_crop());
		paymentModel.setPk_user(rs[0].getPk_user());
		paymentModel.setPayment_date(dateString);
		paymentModel.setPayment_amount(ChargePaymentUtil.getTimeToPrice(259200000l, ChargePaymentUtil.DEFAULTBASE,ChargePaymentUtil.DEFAULTAUTHORIZE));
		paymentModel.setRemark("试用缴费单");
		paymentDomainService.save(paymentModel);
		
		//发送邮件
		MailModel message = new MailModel();
		message.setSubject("达美盛资产云服务");
		message.setHtmlText("达美盛资产云申请通过。\n账号："+model.getUser_code()+"\n密码："+tempPassword);
		message.setFrom(new AddressModel(host));
		message.setTo(new AddressModel[]{new AddressModel(cm.getCrop_email())});
		mailService.simpleMailSend(message);
		
		//发送短信
		ShortMessageUtil.SendAccount(cm.getCrop_phone(), model.getUser_code(), tempPassword);
		
		return new ModelAndResult(rs);
	}
	
	@RequestMapping(value="user/update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult userUpdate(@RequestBody YJWYUserModel model) {		
		if(LoginCommons.QYUSERCODE.equals(model.getUser_code())){
			throw new ShareworxAuthencatinException("编码重复，请换个编码");
		}else if(LoginCommons.DMSUSERCODE.equals(model.getUser_code())){
			throw new ShareworxAuthencatinException("编码重复，请换个编码");
		}
		String tempPassword = new String(model.getPassword());
		YJWYUserModel[] rs = service.userUpdate(new YJWYUserModel[]{model});

		MailModel message = new MailModel();
		message.setSubject("达美盛资产云服务");
		message.setHtmlText("达美盛资产云账号变更。\n账号："+model.getUser_code()+"\n密码："+tempPassword);
		message.setFrom(new AddressModel(host));
		message.setTo(new AddressModel[]{new AddressModel(domainService.queryById(model.getPk_crop()).getCrop_email())});
		mailService.simpleMailSend(message);
		return new ModelAndResult(rs);
	}
	
	/**
	 * 判断企业编码是否重复
	 * @param code
	 * @param id
	 * @return
	 */
	public boolean doValidateUserCode(String code,String id){
		if(!StringUtils.isEmpty(id)){
			CropModel model = service.query(Query.from(CropModel.META_ID).and(Condition.create("pk_crop_", id)))[0];
			if(code.equals(model.getCrop_code())){
				return true;
			}
		}
		
		CropModel[] models = service.query(Query.from(CropModel.META_ID).and(Condition.create("crop_code_", code)));
		
		if(null!=models && models.length>0){
			return false;
		}
		
		return true;
	}
}