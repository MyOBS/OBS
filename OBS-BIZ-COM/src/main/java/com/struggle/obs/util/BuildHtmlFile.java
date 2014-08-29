package com.struggle.obs.util;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.struggle.obs.bean.bbs.Topic;

/**
 * 创建帖子的Html页面，存放路径在root/html/topic/${forumId}/${topic.id}.shtml
 * @author tangyh
 *  2014年8月25日 下午10:43:31
 */
public class BuildHtmlFile {
	
	public static void createProductHtml(Topic topic, File saveDir){
		try {
			if(!saveDir.exists()) saveDir.mkdirs();
			VelocityContext context = new VelocityContext();
			context.put("topic", topic);
			Template template = Velocity.getTemplate("topic/topicview.html");
			FileOutputStream outStream = new FileOutputStream(new File(saveDir, topic.getId()+".shtml"));
			OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
			BufferedWriter sw = new BufferedWriter(writer);
			template.merge(context, sw);
			sw.flush();
			sw.close();
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
