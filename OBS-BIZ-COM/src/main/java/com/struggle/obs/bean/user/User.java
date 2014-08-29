package com.struggle.obs.bean.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.struggle.obs.bean.bbs.Critique;
import com.struggle.obs.bean.bbs.Reply;
import com.struggle.obs.bean.bbs.Score;
import com.struggle.obs.bean.bbs.Topic;
import com.struggle.obs.bean.credit.UserCredit;
import com.struggle.obs.syscom.bean.BaseModelSub;

/**
 * @author tangyh
 * @create 2014年8月3日 18:39:00
 * @version 0.0.1
 */
@Entity
@Table(name = "user")
public class User extends BaseModelSub implements java.io.Serializable {
	private static final long serialVersionUID = 4249137208826055737L;
	/** 登录名 */
	private String loginName;
	/** 密码 **/
	private String passWord;// 采用MD5加密
	/** 是否验证：前台用户使用，注册后需要验证才可以使用 ; 默认为false(0)：没有验证, true(1):验证 */
	private boolean validate = false;
	/** 是否为管理员帐号:默认为false(0):普通用户 , true(1)表示:管理员账户 */
	private boolean admin = false;
	/** 是否在线 */
	private boolean onLine = false;
	/** 积分 */
	private UserCredit userCredit;
	/** 基础信息 */
	private BaseProfile baseProfile;
	/** 联系方式 */
	private ContactProfile contactProfile;
	/** 工作情况 */
	private WorkProfile workProfile;
	/** 个人信息 */
	private InfoProfile infoProfile;
	/** 教育情况 */
	private EduProfile eduProfile;
	/** 统计信息 */
	private StatisticsProfile statisticsProfile;
	/** 实名认证 */
	private RealNameVerify realNameVerify;
	/** 点评 */
	private Set<Critique> critiques = new HashSet<Critique>();
	/** 评分 */
	private Set<Score> scores = new HashSet<Score>();
	/** 帖子 */
	private Set<Topic> topics = new HashSet<Topic>();
	/** 回复 */
	private Set<Reply> replys = new HashSet<Reply>();
	
	/**
	 * @return 登录名
	 */
	@Column(name = "loginName", unique = true, nullable = false, length = 20)
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            登录名
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return 密码
	 */
	@Column(name = "passWord", length = 32)
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord
	 *            密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	/**
	 * 是否在线
	 * 
	 * @return
	 */
	@Column(name = "onLine", length=1)
	public boolean isOnLine() {
		return onLine;
	}
	/**
	 * @param 是否在线
	 *            onLine
	 */
	public void setOnLine(boolean onLine) {
		this.onLine = onLine;
	}
	
	/**
	 * @return 是否验证：前台用户使用，注册后需要验证才可以使用
	 */
	@Column(name = "validate", length=1)
	public boolean isValidate() {
		return validate;
	}

	/**
	 * @param validate
	 *            是否验证：前台用户使用，注册后需要验证才可以使用
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**
	 * @return 是否为管理员帐号:默认为false(0):普通用户 , true(1)表示:管理员账户
	 */
	@Column(name = "admin", length=1)
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param 是否为管理员帐号
	 *            :默认为false(0):普通用户 , true(1)表示:管理员账户
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * 基础信息
	 * 
	 * @return
	 */
	// optional 属性是定义该关联类是否必须存在,值为 false 时,关联类双方都必 须存在,如果关系被维护端不存在,查询的结果为
	// null.值为 true 时关系被维护端可以不存在，
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "baseProfileId")
	public BaseProfile getBaseProfile() {
		return baseProfile;
	}

	/**
	 * 是否为管理员帐号:默认为false普通用户true(1):管理员账户
	 * 
	 * @return
	 */

	/**
	 * @param 基础信息
	 *            baseProfile
	 */
	public void setBaseProfile(BaseProfile baseProfile) {
		this.baseProfile = baseProfile;
	}

	/**
	 * 联系方式
	 * 
	 * @return
	 */
	// optional 属性是定义该关联类是否必须存在,值为 false 时,关联类双方都必 须存在,如果关系被维护端不存在,查询的结果为
	// null.值为 true 时关系被维护端可以不存在，
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "contactProfileId")
	public ContactProfile getContactProfile() {
		return contactProfile;
	}

	/**
	 * @param 联系方式
	 *            contactProfile
	 */
	public void setContactProfile(ContactProfile contactProfile) {
		this.contactProfile = contactProfile;
	}

	/**
	 * 工作情况
	 * 
	 * @return
	 */
	// optional 属性是定义该关联类是否必须存在,值为 false 时,关联类双方都必 须存在,如果关系被维护端不存在,查询的结果为
	// null.值为 true 时关系被维护端可以不存在，
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "workProfileId")
	public WorkProfile getWorkProfile() {
		return workProfile;
	}

	/**
	 * @param 工作情况
	 *            workProfile
	 */
	public void setWorkProfile(WorkProfile workProfile) {
		this.workProfile = workProfile;
	}

	/**
	 * 个人信息
	 * 
	 * @return
	 */
	// optional 属性是定义该关联类是否必须存在,值为 false 时,关联类双方都必 须存在,如果关系被维护端不存在,查询的结果为
	// null.值为 true 时关系被维护端可以不存在，
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "infoProfileId")
	public InfoProfile getInfoProfile() {
		return infoProfile;
	}

	/**
	 * @param 个人信息
	 *            infoProfile
	 */
	public void setInfoProfile(InfoProfile infoProfile) {
		this.infoProfile = infoProfile;
	}

	/**
	 * 教育情况
	 * 
	 * @return
	 */
	// optional 属性是定义该关联类是否必须存在,值为 false 时,关联类双方都必 须存在,如果关系被维护端不存在,查询的结果为
	// null.值为 true 时关系被维护端可以不存在，
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "eduProfileId")
	public EduProfile getEduProfile() {
		return eduProfile;
	}

	/**
	 * @param 教育情况
	 *            eduProfile
	 */
	public void setEduProfile(EduProfile eduProfile) {
		this.eduProfile = eduProfile;
	}

	/**
	 * 统计信息
	 * 
	 * @return
	 */
	// optional 属性是定义该关联类是否必须存在,值为 false 时,关联类双方都必 须存在,如果关系被维护端不存在,查询的结果为
	// null.值为 true 时关系被维护端可以不存在，
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "statisticsProfileId")
	public StatisticsProfile getStatisticsProfile() {
		return statisticsProfile;
	}

	/**
	 * @param 统计信息
	 *            statisticsProfile
	 */
	public void setStatisticsProfile(StatisticsProfile statisticsProfile) {
		this.statisticsProfile = statisticsProfile;
	}

	/**
	 * 实名认证
	 * 
	 * @return
	 */
	// optional 属性是定义该关联类是否必须存在,值为 false 时,关联类双方都必 须存在,如果关系被维护端不存在,查询的结果为
	// null.值为 true 时关系被维护端可以不存在，
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "realNameVerifyId")
	public RealNameVerify getRealNameVerify() {
		return realNameVerify;
	}

	/**
	 * @param 实名认证
	 *            realNameVerify
	 */
	public void setRealNameVerify(RealNameVerify realNameVerify) {
		this.realNameVerify = realNameVerify;
	}
	
	/**
	 * 积分
	 * 
	 * @return
	 */
	// optional 属性是定义该关联类是否必须存在,值为 false 时,关联类双方都必 须存在,如果关系被维护端不存在,查询的结果为
	// null.值为 true 时关系被维护端可以不存在，
	@OneToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "userCreditId")
	public UserCredit getUserCredit() {
		return userCredit;
	}

	/**
	 * @param 积分
	 *            userCredit
	 */
	public void setUserCredit(UserCredit userCredit) {
		this.userCredit = userCredit;
	}
	
	/**
	 * 点评 
	 * @return 
	 */
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="user",fetch=FetchType.LAZY)
	public Set<Critique> getCritiques() {
		return critiques;
	}

	/**
	 * @param 点评 critiques
	 */
	public void setCritiques(Set<Critique> critiques) {
		this.critiques = critiques;
	}

	/**
	 * 评分 
	 * @return 
	 */
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="user",fetch=FetchType.LAZY)
	public Set<Score> getScores() {
		return scores;
	}

	/**
	 * @param 评分 scores
	 */
	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}

	/**
	 * 帖子 
	 * @return 
	 */
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="author",fetch=FetchType.LAZY)
	public Set<Topic> getTopics() {
		return topics;
	}

	/**
	 * @param 帖子 topics
	 */
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	/**
	 * 回复 
	 * @return 
	 */
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="author",fetch=FetchType.LAZY)
	public Set<Reply> getReplys() {
		return replys;
	}

	/**
	 * @param 回复 replys
	 */
	public void setReplys(Set<Reply> replys) {
		this.replys = replys;
	}

	public User() {
	}

	public User(String loginName) {
		this.loginName = loginName;
	}
}
