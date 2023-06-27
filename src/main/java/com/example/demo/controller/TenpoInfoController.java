package com.example.demo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.TenpoAddRequest;
import com.example.demo.dto.TenpoSearchRequest;
import com.example.demo.dto.TenpoUpdateRequest;
import com.example.demo.entity.TenpoInfo;
import com.example.demo.service.TenpoInfoService;

/**
 * 店舗情報 Controller
 */
@Controller
public class TenpoInfoController {
	/**
	 * 店舗情報 Service
	**/
	@Autowired
	private TenpoInfoService tenpoInfoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	MultipartFile multiInputStream;
	
	Logger logger = LoggerFactory.getLogger(TenpoInfoController.class);
	
    /**
     * 店舗情報一覧画面を表示
     * @param model Model
     * @return 店舗情報一覧画面
     * @throws UnsupportedEncodingException 
     */
    @GetMapping(value = "/tenpoinfo/list")
    public String displayList(Model model) throws UnsupportedEncodingException {
        List<TenpoInfo> tenpoList = tenpoInfoService.findAll();
        
        StringBuffer data;
        
        String base64 = "";
        
        for(int i=0; i<tenpoList.size(); i++) {
        	//DBから取得したpost_codeを分割してpostal1,postal2に挿入
        	tenpoList.get(i).setPostal1(tenpoList.get(i).getPostCode().substring(0, 3));
        	tenpoList.get(i).setPostal2(tenpoList.get(i).getPostCode().substring(3));
        	
        	if(tenpoList.get(i).getImage() != null) {
        		// base64にエンコードしたものを文字列に変更
        		base64 = new String(Base64.encodeBase64String(tenpoList.get(i).getImage()));
        		// 拡張子をjpegと指定
                // <img ht:src="">で指定できる形にする
        		data = new StringBuffer();
        		data.append("data:image/png;base64,");
        		data.append(base64);
        		tenpoList.get(i).setBanner(data.toString());
        	}
        }
        model.addAttribute("tenpolist", tenpoList);
        model.addAttribute("tenpoSearchRequest", new TenpoSearchRequest());
        return "tenpoinfo/serch";
    }
	
    /**
     * 店舗ログイン画面を表示
     * @param model Model
     * @return 店舗ログイン画面
     */
    @GetMapping(value = "/tenpoinfo/login")
    public String login(Model model) {
        return "tenpoinfo/login";
    }
    
    /**
     * 店舗新規登録画面を表示
     * @param model Model
     * @return 店舗情報一覧画面
     */
    @GetMapping(value = "/tenpoinfo/add")
    public String displayAdd(Model model) {
        model.addAttribute("tenpoAddRequest", new TenpoAddRequest());
        return "tenpoinfo/add";
    }
    
    /**
     * 店舗編集画面を表示
     * @param id ID
     * @param model Model
     * @return 店舗編集画面
     */
    @GetMapping("/tenpoinfo/{id}/edit")
    public String displayEdit(@PathVariable Long id, Model model) {
        TenpoInfo tenpo = tenpoInfoService.findById(id);
        TenpoUpdateRequest tenpoUpdateRequest = new TenpoUpdateRequest();
        tenpoUpdateRequest.setId(tenpo.getId());
        tenpoUpdateRequest.setName(tenpo.getName());
        tenpoUpdateRequest.setPhone(tenpo.getPhone());
        tenpoUpdateRequest.setPostal1(tenpo.getPostCode().substring(0,3));
        tenpoUpdateRequest.setPostal2(tenpo.getPostCode().substring(3));
        tenpoUpdateRequest.setAddress(tenpo.getAddress());
        tenpoUpdateRequest.setTime(tenpo.getTime());
        tenpoUpdateRequest.setUrl(tenpo.getUrl());
        tenpoUpdateRequest.setImageName(tenpo.getImage_name());
        model.addAttribute("tenpoUpdateRequest", tenpoUpdateRequest);
        return "tenpoinfo/edit";
    }
    
    /**
     * 店舗情報検索
     * @param tenpoSearchRequest リクエストデータ
     * @param model Model
     * @return 店舗情報一覧画面
     */
    @RequestMapping(value = "/tenpoinfo/serch", method = RequestMethod.POST)
    public String search(@ModelAttribute TenpoSearchRequest tenpoSearchRequest, Model model) {
        List<TenpoInfo> tenpoList = tenpoInfoService.search(tenpoSearchRequest);
        StringBuffer data;
        
        String base64 = "";
        
        for(int i=0; i<tenpoList.size(); i++) {
        	
        	//DBから取得したpost_codeを分割してpostal1,postal2に挿入
        	tenpoList.get(i).setPostal1(tenpoList.get(i).getPostCode().substring(0, 3));
        	tenpoList.get(i).setPostal2(tenpoList.get(i).getPostCode().substring(3));
        	
        	if(tenpoList.get(i).getImage() != null) {
        		// base64にエンコードしたものを文字列に変更
        		base64 = new String(Base64.encodeBase64String(tenpoList.get(i).getImage()));
        		// 拡張子をjpegと指定
                // <img ht:src="">で指定できる形にする
        		data = new StringBuffer();
        		data.append("data:image/png;base64,");
        		data.append(base64);
        		tenpoList.get(i).setBanner(data.toString());
        	}
        }
        model.addAttribute("tenpolist", tenpoList);
        return "tenpoinfo/serch";
    }
    
    /**
     * 店舗情報削除（論理削除）
     * @param id ID
     * @param model Model
     * @return 店舗情報一覧画面
     */
    @GetMapping("/tenpoinfo/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        // 店舗情報の削除
        tenpoInfoService.delete(id);
        return "redirect:/tenpoinfo/list";
    }
    
    /**
     * 店舗新規登録
     * @param tenpoRequest リクエストデータ
     * @param model Model
     * @return 店舗情報一覧画面
     * @throws IOException 
     */
    @RequestMapping(value = "/tenpoinfo/create", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute TenpoAddRequest tenpoRequest, BindingResult result, Model model) throws IOException {
    	logger.info("image is {}", tenpoRequest.getImage());
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "tenpoinfo/add";
        }
        // tenpoRequestをTenpoinfoクラスに変換
        TenpoInfo info = modelMapper.map(tenpoRequest, TenpoInfo.class);
        
        info.setPostCode(tenpoRequest.getPostal1()+tenpoRequest.getPostal2());
        
        if(!tenpoRequest.getImage().isEmpty()) {
            // フォームに渡されたアップロードファイルを取得
            MultipartFile multipartFile = tenpoRequest.getImage();

            // アップロード実行処理メソッドの呼び出し
            info.setImage(tenpoInfoService.uploadFile(multipartFile));
            info.setImage_name(multipartFile.getOriginalFilename());        	
        }
        // 店舗情報の登録
        tenpoInfoService.save(info);
        return "redirect:/tenpoinfo/list";
    }

    /**
     * 店舗更新
     * @param tenpoRequest リクエストデータ
     * @param model Model
     * @return 店舗情報詳細画面
     */
    @RequestMapping(value = "/tenpoinfo/update", method = RequestMethod.POST)
    public String update(@Validated @ModelAttribute TenpoUpdateRequest tenpoUpdateRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "tenpoinfo/edit";
        }
        // tenpoUpdateRequestをTenpoinfoクラスに変換
        TenpoInfo info = modelMapper.map(tenpoUpdateRequest, TenpoInfo.class);
        
        info.setPostCode(tenpoUpdateRequest.getPostal1()+tenpoUpdateRequest.getPostal2());
        
        if(!tenpoUpdateRequest.getImage().isEmpty()) {
            // フォームに渡されたアップロードファイルを取得
            MultipartFile multipartFile = tenpoUpdateRequest.getImage();
            
            // アップロード実行処理メソッドの呼び出し
            info.setImage(tenpoInfoService.uploadFile(multipartFile));
            info.setImage_name(multipartFile.getOriginalFilename());        	
        }

        // ユーザー情報の更新
        tenpoInfoService.update(info);
        return "redirect:/tenpoinfo/list";
    }
    
}
