package com.struggle.obs.oa.web.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.struggle.obs.bbs.service.MenuService;
import com.struggle.obs.bean.base.Menu;

/**
 * 监听器  ： 获取菜单列表
 * @author tangyh
 *
 */
public class InitListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 获取容器与相关的Service对象
        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        MenuService menuService = (MenuService) ac.getBean("menuServiceImpl");

        // 准备数据：findTopList
        List<Menu> topUrlList = menuService.findTopList();
        sce.getServletContext().setAttribute("topUrlList", topUrlList);
//        System.out.println("------------> 已准备数据topPrivilegeList <------------");

        // 准备数据：getAllUrls
//        Collection<String> allUrls = menuService.getAllUrls();
//        sce.getServletContext().setAttribute("allUrls", allUrls);
//        System.out.println("------------> 已准备数据allPrivilegeUrls <------------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }

}
