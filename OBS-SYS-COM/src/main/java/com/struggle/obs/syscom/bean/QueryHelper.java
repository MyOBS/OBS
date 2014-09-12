package com.struggle.obs.syscom.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.struggle.obs.syscom.dao.GenericDao;



/**
 * 用于辅助拼接HQL语句
 * 
 * @author tyg
 * 
 */
public class QueryHelper<T, PK extends Serializable> {

	private String fromClause; // FROM子句
	private String whereClause = ""; // Where子句
	private String orderByClause = ""; // OrderBy子句

	private List<Object> parameters = new ArrayList<Object>(); // 参数列表
	
	
	/**
	 * 生成From子句
	 * 
	 * @param clazz 实体对象
	 * @param alias
	 *            别名
	 */
	public QueryHelper(Class<T> clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}
	
	/**
	 * 拼接Where子句
	 * 
	 * @param condition
	 * @param params
	 */
	public QueryHelper<T, PK> addCondition(String condition, Object... params) {
		// 拼接
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}

		// 参数
		if (params != null) {
			for (Object p : params) {
				parameters.add(p);
			}
		}

		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接Where子句
	 * 
	 * @param append
	 * @param condition
	 * @param params
	 */
	public QueryHelper<T, PK> addCondition(boolean append, String condition, Object... params) {
		if (append) {
			addCondition(condition, params);
		}
		return this;
	}

	/**
	 * 拼接OrderBy子句
	 * 
	 * @param propertyName
	 *            参与排序的属性名
	 * @param asc
	 *            true表示升序，false表示降序
	 */
	public QueryHelper<T, PK> addOrderProperty(String propertyName, boolean asc) {
		if (orderByClause.length() == 0) {
			orderByClause = " ORDER BY " + propertyName + (asc ? " ASC" : " DESC");
		} else {
			orderByClause += ", " + propertyName + (asc ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接OrderBy子句<br>
	 * eg:<br>
	 *   addOrderProperty(true, "t.addDate" ,true):表示 拼接成：AND t.addDate ASC
	 * 
	 * 
	 * @param isappend 是否拼接 true 表示拼接，false则不拼接
	 * @param propertyName 参与排序的属性名
	 * @param asc true表示升序，false表示降序
	 */
	public QueryHelper<T, PK> addOrderProperty(boolean isappend, String propertyName, boolean asc) {
		if (isappend) {
			addOrderProperty(propertyName, asc);
		}
		return this;
	}

	/**
	 * 获取生成的用于查询数据列表的HQL语句
	 * 
	 * @return 
	 */
	public String getListQueryHql() {
		return fromClause + whereClause + orderByClause;
	}

	/**
	 * 获取生成的用于查询总记录数的HQL语句
	 * 
	 * @return 
	 */
	public String getCountQueryHql() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	/**
	 * 获取HQL中的参数值列表
	 * 
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	/**
	 * 查询分页信息
	 * 
	 * @param dao
	 * @param pageNum
	 * @param pageSize
	 */
	public Page<T> preparePageBean(GenericDao<T, PK> dao, int pageNum, int pageSize, int pages) {
		Page<T> page = dao.getPageBean(pageNum, pageSize, pages, this);
		return page;
	}

}
