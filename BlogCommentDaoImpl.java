package com.js.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.js.model.BlogComment;

@Repository
@Transactional
public class BlogCommentDaoImpl {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addBlogComment(BlogComment blogComment) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.save(blogComment);
	}
	public List<BlogComment> getBlogComment(int blogPostId) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogComment where blogPost=?");
		query.setInteger(0, blogPostId);
		List<BlogComment> blogComments=query.list();
		return blogComments;
	}

	public void deleteBlogComment(BlogComment blogComment) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();		
		session.delete(blogComment);		
	}

}


