package com.hanbit.cock.api.vo;

import java.util.List;

import com.hanbit.cock.api.emblem.vo.EmblemVO;

// article_id, rid, comment, status, likes, hate, write_dt
public class ArticleVO {
	private Integer articleId;
	private Integer rid;
	private String comment;
	private String status;
	private int likes;
	private int hate;
	private String writeDt;
	private String uid;
	private String email; // chi
	private String avatar; // chi
	private List<ImgVO> imgs;
	private List<TagVO> tags;
	private List<MenuVO> menus;
	private List<EmblemVO> emblems; // chi

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getHate() {
		return hate;
	}

	public void setHate(int hate) {
		this.hate = hate;
	}

	public String getWriteDt() {
		return writeDt;
	}

	public void setWriteDt(String writeDt) {
		this.writeDt = writeDt;
	}

	public List<MenuVO> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuVO> menus) {
		this.menus = menus;
	}

	public List<TagVO> getTags() {
		return tags;
	}

	public void setTags(List<TagVO> tags) {
		this.tags = tags;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<ImgVO> getImgs() {
		return imgs;
	}

	public void setImgs(List<ImgVO> imgs) {
		this.imgs = imgs;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<EmblemVO> getEmblems() {
		return emblems;
	}

	public void setEmblems(List<EmblemVO> emblems) {
		this.emblems = emblems;
	}

}
