package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private static final Logger logger = LogManager.getLogger(MenuController.class);

    private final MenuService menuService;
    private final MessageSource messageSource;

    public MenuController(MenuService menuService, MessageSource messageSource) {

        this.menuService = menuService;
        this.messageSource = messageSource;

    }

    public String findMenuList(Model model) {

        List<MenuDTO> menuList = menuService.findAllMenu();

        model.addAttribute("menuList", menuList);

        return "menu/list";
    }

    public void registPage() {}

    public List<CategoryDTO> findCategoryList() {
        System.out.println("JavaScript 내장 함수인 fetch");
        return menuService.findAllCategory();
    }

    public String registMenu() {

        menuService.registNewMenu(newMenu);

        logger.info("Locale : {}", locale);

//        rAttr.addFlashAttribute("successMessage", "신규 메뉴 등록에 성공하셨습니다.");
        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registMenu", null, locale));

    }

    public String findMenuDetail() {

        MenuDTO menu = menuService.findMenuByCode(code);

        model.addAttribute("menu", menu);

    }

    public String showEditMenuForm() {

        MenuDTO menu = menuService.findMenuByCode(code);

        model.addAttribute("menu", menu);

    }

    public String updateMenu() {

        menuService.updateMenu(menu);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 수정되었습니다.");

    }

    public String deleteMenu() {

        menuService.deleteMenu(code);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 삭제되었습니다.");

    }
}