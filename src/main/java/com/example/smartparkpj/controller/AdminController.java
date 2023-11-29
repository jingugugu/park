package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.*;
import com.example.smartparkpj.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminController {
    @Value("${com.example.upload.tempPath}")
    private String uploadTempPath;
    @Value("${com.example.upload.AttractionPath}")
    private String uploadAttractionPath;
    @Value("${com.example.upload.ShopPath}")
    private String uploadShopPath;
    @Value("${com.example.upload.ConveniencePath}")
    private String uploadConveniencePath;


    final private AdminService adminService;

    final private TicketService ticketService;

    final private InquiryService inquiryService;

    final private MemberService memberService;

    private final MailSenderService mailSenderService;


    @GetMapping("")
    public String adminMain(){return "redirect:/admin/main";}

    @GetMapping("/main")
    public void adminMainGet(){}

    @GetMapping("/enter/admin_add_map")
    public void getAdmin_add_map() {
        log.info("GetMapping /enter/admin_add_map ...");
    }

    @GetMapping("/enter/admin_edit_map")
    public void getAdmin_edit_map() {
        log.info("GetMapping /enter/admin_edit_map ...");
    }

    @PostMapping("/enter/addAttraction")
    public String addAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO){

        log.info("addAttraction---------------- : " + attractionDTO + markerDTO);
        int ano = adminService.addAttraction(attractionDTO,markerDTO);

        for (String imgName : attractionDTO.getFileNames()){
            moveFile(imgName,markerDTO.getType(),"A" + ano);
            moveFile("s_" + imgName,markerDTO.getType(),"A" + ano);
        }
        return "redirect:/admin/enter/admin_add_map";

    }

    @PostMapping("/enter/addShop")
    public String addShop(ShopDTO shopDTO, MarkerDTO markerDTO){

        log.info("addShop---------------- : " + shopDTO + markerDTO);
        int sno = adminService.addShop(shopDTO,markerDTO);

        for (String imgName : shopDTO.getFileNames()){
            moveFile(imgName,markerDTO.getType(),"S" + sno);
            moveFile("s_" + imgName,markerDTO.getType(),"S" + sno);
        }
        return "redirect:/admin/enter/admin_add_map";

    }

    @PostMapping("/enter/addConvenience")
    public String addConvenience(ConvenienceDTO convenienceDTO, MarkerDTO markerDTO){

        log.info("addCon------------------: " + convenienceDTO + markerDTO);
        int cno = adminService.addConvenience(convenienceDTO, markerDTO);
        moveFile(convenienceDTO.getCon_img(),markerDTO.getType(),"C" + cno);
        moveFile("s_" + convenienceDTO.getCon_img(),markerDTO.getType(),"C" + cno);
        return "redirect:/admin/enter/admin_add_map";
    }

    @PostMapping("/enter/editAttraction")
    public String editAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO){

        log.info("editAttraction------------------: " + attractionDTO + markerDTO);
        adminService.editAttraction(attractionDTO, markerDTO);
        for (String imgName : attractionDTO.getFileNames()){
            moveFile(imgName,markerDTO.getType(),"A" + attractionDTO.getAno());
            moveFile("s_" + imgName,markerDTO.getType(),"A" + attractionDTO.getAno());
        }

        return "redirect:/admin/enter/admin_edit_map";

    }

    @PostMapping("/enter/editShop")
    public String editShop(ShopDTO shopDTO, MarkerDTO markerDTO){

        log.info("editShop------------------: " + shopDTO + markerDTO);
        adminService.editShop(shopDTO, markerDTO);
        for (String imgName : shopDTO.getFileNames()){
            moveFile(imgName,markerDTO.getType(),"S" + shopDTO.getSno());
            moveFile("s_" + imgName,markerDTO.getType(),"S" + shopDTO.getSno());
        }

        return "redirect:/admin/enter/admin_edit_map";

    }

    @PostMapping("/enter/editConvenience")
    public String editConvenience(ConvenienceDTO convenienceDTO, MarkerDTO markerDTO){
        log.info("editConvenience------------------: " + convenienceDTO + markerDTO);
        adminService.editConvenience(convenienceDTO, markerDTO);
        String imgName = convenienceDTO.getCon_img();
        moveFile(imgName,markerDTO.getType(),"C" + convenienceDTO.getCno());
        moveFile("s_" + imgName,markerDTO.getType(),"C" + convenienceDTO.getCno());


        return "redirect:/admin/enter/admin_edit_map";

    }

    @PostMapping("/enter/removeAttraction")
    public String removeAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO){
        log.info("removeAttraction--------------: " + attractionDTO + markerDTO);
        adminService.removeAttraction(attractionDTO, markerDTO);
        removeFolder(markerDTO.getType(), attractionDTO.getAno()); // 해당 어트랙션 폴더 삭제
        return "redirect:/admin/enter/admin_edit_map";
    }

    @PostMapping("/enter/removeShop")
    public String removeShop(ShopDTO shopDTO, MarkerDTO markerDTO){
        log.info("removeShop--------------: " + shopDTO + markerDTO);
        adminService.removeShop(shopDTO, markerDTO);
        removeFolder(markerDTO.getType(), shopDTO.getSno()); // 해당 어트랙션 폴더 삭제
        return "redirect:/admin/enter/admin_edit_map";
    }

    @PostMapping("/enter/removeConvenience")
    public String removeShop(ConvenienceDTO convenienceDTO, MarkerDTO markerDTO){
        log.info("removeCon--------------: " + convenienceDTO + markerDTO);
        adminService.removeConvenience(convenienceDTO, markerDTO);
        removeFolder(markerDTO.getType(), convenienceDTO.getCno()); // 해당 어트랙션 폴더 삭제
        return "redirect:/admin/enter/admin_edit_map";
    }




    @GetMapping("/ticket/adminTicket")
    public void managerListGet(Model model){
        log.info("managerList GET");
//
//        if(bindingResult.hasErrors()){
//            ticketDTO = ticketDTO.builder().build();
//        }
        model.addAttribute("ticketDTO", ticketService.getAll());
    }

    @GetMapping("/ticket/adminRemoveTicket")
    public String managerListPost(int tno) {
        log.info("managerList POST");
        ticketService.remove(tno);
        return "redirect:/admin/ticket/adminTicket"; // 티켓 리스트 페이지로 리다이렉트
    }

    @GetMapping("/ticket/adminAddTicket")
    public void adminAddTicketGET(){
        log.info("adminAddTicketGE...!!!!");
    }

    @PostMapping("/ticket/adminAddTicket")
    public String adminAddTicket(@Valid TicketDTO ticketDTO,
                                 BindingResult bindingResult, HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes){
        log.info(ticketDTO);

        // 오류가 있을 경우 처리
        if(bindingResult.hasErrors()){
            // 예: redirectAttributes.addFlashAttribute("error", "주문 정보가 올바르지 않습니다.");
            return "redirect:/ticket/add"; // 다시 입력 폼으로 리다이렉트
        }

        // 주문 정보를 서비스에 전달
        ticketService.add(ticketDTO);

        return "redirect:/admin/ticket/adminTicket";
    }


    //파일 이동
    private void moveFile(String imgName,String type, String folderName){
        log.info("moveFile: " + imgName);
        try{
            //임시 경로
            FileInputStream fileInputStream = new FileInputStream(uploadTempPath + File.separator + imgName);

            // 저장될 경로 설정
            String resultPath = "";
            if(type.equals("어트랙션")) {
                resultPath = uploadAttractionPath + File.separator + folderName;
            }
            else if(type.equals("매장")){
                resultPath = uploadShopPath + File.separator + folderName;
            }
            else if(type.equals("편의시설")){
                resultPath = uploadConveniencePath + File.separator + folderName;
            }
            File resultFolder = new File(resultPath);
            if(!resultFolder.exists()){
                resultFolder.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(resultPath + File.separator + imgName);

            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            int i;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
            bufferedInputStream.close();
            bufferedOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();

            Files.delete(Paths.get(uploadTempPath + File.separator + imgName));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // 시설 삭제시 해당 폴더 삭제
    private void removeFolder(String type, int no) {
        String deletePath = "";

        if (type.equals("어트랙션")) {
            deletePath = uploadAttractionPath + File.separator + "A" + no;
        } else if (type.equals("매장")) {
            deletePath = uploadShopPath + File.separator + "S" + no;
        } else {
            deletePath = uploadConveniencePath + File.separator + "C" + no;
        }

        // 삭제할 폴더의 경로로 File 객체를 생성
        File folderToDelete = new File(deletePath);

        // 폴더 내용물을 먼저 삭제
        deleteFolderContents(folderToDelete);

        // 폴더를 삭제
        if (folderToDelete.delete()) {
            System.out.println("폴더가 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("폴더 삭제에 실패했습니다.");
        }
    }

    // 폴더 내용물을 삭제하는 메서드
    private void deleteFolderContents(File folder) {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolderContents(file);
                } else {
                    file.delete();
                }
            }
        }
    }
    
    /* Inquiry(문의) 관련 작업 */

    @GetMapping("/inquiry/inquiryList")
    public void getInquiryAdminList(Model model) {
        log.info("GetAdminList ...");
        List<InquiryDTO> inquiryDTOS = inquiryService.getAdminListAll();
        log.info(inquiryDTOS);
        model.addAttribute("adminInquiryDTO", inquiryService.getAdminListAll());
    }



    @GetMapping("/inquiry/adminRead")
    public void getReadInquiry(InquiryDTO inquiryDTO, Model model) {
        log.info("GetMapping/adminRead...");

        int ino = inquiryDTO.getIno();

        model.addAttribute("inquiryDTO", inquiryService.getOne(ino));
    }

    @GetMapping("/inquiry/adminModify")
    public void getModifyInquiry(InquiryDTO inquiryDTO, Model model) {
        log.info("GetMapping/adminModify ...");

        int ino = inquiryDTO.getIno();

        model.addAttribute("inquiryDTO", inquiryService.getOne(ino));
    }

    @GetMapping("/sendConfirmMail")
    @ResponseBody
    public String sendConfirmMail(String mailTo, HttpSession session) throws Exception {
        String confirmKey = "";

        try {
            if (mailSenderService.sendReplyEmail(mailTo,"Smart_Park Confirm Email", "Your confirmation key: " + confirmKey)) {
                session.setAttribute("reply", confirmKey);
                return "reply true";
            }
            else {
                return "reply false";
            }
        } catch (Exception e) {
            return "reply false";
        }
    }

    @PostMapping("/inquiry/adminModify")
    public String postModifyInquiry(InquiryDTO inquiryDTO, BindingResult bindingResult, Model model) {
        log.info("PostMapping/admin/inquiry/adminModify...");
        log.info(inquiryDTO);

        if (inquiryDTO.getState() == 0) {
            try {
                log.info("/inquiry/adminModify try----------------------------------------------------");
                log.info("메일 샌더 서비스 = " + mailSenderService);
                mailSenderService.sendReplyEmail(inquiryDTO.getEmail_id(), "Smart_Park 문의답변 완료", "문의 하신 글에 답변이 달렸습니다");
                // 이메일 전송 성공 시 처리할 내용
            } catch (Exception e) {
                log.info("/inquiry/adminModify catch----------------------------------------------------");
                // 이메일 전송 실패 시 처리할 내용
            }
        }

        log.info("PostMapping--inquiry---adminModify-----------------------------");
        log.info(inquiryDTO);
        inquiryService.updateAnswer(inquiryDTO);

        return "redirect:/admin/inquiry/adminRead?ino="+inquiryDTO.getIno()+"&mno="+inquiryDTO.getMno();
    }

    @PostMapping("/inquiry/adminRemove")
    public String adminInquiryRemove(int ino, Model model) {
        log.info("PostMapping/admin/inquiry/adminRemove");

        inquiryService.adminRemove(ino);
        return "redirect:/admin/inquiry/inquiryList";
    }

    @GetMapping("/member/management")
    public void MemberList(Model model) {
        List<MemberDTO> memberDTOList = memberService.selectAll();
        log.info("================회원 목록================");
        log.info(memberDTOList.size());
        model.addAttribute("memberDTO", memberDTOList);
    }

    @GetMapping("/member/detailManagement")
    public void getDetailManagement(int mno, Model model) {
        log.info("================회원 탈퇴 상세================");
        log.info(mno);
        model.addAttribute("memberDTO", memberService.selectOne(mno));
    }

    @PostMapping("/member/detailManagement")
    public String postDetailManagement(MemberDTO memberDTO) {
        log.info("================회원 탈퇴 처리================");
        log.info(memberDTO);
        memberService.removeMember(memberDTO);

        return "redirect:/admin/member/management";
    }
}
