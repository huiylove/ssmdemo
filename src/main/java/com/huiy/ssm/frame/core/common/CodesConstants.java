package com.huiy.ssm.frame.core.common;

/**
 * 类功能描述
 * CodesConstants.java
 *
 * @history 2015年3月23日 ** 创建CodesConstants.java
 * 
 * @author **
 * @version 0.1.0
 */
public class CodesConstants {
	//通用是否词典
		public enum isOky {
			YES("1"), // 是
			NO("0"); // 否
			private String value;

			public String getValue() {
				return value;
			}

			private isOky(String value) {
				this.value = value;
			}
		}
		
	 //用户状态
	public enum UserState {
		NORMAL("00"), // 00正常
		CANCEL("02"), // 02注销
		DISABLED("01"); // 01禁用
		private String value;

		public String getValue() {
			return value;
		}

		private UserState(String value) {
			this.value = value;
		}
	}
	
	//用户类别
	public enum UserType {
		TYPE_01("01"), // 云为个人用户
		TYPE_02("02"), // 机构个人用户
		TYPE_03("03"); // 海学邦个人用户
		private String value;

		public String getValue() {
			return value;
		}

		private UserType(String value) {
			this.value = value;
		}
	}
	//角色状态
	public enum RoleState  {
		NORMAL("00"), // 正常
		CANCEL("01"); // 注销
		private String value;

		public String getValue() {
			return value;
		}

		private RoleState(String value) {
			this.value = value;
		}
	}
	//角色类型
	public enum RoleType  {
		TYPE_01("01"), // 云为管理角色
		TYPE_02("02"); // 机构角色
		private String value;

		public String getValue() {
			return value;
		}

		private RoleType(String value) {
			this.value = value;
		}
	}
	//岗位状态
		public enum PozState  {
			NORMAL("00"), // 01正常
			CANCEL("01"); // 03灭失
			private String value;

			public String getValue() {
				return value;
			}

			private PozState(String value) {
				this.value = value;
			}
		}
		//岗位类别
		public enum PozType  {
			TYPE_01("01"), // 云为岗位
			TYPE_02("02"), // 机构岗位
			TYPE_03("03"); // 个人会员岗位
			private String value;

			public String getValue() {
				return value;
			}

			private PozType(String value) {
				this.value = value;
			}
		}
		//个人会员状态
		public enum P_M_STATE  {
			S_00("00"), // 01正常
			S_01("01"), // 暂停
			S_02("02"); // 注销
			private String value;

			public String getValue() {
				return value;
			}

			private P_M_STATE(String value) {
				this.value = value;
			}
		}
		        //个人会员类别  PMEMBER_TYPE GZQ151116增加数据库配置
				public enum P_M_Type  {
					TYPE_01("01"), // 平台-云为员工
					TYPE_02("02"), // 个人-hxb
					TYPE_03("03"); // 机构-员工会员
					private String value;

					public String getValue() {
						return value;
					}

					private P_M_Type(String value) {
						this.value = value;
					}
				}
				
				//个人会员身份 
				public enum P_ID_TYPE  {
					TYPE_01("01"), // 学生
					TYPE_02("02"); //老师
					private String value;

					public String getValue() {
						return value;
					}

					private P_ID_TYPE(String value) {
						this.value = value;
					}
				}
				//菜单状态
				public enum DataTypeState  {
					NORMAL("00"), // 01正常
					CANCEL("01"), // 02禁用
					DETORY("02"); // 03删除
					private String value;

					public String getValue() {
						return value;
					}

					private DataTypeState(String value) {
						this.value = value;
					}
				}
				//数据字典状态
				public enum DataDictState  {
					NORMAL("00"), // 01正常
					CANCEL("01"); // 02停用
					private String value;

					public String getValue() {
						return value;
					}

					private DataDictState(String value) {
						this.value = value;
					}
				}
				//项目大类
				public enum ProjectKind  {
					P_01("01"), // 学术研究国际化
					P_02("02"), // 教学国际化
					P_03("03"); //师资国际化
					private String value;

					public String getValue() {
						return value;
					}

					private ProjectKind(String value) {
						this.value = value;
					}
				}
				//项目小类
				public enum ProjectStyle  {
					P_0101("0101"), // 学术交流
					P_0102("0102"), // 科研项目
					P_0103("0103"), // 主题论坛
					P_0201("0201"), // 合作办学
					P_0202("0202"), // 国际交换生
					P_0203("0203"), // 短期游学
					P_0301("0301"), //教师出国
					p_0206("0206"), //YES项目
					p_0205("0205"), //121项目
					p_0208("0208"), //新YES项目
					p_0207("0207"); //新121项目
					
					private String value;

					public String getValue() {
						return value;
					}

					private ProjectStyle(String value) {
						this.value = value;
					}
				}
				//项目状态
				public enum ProjectState  {
					P_00("00"), // 待发布
					P_01("01"), // 已发布
					P_02("02"), // 暂停
					P_03("03"), // 撤销
					P_04("04"); //完成
					private String value;

					public String getValue() {
						return value;
					}

					private ProjectState(String value) {
						this.value = value;
					}
				}
				
				//项目类别
				public enum ProjectType  {
					PT_0("0"),// 院校项目
					PT_1("1"); // 自身项目
					
					private String value;

					public String getValue() {
						return value;
					}

					private ProjectType(String value) {
						this.value = value;
					}
				}
				
				//机构会员类型
				public enum C_M_TYPE  {
					C_00("00"), // 云为教育平台
					C_01("01"), // 机构教育组织会员
					C_02("02"), // 机构院校会员
					C_03("03"), // 虚拟合作机构教育组织非会员
					C_04("04"), // 虚拟合作机构院校非会员
					C_05("05"), // 个人会员虚拟机构
					C_06("06"); // 美方院校
					private String value;

					public String getValue() {
						return value;
					}

					private C_M_TYPE(String value) {
						this.value = value;
					}
				}
				//机构会员状态
				public enum C_M_STATE  {
					S_00("00"), // 正常
					S_01("01"), // 暂停
					S_03("03"); // 注销
					private String value;

					public String getValue() {
						return value;
					}

					private C_M_STATE(String value) {
						this.value = value;
					}
				}
				//登录类型
				public enum LOGIN_TYPE {
					L_0("00"), // 正常
					l_1("01"); // KEY
					private String value;

					public String getValue() {
						return value;
					}

					private LOGIN_TYPE(String value) {
						this.value = value;
					}
				}
				//菜单类型
				public enum MENU_TYPE {
					T_1("01"), // 国际教育平台
					T_2("02"); // 云为
					private String value;

					public String getValue() {
						return value;
					}

					private MENU_TYPE(String value) {
						this.value = value;
					}
				}
				
				
				//菜单状态
				public enum MENU_STATE {
					M_1("00"), // 正常
					M_2("01"); // 禁用
					private String value;

					public String getValue() {
						return value;
					}

					private MENU_STATE(String value) {
						this.value = value;
					}
				}
				//公共资料附件类别
				public enum FILE_TYPE{
					FILE_01("01"),//机构合作资料附件  文档
					FILE_02("02"),//机构图标附件     图片
					FILE_03("03"),//个人会员头像附件  图片
					FILE_04("04"),//申请报名材料附件  文档
					FILE_05("05"),//项目图标附件   图片
					FILE_06("06"),//项目报名材料附件   文档
					FILE_07("07"),//网站新闻模块附件  图片
					FILE_08("08"),//网站活动发布附件  图片
					FILE_09("09"),//网站学科建设附件  图片
					FILE_10("10"),//网站师资引进附件  图片
					FILE_11("11"),//网站游学体验附件  图片
					FILE_12("12"),//网站访问学者附件  图片
					FILE_15("15"),//公共资料附件  文档
					FILE_16("16"),//前台静态页面
					FILE_17("17"),//ckeditor组件上传 图片 -- by 许灿灿
					FILE_18("18"),//美方报名资料附件
					FILE_19("19");//课程附件

					private String value;
					
					public String getValue(){
						return value;
					}
					
					private FILE_TYPE(String value){
						this.value = value;
					}
				}
				
				//公共资料状态
				public enum PUBFILE_STATE  {
					PUB_00("00"), // 正常
					PUB_01("01"); // 失效
					private String value;

					public String getValue() {
						return value;
					}

					private PUBFILE_STATE(String value) {
						this.value = value;
					}
				}
				
				//公共资料附件小类别  各自需要加
				public enum FILE_STY{
					FILE_0201("0201"),//海学邦-机构图标附件 -首页图标
					FILE_0202("0202"),//海学邦-机构图标附件 -详细页面图标
					FILE_0203("0203"),//海学邦-机构图标附件 -2ji页面图标（学生专区 汇总）
					FILE_0204("0204"),//海学邦-机构图标附件 -3ji页面图标（学生专区-院校介绍分页）
					FILE_0301("0301"),//海学邦-个人小头贴
					FILE_0302("0302"),//海学邦-个人大头贴
					FILE_0501("0501"),//海学邦-项目图标附件-首页图标
					FILE_0502("0502"),//海学邦-项目图标附件-详细页面图标
					FILE_0503("0503"),//海学邦-项目图标附件-2ji页面图标
					FILE_0504("0504"),//海学邦-项目图标附件-3ji页面图标
					FILE_0701("0701"),//海学邦-网站新闻 -首页图标
					FILE_0702("0702"),//海学邦-网站新闻 -2ji页面图标
					FILE_0703("0703"),//海学邦-网站新闻 -3ji页面图标
					FILE_0704("0704"),//海学邦-网站新闻 -4ji页面图标
					FILE_0801("0801"),//海学邦-网站活动发布 -首页图标
					FILE_0802("0802"),//海学邦-网站活动发布 -2ji页面图标
					FILE_0803("0803"),//海学邦-网站活动发布 -3ji页面图标
					FILE_0804("0804"),//海学邦-网站活动发布 -4ji页面图标
					FILE_0901("0901"),//海学邦-网站学科建设附件 -首页图标
					FILE_0902("0902"),//海学邦-网站学科建设附件 -2ji页面图标
					FILE_0903("0903"),//海学邦-网站学科建设附件 -3ji页面图标
					FILE_0904("0904"),//海学邦-网站学科建设附件 -4ji页面图标
					FILE_1001("1001"),//海学邦-网站师资引进附件 -首页图标
					FILE_1002("1002"),//海学邦-网站师资引进附件 -2ji页面图标
					FILE_1003("1003"),//海学邦-网站师资引进附件 -3ji页面图标
					FILE_1004("1004"),//海学邦-网站师资引进附件 -4ji页面图标
					FILE_1101("1101"),//海学邦-网站游学体验附件-首页图标
					FILE_1102("1102"),//海学邦-网站游学体验附件  -2ji页面图标
					FILE_1103("1103"),//海学邦-网站游学体验附件  -3ji页面图标
					FILE_1104("1104"),//海学邦-网站游学体验附件  -4ji页面图标
					FILE_1201("1201"),//海学邦-网站访问学者附件-首页图标
					FILE_1202("1202"),//海学邦-网站访问学者附件-2ji页面图标
					FILE_1203("1203"),//海学邦-网站访问学者附件-3ji页面图标
					FILE_1204("1204");//海学邦-网站访问学者附件  -4ji页面图标
				    private String value;
					
					public String getValue(){
						return value;
					}
					
					private FILE_STY(String value){
						this.value = value;
					}
				}
				
				//网站内容类型
				public enum CONTEXT_TYPE{
					T_01("01"),//新闻
					T_02("02"),//访问学者
					T_03("03"),//活动发布
					T_04("04"),//学科建设
					T_05("05"),//师资引进
					T_06("06");//游学体验
				   
					
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private CONTEXT_TYPE(String value){
						this.value = value;
					}
				}
				//网站内容状态
				public enum CONTEXT_STATE{
					S_00("00"),//登记
					S_01("01"),//正常
					S_02("02");//取消
				   
					
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private CONTEXT_STATE(String value){
						this.value = value;
					}
				}
				//意向客户状态
				public enum HXB_CUST_STATE{
					S_00("00"),//已申请
					S_01("01"),//已联系
					S_02("02"),//已成会员
					S_03("03");//失效
				   
					
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private HXB_CUST_STATE(String value){
						this.value = value;
					}
				}
				
				//项目报名状态
				public enum PRO_APP_STATE{
					S_00("00"),//待支付
					S_01("01"),//报名中
					S_02("02"),//报名成功
					S_03("03"),//取消报名
					S_04("04"),//项目结束
					S_05("05");//报名暂存
					
				   
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private PRO_APP_STATE(String value){
						this.value = value;
					}
				}
				
				//支付订单类型  01 报名费 02 学费  03：退费
				public enum ORDER_TYPE{
				    T_01("01"),
					T_02("02"),
					T_03("03");
				   
					
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private ORDER_TYPE(String value){
						this.value = value;
					}
				}
				//支付类型  B2C’ – B2C支付  ‘B2B’ – B2B支付
				public enum PAY_TYPE{
					B2C("B2C"),
					B2B("B2B");
				   
					
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private PAY_TYPE(String value){
						this.value = value;
					}
				}
				//支付状态 00 待支付  01：成功  02：失败  03：已退回  04 :未知(支付中)
				public enum PAY_STATE{
					    S_00("00"),
					    S_01("01"),
						S_02("02"),
						S_03("03"),
					    S_04("04");
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private PAY_STATE(String value){
						this.value = value;
					}
				}
				//富友支付 订单状态
				public enum ORDER_ST{
					S_00("00"),
					  S_01("01"),
						S_02("02"),
						S_03("03"),
						S_04("04"),
					    S_05("05"),
					    S_11("11"),
					    S_18("18"),
					    S_19("19");
				   
					
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private ORDER_ST(String value){
						this.value = value;
					}
				}
			
				
				
				//流程提交类型
				public enum FLOW_POST_TYPE{
					   S_01("01"),  //下一步
					   S_02("02"),  //退回
					   S_03("03");  //收回
				   
					
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private FLOW_POST_TYPE(String value){
						this.value = value;
					}
				}
				//流程过程状态
				public enum FLOW_PRO_STATE{
					   S_00("00"),  //已认领
					   S_01("01");  //已完成
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private FLOW_PRO_STATE(String value){
						this.value = value;
					}
				}
				//流程退回模式
				public enum NOTE_R_MODE{
					   S_01("01"),  //不允许退回
					   S_02("02"),  //退回上一步
					   S_03("03"),  //退回首节点
					   S_04("04");  //退回自选节点
					private String value;
					
					public String getValue(){
						return value;
					}
					
					private NOTE_R_MODE(String value){
						this.value = value;
					}
				}
			//优化报名查询页面返回 0:报名回退;1:再次提交;2:待缴费;3:电子凭证.
			public enum QUERY_TYPE{
				   T_00("0"),  //0:报名回退
				   T_01("1"),  //1:再次提交
				   T_02("2"),  //2:待缴费
				   T_03("3");  //3:电子凭证.
				private String value;
				
				public String getValue(){
					return value;
				}
				
				private QUERY_TYPE(String value){
					this.value = value;
				}
			}
			
			public enum IS_PAY_ONLINE{
				   IS_00("0"),  //0:线下
				   IS_01("1");  //1:线上
				private String value;
				
				public String getValue(){
					return value;
				}
				
				private IS_PAY_ONLINE(String value){
					this.value = value;
				}
			}
			
			//报名状态
			public enum ALL_STATE{
					AS_01("01"),  //--已报名未支付报名费
					AS_02("02"),  //--已报名支付报名费结果未知
					AS_03("03"),  //--已报名审批退回
					AS_04("04"),  //--报名审批中
				    AS_05("05"),  //--再次提交报名材料
				    AS_06("06"),  //--报名完成确认并需支付
				    AS_07("07"),  //--报名完成确认无需支付
					AS_08("08"),  //--已报名成功待支付学费
					AS_09("09"),  //--已报名成功支付学费结果未知
					AS_10("10"),  //--已报名成功并已支付学费
					AS_11("11"),  //--已报名成功无需支付学费
				    AS_12("12"),  //--已取消报名未退报名费
					AS_13("13"),  //--已取消报名退费结果未知
					AS_14("14"),  //--已取消无需退费
					AS_15("15"),  //--已取消并已退费
					AS_16("16");  //--项目结束
				
				private String value;
				
				public String getValue(){
					return value;
				}
				
				private ALL_STATE(String value){
					this.value = value;
				}
			}
			
			//证件类型
			public enum CERT_TYPE{
					CT_01("01"),  //--身份证
					CT_02("02"),  //--学生证
					CT_03("03"),  //--军官证
					CT_04("04");  //--教师证
				   
				
				private String value;
				
				public String getValue(){
					return value;
				}
				
				private CERT_TYPE(String value){
					this.value = value;
				}
			}
			
			//是否显示机构合作关系
			public enum IS_SHOW{
					CPTION_0("0"),  //否
					CPTION_1("1");  //是
				   
				
				private String value;
				
				public String getValue(){
					return value;
				}
				
				private IS_SHOW(String value){
					this.value = value;
				}
			}
			
			//项目邀请机构状态
			public enum INVCORP_INVSTATE{
					INVSTATE_01("01"),  //邀请中
					INVSTATE_02("02"),  //同意邀请
					INVSTATE_03("03");	//拒绝邀请
				
				private String value;
				
				public String getValue(){
					return value;
				}
				
				private INVCORP_INVSTATE(String value){
					this.value = value;
				}
			}
			
			//20160926
			//节点模式
			public enum NOTE_MODE{
				MODE_01("01"),  //序列
				MODE_02("02");  //会签
				
				private String value;
				
				public String getValue(){
					return value;
				}
				
				private NOTE_MODE(String value){
					this.value = value;
				}
			}
			
}

