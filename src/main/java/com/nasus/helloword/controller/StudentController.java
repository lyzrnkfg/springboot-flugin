package com.nasus.helloword.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nasus.helloword.form.Student;
import com.nasus.helloword.service.StudentService;
import jdk.nashorn.internal.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    HttpSession session;

    @GetMapping
    public String index(Model map) throws Exception {
        Student studentForm = new Student();
        map.addAttribute("studentForm", studentForm);
        Optional.ofNullable(studentForm).orElse(studentForm);

        return "student/index";
    }

    @PostMapping("search")
    public String searchStudent(@ModelAttribute Student studentForm, Model model, @RequestParam(value="pageNum",defaultValue="1")Integer pageNum, @RequestParam(value="pageSize",defaultValue="4")Integer pageSize) {
        session.setAttribute("studentForm", studentForm);
        PageHelper.startPage(pageNum, pageSize);
        List<Student> searchResult = studentService.findSearch(studentForm);
        PageInfo<Student> pageInfo = new PageInfo<>(searchResult);
        model.addAttribute("studentList", searchResult);
        model.addAttribute("studentForm", studentForm);
        model.addAttribute("pageInfo", pageInfo);
        return "student/index";
    }

    @GetMapping("search")
    public String searchPageStudent(@ModelAttribute Student studentForm, Model model, @RequestParam(value="pageNum",defaultValue="1")Integer pageNum, @RequestParam(value="pageSize",defaultValue="4")Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> searchResult = studentService.findSearch(studentForm);
        PageInfo<Student> pageInfo = new PageInfo<>(searchResult);
        model.addAttribute("studentList", searchResult);
        model.addAttribute("studentForm", studentForm);
        model.addAttribute("pageInfo", pageInfo);
        return "student/index";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute Student studentForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {


            // 得到全部不合法的字段
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            // 遍历不合法字段
            fieldErrors.forEach(
                    fieldError -> {
                        // 获取不合法的字段名和不合法原因
                        System.out.println("error field is : {} ,message is : {}" + fieldError.getField() + "###" + fieldError.getDefaultMessage());
                    }
            );

        }
        studentService.updateStudents(studentForm);
        List<Student> searchResult = studentService.findSearch(studentForm);
        model.addAttribute("studentList", searchResult);
        model.addAttribute("studentForm", studentForm);
        return "student/index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model map, RedirectAttributes redirectAttributes) {
        Student studentForm = new Student();
        map.addAttribute("studentForm", studentForm);
        if (file.isEmpty()) {
            LOGGER.error("select file");
            return "student/index";
        }

        String fileName = file.getOriginalFilename();
        File destOne = new File(System.getProperty("user.dir") + "/src/main/resources/static/upload/" + fileName);
        try {
            file.transferTo(destOne);
            LOGGER.error("upload ok");
            return "student/index";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        LOGGER.error("upload error");
        return "student/index";
    }

    @PostMapping("/multiUpload")
    public String multiUpload(HttpServletRequest request, Model map) {
        Student studentForm = new Student();
        map.addAttribute("studentForm", studentForm);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "/Users/itinypocket/workspace/temp/";
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                LOGGER.error("上传第" + (i + 1) + "个文件失败");
                return "student/index";
            }
            String fileName = file.getOriginalFilename();
            File dest = new File(System.getProperty("user.dir") + "/src/main/resources/static/upload/" + fileName);
            try {
                file.transferTo(dest);
                LOGGER.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                LOGGER.error(e.toString(), e);
                LOGGER.error("上传第" + (i + 1) + "个文件失败");
                return "student/index";
            }
        }

        LOGGER.info("个文件上传成功");
        return "student/index";

    }

    @PostMapping("/fileDownload")
    public String fileDownload(HttpServletResponse response, Model map) {
        Student studentForm = new Student();
        map.addAttribute("studentForm", studentForm);
        String fileName = "1.jpg";
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/static/upload/" + fileName);
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=\"" + fileName + "\"");
        response.setContentLength((int) file.length());
        byte[] buff = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(file);
            os = response.getOutputStream();
            bis = new BufferedInputStream(fis);
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "student/index";
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "student/index";
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable("id") Integer id){
        return studentService.findStudentById(id);
    }

    /**
     * 随机抛出异常
     */
    private void randomException() throws Exception {
        Exception[] exceptions = { //异常集合
                new NullPointerException(),
                new ArrayIndexOutOfBoundsException(),
                new NumberFormatException(),
                new SQLException()};
        //发生概率
        double probability = 0.75;
        if (Math.random() < probability) {
            //情况1：要么抛出异常
            throw exceptions[(int) (Math.random() * exceptions.length)];
        } else {
            //情况2：要么继续运行
        }

    }

}
