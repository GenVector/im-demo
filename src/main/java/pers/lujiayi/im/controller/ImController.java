package pers.lujiayi.im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.lujiayi.im.entity.ImGroupUser;
import pers.lujiayi.im.entity.ImUser;
import pers.lujiayi.im.entity.Res;
import pers.lujiayi.im.service.FileService;
import pers.lujiayi.im.service.ImService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("im")
public class ImController {

    @Autowired
    private ImService imService;

    @Autowired
    private FileService fileService;

    @PostMapping("register")
    public Res register(ImUser imUser) {
        return this.imService.register(imUser);
    }

    @PostMapping("joinGroup")
    public Res joinGroup(ImGroupUser imGroupUser) {
        return this.imService.joinGroup(imGroupUser);
    }

    @GetMapping("getContacts/{userId}")
    public Res getContacts(@PathVariable String userId) {
        return this.imService.getContacts(userId);
    }

    @GetMapping("getPublicGroup")
    public Res getPublicGroup() {
        return this.imService.getPublicGroup();
    }

    @GetMapping("getGroups/{userId}")
    public Res getGroups(@PathVariable String userId) {
        return this.imService.getGroups(userId);
    }

    @GetMapping("getGroupUsers/{groupId}")
    public Res getGroupUsers(@PathVariable String groupId) {
        return this.imService.getGroupUsers(groupId);
    }

    @PostMapping("upload")
    public Res upload(@RequestParam("file") MultipartFile file) {
        if (file == null) {
            return Res.failure("上传文件为空!");
        }
        return fileService.upload(file);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        this.fileService.download(id, response, request.getHeader("User-agent"));
    }

}
