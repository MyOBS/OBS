package com.struggle.obs.bbs.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.struggle.obs.bbs.dao.TopicDao;
import com.struggle.obs.bbs.dao.TypeDao;
import com.struggle.obs.bbs.service.TypeService;
import com.struggle.obs.bean.bbs.Type;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;

/**
 * @author tangyh 2014年8月19日 下午11:38:02
 */
@Service
public class TypeServiceImpl implements TypeService {
	@Autowired
	private TypeDao typeDao;
	@Autowired
	private TopicDao topicDao;
	private static Log LOGGER = LogFactory.getLog(TypeServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<Type> findAllNoDel(Long forumId) {
		if (forumId == null) {
			throw new OBSException("查询分类失败");
		}
		try {
			return typeDao.findByForumId(forumId, ConstantDefine.NO_DEL_FLAG);
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("查询分类失败", e);
		}
	}

	/**
	 * 2014年8月24日 上午11:56:46
	 * 
	 * @see com.struggle.obs.bbs.service.TypeService#resetTypeData()
	 */
	@Override
	@Transactional
	public void resetTypeData() {
		try {
			List<Type> types = typeDao
					.findAllByNoDel(ConstantDefine.NO_DEL_FLAG);
			if (types != null) {
				for (Type type : types) {
					if (type != null) {
						int topicCount = topicDao.getTopicCountByTypeId(
								type.getId(), ConstantDefine.NO_DEL_FLAG);
						type.setTopicCount(topicCount);
					}
				}
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
			throw new OBSException("更新分类数据失败", e);
		}
	}
}
