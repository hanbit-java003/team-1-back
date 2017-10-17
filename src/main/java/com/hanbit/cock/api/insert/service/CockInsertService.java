package com.hanbit.cock.api.insert.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hanbit.cock.api.insert.dao.CockInsertDAO;
import com.hanbit.cock.api.service.FileService;
import com.hanbit.cock.api.vo.ArticleVO;
import com.hanbit.cock.api.vo.FileVO;
import com.hanbit.cock.api.vo.ImgVO;
import com.hanbit.cock.api.vo.RestVO;

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

	
	public void setRestSave(RestVO rest) {
		if (Integer.valueOf(rest.getRid()) == null) {
			rest.setRid(cockInsertDAO.ridGenerate());
		}
		//cockInsertDAO.insertRest(rest); 보류
	}

	public void setArticleSave(RestVO rest, List<MultipartFile> images) throws Exception {
		ArticleVO oldArt = null;
		ArticleVO newArt = rest.getArticles().get(0);
		// writeDt - DB에서 NOW()로 입력
		
		newArt.setRid(rest.getRid());
		if (Integer.valueOf(newArt.getArticleId()) == null) {
			newArt.setArticleId(cockInsertDAO.articleIdGenerate(newArt.getRid())); 
		}
		else {
			oldArt = cockInsertDAO.selectArticle(newArt);
		}
		
		List<ImgVO> imgList = null;
		int lastIndex = 0;
		
		if (oldArt == null || oldArt.getImgs() == null) {
			imgList = new ArrayList<>();
		}
		else {
			imgList = cockInsertDAO.selectImgs(newArt);
			
			ImgVO lastImgVO = imgList.get(imgList.size() - 1);
			lastIndex = Integer.valueOf(StringUtils.substringAfterLast(lastImgVO.getPath(), "_"));
			
			for (int i=newArt.getImgs().size()-1; i>-1; i--) {
				String imgUrl = newArt.getImgs().get(i).getPath();
				
				if (!"_removed_".equals(imgUrl)) {
					continue;
				}
				
				String oldUrl = imgList.get(i).getPath();
				String oldFileId = StringUtils.substringAfterLast(oldUrl, "/");
				
				fileService.removeFile(oldFileId);
				imgList.remove(i);
			}
		}
		
		for (int i=0; i<images.size(); i++) {
			MultipartFile image = images.get(i);
			
			String fileName = newArt.getRid() + "_" + newArt.getArticleId() + "_" + (lastIndex + i);
			String fileExt = FilenameUtils.getExtension(image.getOriginalFilename());
			String fileId = "art-" + fileName;
			String filePath = "/hanbit2/webpack/team-1-front/src/img/insert/" + fileName + "." + fileExt;
			
			FileVO fileVO = new FileVO();
			fileVO.setFileId(fileId);
			fileVO.setFilePath(filePath);
			fileVO.setFileName(image.getOriginalFilename());
			fileVO.setContentType(image.getContentType());
			fileVO.setContentLength(image.getSize());
			
			fileService.addFile(fileVO, image.getInputStream());
			
			String fileUrl = "/api/file/" + fileId;
			
			ImgVO img = new ImgVO();
			img.setImgId(lastIndex + i);
			img.setRid(newArt.getRid());
			img.setArticleId(newArt.getArticleId());
			img.setPath(fileUrl);
			imgList.add(img);
		}
		
		newArt.setImgs(imgList);
		
		cockInsertDAO.saveArticle(newArt);
		cockInsertDAO.saveImgs(newArt);
	}
	
	@Transactional
	public void setRestAndArticleSave(RestVO rest, List<MultipartFile> images) throws Exception {
		// 처리순서 - 1. rest
		//            2. article - img
		//            3. menu
		//            4. tag
		if (Integer.valueOf(rest.getRid()) == null) {
			setRestSave(rest);
		}
		
		if (Integer.valueOf(rest.getArticles().get(0).getArticleId()) == null) {
			setArticleSave(rest, images);
		}
		
		if (!rest.getMenus().isEmpty()) {
			//setMenusSave(rest);
		}
		
		if (!rest.getTags().isEmpty()) {
			//setTagsSave(rest);
		}
		
	}
	
	
}
