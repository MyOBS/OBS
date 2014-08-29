package com.struggle.obs.bean.base;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.struggle.obs.syscom.bean.BaseModel;

/**
 * @author tangyh
 * @create 2014年8月13日 23:04:07
 * 
 */
@Entity
@Table(name = "menu")
public class Menu extends BaseModel implements java.io.Serializable{

    private static final long serialVersionUID = 1333301726172575354L;
    
    private String name;// 菜单名称
    private String url;//菜单地址
    private String icon;//图标
    
    private Menu parent;
    private Set<Menu> children = new HashSet<Menu>(); // 下级菜单
    
    @Column(name="name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name="url")
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Column(name="icon")
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="parentId")
    public Menu getParent() {
        return parent;
    }
    public void setParent(Menu parent) {
        this.parent = parent;
    }
    
    //fetch=FetchType.EAGER :懒加载，查询一方时，另一方用不用都一起查出来
    @OneToMany(cascade={CascadeType.ALL} , mappedBy="parent" , fetch=FetchType.EAGER)
    @OrderBy("id ASC")
    public Set<Menu> getChildren() {
        return children;
    }
    public void setChildren(Set<Menu> children) {
        this.children = children;
    }
	public Menu() {
	}
    
    
}
