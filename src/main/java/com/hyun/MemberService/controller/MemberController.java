package com.hyun.MemberService.controller;

import com.hyun.MemberService.Dto.MemberDTO;
import com.hyun.MemberService.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // 컨트롤러 : 웹 요청을 처리하는 컴포넌트
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    @GetMapping("/Register") // "/Register"이 요청(Get)되면 아래 함수 실행
    public String RegisterForm() {
        return "Register"; // templates 폴더의 Register.html을 실행
    }

    @PostMapping("/Register") // "/Register"이 요청(Post)되면 아래 함수 실행
    public String Register(/*@RequestParam("email") String email,
                       @RequestParam("password") String password,
                       @RequestParam("name") String name*/
                       @ModelAttribute MemberDTO memberDTO) {
        memberService.Register(memberDTO);
        return "Login"; // templates 폴더의 login.html을 실행
    }

    @GetMapping("/Login") // "/login"이 요청(Get)되면 아래 함수 실행
    public String loginForm() {
        return "Login"; // templates 폴더의 login.html을 실행
    }

    @PostMapping("/Login") // "/login"이 요청(Post)되면 아래 함수 실행
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO login = memberService.login(memberDTO);
        if (login != null) {
            session.setAttribute("loginId", login.getId());
            session.setAttribute("loginEmail", login.getEmail());
            session.setAttribute("loginPassword", login.getPassword());
            session.setAttribute("loginName", login.getName());
            return "main"; // templates 폴더의 main.html을 실행
        } else {
            return "Login"; // templates 폴더의 login.html을 실행
        }
    }

    @GetMapping("/MemberList")
    public String getMemberList(Model model) {
        List<MemberDTO> MemberDTOList = memberService.findAllMembers();
        model.addAttribute("memberList", MemberDTOList); // html로 전송할 데이터가 있다면 Model을 사용한다.
        return "list";
    }

    @GetMapping("/member/{id}")
    public String getMemberDetails(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findDetails(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String email = (String)session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.getMyInformations(email);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String updating(@ModelAttribute MemberDTO memberDTO) {
        memberService.updateMemberInformations(memberDTO);
        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/MemberList";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("email") String email) {
        System.out.println("email = " + email);
        String checkResult = memberService.emailCheck(email);
        return checkResult;
    }
}
