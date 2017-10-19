package com.hanbit.cock.api.insert.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hanbit.cock.api.dao.FileDAO;
import com.hanbit.cock.api.insert.dao.CockInsertDAO;
import com.hanbit.cock.api.service.FileService;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.FileVO;
import com.hanbit.cock.api.vo.ImgVO;
import com.hanbit.cock.api.vo.MenuVO;
import com.hanbit.cock.api.vo.RestVO;
import com.hanbit.cock.api.vo.TagVO;

@Service
public class CockInsertService {

	@Autowired
	private CockInsertDAO cockInsertDAO;
	
	@Autowired
	private FileService fileService;
	
	public RestVO getRest(int rid) {
		RestVO restVO = cockInsertDAO.selectRest(rid);
		if (restVO == null) { // restVO가 null 이면 List를 받을 수가 없다.
			restVO = new RestVO();
		}
		
		return restVO;
	}
	
	public RestVO getArticle(int rid, int articleId) {
		RestVO restVO = cockInsertDAO.selectRest(rid);
		ArticleVO article = new ArticleVO();
		article.setRid(rid);
		article.setArticleId(articleId);
		article = cockInsertDAO.selectArticle(article);
		article.setImgs(cockInsertDAO.selectImgs(article));
		
		restVO.setArticles(new ArrayList<ArticleVO>());
		restVO.getArticles().add(article);
		restVO.setMenus(cockInsertDAO.selectMenus(article));
		restVO.setTags(cockInsertDAO.selectTags(article));
		
		return restVO;
	}

	
	private void setRestSave(RestVO rest) {
		rest.setRid(cockInsertDAO.ridGenerate());
		cockInsertDAO.insertRest(rest);
	}

	private void modifyArticle(RestVO rest) {
		ArticleVO newArt = rest.getArticles().get(0);
		
		newArt.setRid(rest.getRid());
		if (newArt.getArticleId() == null) {
			newArt.setArticleId(cockInsertDAO.articleIdGenerate(newArt.getRid()));
		}
		
		cockInsertDAO.modifyArticle(newArt);
	}
	
	/*

	private void modifyArticle(RestVO rest, List<MultipartFile> images) throws Exception {
		ArticleVO article = rest.getArticles().get(0);
		
		List<ImgVO> imgList = null;
		
		imgList = article.getImgs();
		
		for (int i=imgList.size()-1; i>-1; i--) {
			String imgUrl = imgList.get(i).getPath();
			
			if (!"_removed_".equals(imgUrl)) {
				continue;
			}
			
			cockInsertDAO.removeImg(imgList.get(i));
			String removeFileId = StringUtils.substringAfterLast(imgUrl, "/");
			fileService.removeFile(removeFileId);
			imgList.remove(i);
		}
		
		int lastIndex = 0;
		for (ImgVO img : imgList) {
			img.setRid(article.getRid());
			img.setArticleId(article.getRid());
			img.setImgId(lastIndex);
			
			lastIndex++;
		}
		
		for (int i=0; i<images.size(); i++) {
			MultipartFile image = images.get(i);
			
			String fileIndex = article.getRid() + "_" + article.getArticleId() + "_" + (lastIndex + i);
			String fileName = FilenameUtils.removeExtension(image.getOriginalFilename());
			String fileExt = FilenameUtils.getExtension(image.getOriginalFilename());
			String fileId = "art-" + fileName + "-" + fileIndex;
			String filePath = "/hanbit2/webpack/team-1-front/src/img/insert/" + fileId + "." + fileExt;
			
			FileVO fileVO = new FileVO();
			fileVO.setFileId(fileId);
			fileVO.setFilePath(filePath);
			fileVO.setFileName(fileId + "." + fileExt);
			fileVO.setContentType(image.getContentType());
			fileVO.setContentLength(image.getSize());

			System.out.println(ToStringBuilder.reflectionToString(fileVO));
			//fileService.addFile(fileVO, image.getInputStream()); - 보류
			
			String fileUrl = "/api/file/" + fileId;
			
			ImgVO img = new ImgVO();
			img.setImgId(lastIndex + i);
			img.setRid(newArt.getRid());
			img.setArticleId(newArt.getArticleId());
			img.setPath(fileUrl);
			imgList.add(img);
		}
		
		newArt.setImgs(imgList);
		
		//cockInsertDAO.saveArticle(newArt);
		//cockInsertDAO.saveImgs(newArt);
	}*/
	
	@Transactional
	public Map setRestAndArticleSave(RestVO rest, List<MultipartFile> images) throws Exception {
		Map result = new HashMap();		
		// 처리순서 - 1. rest
		//            2. article
		//            3. menu
		//            4. tag
		//			  5. img
		if (rest.getArticles().get(0).getUid() == null) {
			result.put("result", "must login");
			return result;
		}
		
		if (rest.getRid() == null) {
			setRestSave(rest);
			result.put("result", "save Rest");
		}
		modifyArticle(rest);
		result.put("result", "modify Article");
		System.out.println(rest.getArticles().get(0).getArticleId());
		
		if (!rest.getMenus().isEmpty()) {
			setMenusSave(rest);
			result.put("result", "modify Menu");
		}
		
		if (!rest.getTags().isEmpty()) {
			setTagsSave(rest);
			result.put("result", "modify Tag");
		}

		if (rest.getArticles().get(0).getImgs().size() > 0 || images.size() > 0) {
			//saveImgs(rest, images);
		}
		
		result.put("rid", rest.getRid());
		result.put("articleId", rest.getArticles().get(0).getArticleId());
		
		return result;
	}

	private void setTagsSave(RestVO rest) {
		ArticleVO article = rest.getArticles().get(0);
		List<TagVO> tags = rest.getTags();
		int index = 0;
		for (TagVO tag : tags) {
			tag.setRid(article.getRid());
			tag.setArticleId(article.getArticleId());
			tag.setTagId(index);
			index++;
		}
		
		cockInsertDAO.removeTags(article);
		cockInsertDAO.insertTags(rest);
	}

	private void setMenusSave(RestVO rest) {
		ArticleVO article = rest.getArticles().get(0);
		List<MenuVO> menus = rest.getMenus();
		for (MenuVO menu : menus) {
			menu.setRid(article.getRid());
			menu.setArticleId(article.getArticleId());
		}
		
		cockInsertDAO.removeMenus(article);
		cockInsertDAO.insertMenus(rest);
	}
}
