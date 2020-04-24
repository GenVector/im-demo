package pers.lujiayi.im.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.lujiayi.im.entity.Res;
import pers.lujiayi.im.entity.SysFile;
import pers.lujiayi.im.mapper.SysFileMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author Lujiayi
 * @date 2020/4/24
 */
@Service
public class FileService {

    @Value("${file-backup-path}")
    private String fileBackupPath;

    @Autowired
    private SysFileMapper sysFileMapper;

    public Res upload(MultipartFile multipartFile) {
        SysFile sysFile = new SysFile();
        sysFile.setId(IdUtil.simpleUUID());
        sysFile.setName(multipartFile.getOriginalFilename());
        sysFile.setSize(multipartFile.getSize());
        sysFile.setExt(sysFile.getName().substring(sysFile.getName().lastIndexOf(".") + 1));
        sysFile.setCreateTime(LocalDateTime.now());
        sysFileMapper.insert(sysFile);

        File directory = new File(fileBackupPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = fileBackupPath.concat(sysFile.getId()).concat(sysFile.getName().substring(sysFile.getName().lastIndexOf(".")));
        File file = new File(fileName);
        try {
            InputStream is = multipartFile.getInputStream();
            FileOutputStream os = new FileOutputStream(file);
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Res.ok(sysFile, "上传成功");
    }

    public void download(String id, HttpServletResponse response, String userAgent) {
        if (StrUtil.isBlank(id)) {
            try {
                PrintWriter writer = response.getWriter();
                writer.write("错误,文件id为空!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        SysFile sysFile = this.sysFileMapper.selectById(id);
        if (sysFile == null) {
            try {
                PrintWriter writer = response.getWriter();
                writer.write("错误,未找到文件!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String fileName = fileBackupPath.concat(sysFile.getId()).concat(sysFile.getName().substring(sysFile.getName().lastIndexOf(".")));
        try {
            FileInputStream is = new FileInputStream(fileName);
            OutputStream os = response.getOutputStream();
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            byte[] bytes = userAgent.contains("MSIE") ? sysFile.getName().getBytes() : sysFile.getName().getBytes("UTF-8");
            String filenameDisplay = new String(bytes, StandardCharsets.ISO_8859_1);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filenameDisplay));
            response.setHeader("Content-Length", "" + sysFile.getSize());
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
