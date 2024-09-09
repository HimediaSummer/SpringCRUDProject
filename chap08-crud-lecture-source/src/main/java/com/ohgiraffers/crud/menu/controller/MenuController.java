package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @GetMapping("/list")
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

    @GetMapping("/detail/{code}")
    public String findMenuDetail(@PathVariable("code") int code,
                                 Model model) {

        MenuDTO menu = menuService.findMenuByCode(code);

        model.addAttribute("menu", menu);

        return "menu/detail";
    }

    @GetMapping("/edit/{code}")
    public String showEditMenuForm(@PathVariable("code") int code,
                                   Model model) {

        MenuDTO menu = menuService.findMenuByCode(code);

        model.addAttribute("menu", menu);

        return "menu/edit";
    }

    @PostMapping("/update")
    public String updateMenu(MenuDTO menu, RedirectAttributes rAttr) {

        menuService.updateMenu(menu);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 수정되었습니다.");

        return "redirect:/menu/detail/" + menu.getCode();
    }

    public String deleteMenu() {

        menuService.deleteMenu(code);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 삭제되었습니다.");

    }
}