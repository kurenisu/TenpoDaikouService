package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
	
    /**
     * 店舗情報一覧画面を表示
     * @param model Model
     * @return 店舗情報一覧画面
     */
    @GetMapping(value = "/tenpoinfo/list")
    public String displayList(Model model) {
        List<TenpoInfo> tenpoList = tenpoInfoService.findAll();
        model.addAttribute("tenpolist", tenpoList);
        model.addAttribute("tenpoSearchRequest", new TenpoSearchRequest());
        return "tenpoinfo/search";
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
        tenpoUpdateRequest.setId(tenpo.getId());
        tenpoUpdateRequest.setName(tenpo.getName());
        tenpoUpdateRequest.setPhone(tenpo.getPhone());
        tenpoUpdateRequest.setAddress(tenpo.getAddress());
        model.addAttribute("tenpoUpdateRequest", tenpoUpdateRequest);
        return "tenpoinfo/edit";
    }
    
    /**
     * 店舗情報検索
     * @param tenpoSearchRequest リクエストデータ
     * @param model Model
     * @return 店舗情報一覧画面
     */
    @RequestMapping(value = "/tenpoinfo/search", method = RequestMethod.POST)
    public String search(@ModelAttribute TenpoSearchRequest tenpoSearchRequest, Model model) {
        List<TenpoInfo> tenpoList = tenpoInfoService.search(tenpoSearchRequest);
        model.addAttribute("tenpolist", tenpoList);
        return "tenpoinfo/search";
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
     */
    @RequestMapping(value = "/tenpoinfo/create", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute TenpoAddRequest userRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "tenpoinfo/add";
        }
        // 店舗情報の登録
        tenpoInfoService.save(userRequest);
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
        // ユーザー情報の更新
        tenpoInfoService.update(tenpoUpdateRequest);
        return "redirect:/tenpoinfo/list";
    }
    
}
