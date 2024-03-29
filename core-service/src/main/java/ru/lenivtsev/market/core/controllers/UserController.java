//package ru.lenivtsev.market.core.controllers;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import ru.lenivtsev.market.api.dto.UserDto;
//import ru.lenivtsev.market.core.service.RoleService;
//import ru.lenivtsev.market.core.service.UserService;
//
//import javax.persistence.EntityNotFoundException;
//import javax.validation.Valid;
//import java.util.Optional;
//
//@Slf4j
//@Controller
//@RequestMapping("/user")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//    private final RoleService roleService;
//
//    /*@GetMapping
//    public String listPage(@RequestParam Optional<String> usernameFilter, Model model) {
//        if (usernameFilter.isEmpty() || usernameFilter.get().isBlank()) {
//            model.addAttribute("users", userRepository.findAll());
//        } else {
//            model.addAttribute("users", userRepository.usersByUsername("%" + usernameFilter.get() + "%"));
//        }
//        return "user";
//    }*/
//
//    /*@GetMapping
//    public String listPage(
//            @RequestParam(required = false) String usernameFilter,
//            @RequestParam(required = false) String emailFilter,
//            Model model
//    ) {
//        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : "%" + usernameFilter.trim() + "%";
//        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : "%" + emailFilter.trim() + "%";
//        model.addAttribute("users", userRepository.usersByFilter(usernameFilter, emailFilter));
//        return "user";
//    }*/
//
//    @GetMapping
//    public String listPage(
//            @RequestParam(required = false) String usernameFilter,
//            @RequestParam(required = false) String emailFilter,
//            @RequestParam(required = false) Optional<Integer> page,
//            @RequestParam(required = false) Optional<Integer> size,
//            @RequestParam(required = false) Optional<String> sortField,
//            Model model
//    ) {
//        Integer pageValue = page.orElse(1) - 1;
//        Integer sizeValue = size.orElse(3);
//        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");
//        model.addAttribute("users", userService.findAllByFilter(usernameFilter, emailFilter, pageValue, sizeValue, sortFieldValue));
//        return "user";
//    }
//
//    @GetMapping("/{id}")
//    public String form(@PathVariable("id") long id, Model model) {
//        model.addAttribute("roles", roleService.findAll());
//        model.addAttribute("user", userService.findUserDtoById(id)
//                .orElseThrow(() -> new EntityNotFoundException("User not found")));
//        return "user_form";
//    }
//
//    @GetMapping("/new")
//    public String addNewUser(Model model) {
//        model.addAttribute("roles", roleService.findAll());
//        model.addAttribute("user", new UserDto());
//        return "user_form";
//    }
//
//    @DeleteMapping("{id}")
//    public String deleteUserById(@PathVariable long id) {
//        userService.deleteUserById(id);
//        return "redirect:/user";
//    }
//
//    @PostMapping
//    public String saveUser(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "user_form";
//        }
//        if (!user.getPassword().equals(user.getMatchingPassword())) {
//            bindingResult.rejectValue("password", "Password not match");
//            return "user_form";
//        }
//        userService.save(user);
//        return "redirect:/user";
//    }
//
//    @PostMapping("/update")
//    public String updateUser(@ModelAttribute("user") UserDto user) {
//        userService.save(user);
//        return "redirect:/user";
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
//        model.addAttribute("message", e.getMessage());
//        return "not_found";
//    }
//
//
//}
