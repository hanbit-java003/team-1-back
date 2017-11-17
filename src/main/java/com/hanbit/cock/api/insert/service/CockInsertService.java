package com.hanbit.cock.api.insert.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hanbit.cock.api.admin.vo.AdminAlertArticleVO;
import com.hanbit.cock.api.annotation.EmblemUpdate;
import com.hanbit.cock.api.insert.dao.CockInsertDAO;
import com.hanbit.cock.api.service.FileService;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.FileVO;
import com.hanbit.cock.api.vo.ImgVO;
import com.hanbit.cock.api.vo.LocationVO;
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
	
	@Transactional
	@EmblemUpdate
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
		
		if (!rest.getMenus().isEmpty()) {
			setMenusSave(rest);
			result.put("result", "modify Menu");
		}
		
		if (!rest.getTags().isEmpty()) {
			setTagsSave(rest);
			result.put("result", "modify Tag");
		}

		if (rest.getArticles().get(0).getImgs().size() > 0 || images.size() > 0) {
			saveImgs(rest, images);
			result.put("result", "save img");
		}
		
		result.put("rid", rest.getRid());
		result.put("articleId", rest.getArticles().get(0).getArticleId());
		result.put("RestVO", rest);
		
		return result;
	}

	private void saveImgs(RestVO rest, List<MultipartFile> images) throws Exception {
		ArticleVO article = rest.getArticles().get(0);
		ArticleVO oldArticle = getArticle(article.getRid(), article.getArticleId()).getArticles().get(0);
		
		int lastIndex = 0;
		if (article.getImgs().size() > 0) {
			String lastFileUrl = oldArticle.getImgs().get(oldArticle.getImgs().size() - 1).getPath();
			lastIndex = Integer.valueOf(StringUtils.substringAfterLast(lastFileUrl, "_")) + 1;
		}
		
		List<ImgVO> list = new ArrayList<>();
		
		int index = 0;
		for (int i=oldArticle.getImgs().size()-1; i>-1; i--) {
			if ("_removed_".equals(article.getImgs().get(i).getPath())) {
				String oldUrl = oldArticle.getImgs().get(i).getPath();
				String oldFileId = StringUtils.substringAfterLast(oldUrl, "/");
				System.out.println(oldUrl);
				fileService.removeFile(oldFileId);
				continue;
			}
			
			ImgVO newImg = new ImgVO();
			newImg.setRid(article.getRid());
			newImg.setArticleId(article.getArticleId());
			//newImg.setImgId(index);
			newImg.setPath(article.getImgs().get(i).getPath());
			
			list.add(newImg);
			index++;
		}
		
		Collections.reverse(list); // 뒤집힌 것 뒤집어주기 - menu입력 순서와 일치해야하기 때문에
		for (int i=0; i<index; i++) {
			list.get(i).setImgId(i);
		}
		
		for (int i=0; i<images.size(); i++) {
			MultipartFile imgFile = images.get(i);
			
			String fileIndex = article.getRid() + "_" + article.getArticleId() + "_" + (lastIndex + i);
			String fileName = FilenameUtils.removeExtension(imgFile.getOriginalFilename());
			String fileExt = FilenameUtils.getExtension(imgFile.getOriginalFilename());
			String fileId = "art-" + fileName + "-" + fileIndex;
			String filePath = "/hanbit2/webpack/team-1-front/src/img/insert/" + fileId + "." + fileExt;
			//String filePath = "/hanbit/webpack/cock-front/src/img/insert/" + fileId + "." + fileExt;
			
			FileVO fileVO = new FileVO();
			fileVO.setFileId(fileId);
			fileVO.setFilePath(filePath);
			fileVO.setFileName(fileId + "." + fileExt);
			fileVO.setContentType(imgFile.getContentType());
			fileVO.setContentLength(imgFile.getSize());
			
			fileService.addFile(fileVO, imgFile.getInputStream());
			
			String fileUrl = "/api/file/" + fileId;
			
			ImgVO newImg = new ImgVO();
			newImg.setRid(article.getRid());
			newImg.setArticleId(article.getArticleId());
			newImg.setImgId(index);
			newImg.setPath(fileUrl);
			
			list.add(newImg);
			index++;
		}
		
		article.setImgs(list);
		cockInsertDAO.removeImgs(article);
		cockInsertDAO.saveImgs(article);
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

	public List<LocationVO> getLocations(LocationVO location) {
		//            +0.003, 0
		//  0, -0.009 <- center -> 0, +0.009
		//            -0.003, 0
		List<LocationVO> locations = cockInsertDAO.selectLocations(location);	
		
		return locations;
	}
	
	public List<MenuVO> getMatchMenuList(int rid, String text) {
		Map map = new HashMap<>();
		map.put("rid", rid);
		map.put("text", text);
		
		return cockInsertDAO.getMatchMenus(map);
	}

	@Transactional
	@EmblemUpdate
	public boolean removeArticle(int rid, int articleId, String uid) {
		ArticleVO art = new ArticleVO();
		
		art.setRid(rid);
		art.setArticleId(articleId);
		art.setUid(uid);
				
		art = cockInsertDAO.selectArticle(art);
		art.setImgs(cockInsertDAO.selectImgs(art));
		
		cockInsertDAO.removeMenus(art);
		cockInsertDAO.removeTags(art);
		for (ImgVO img : art.getImgs()) {
			String oldFileId = img.getPath().replace("/api/file/", "");
			System.out.println("delete file: " + oldFileId);
			fileService.removeFile(oldFileId);
		}
		cockInsertDAO.removeImgs(art);
		cockInsertDAO.removeArticle(art);
		
		return true;
	}

}
