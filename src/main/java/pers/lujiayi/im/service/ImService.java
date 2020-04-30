package pers.lujiayi.im.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.lujiayi.im.entity.*;
import pers.lujiayi.im.mapper.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImService {

    @Autowired
    private ImGroupMapper imGroupMapper;

    @Autowired
    private ImGroupUserMapper imGroupUserMapper;

    @Autowired
    private ImUserMapper imUserMapper;

    @Autowired
    private ImFriendMapper imFriendMapper;

    @Autowired
    private ImCategoryMapper imCategoryMapper;

    public Res<List<ImGroup>> getPublicGroup() {
        return Res.ok(this.imGroupMapper.selectList(new LambdaQueryWrapper<ImGroup>().eq(ImGroup::getType, "public")), "获取公共群成功!");
    }

    public Res<List<ImGroup>> getGroups(String userId) {
        List<ImGroup> groups = new ArrayList<>();
        QueryWrapper<ImGroupUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<ImGroupUser> imGroupUsers = imGroupUserMapper.selectList(queryWrapper);
        for (ImGroupUser imGroupUser : imGroupUsers) {
            groups.add(this.imGroupMapper.selectById(imGroupUser.getGroupId()));
        }
        return Res.ok(groups, "查询成功");
    }

    public Res getContacts(String userId) {
        Map<String, Object> data = new HashMap<>();
        List<ImFriend> imFriends = this.imFriendMapper.selectList(new LambdaQueryWrapper<ImFriend>().eq(ImFriend::getUserId, userId));
        data.put("friends", CollUtil.isNotEmpty(imFriends) ?
                imUserMapper.selectBatchIds(imFriends.stream().map(ImFriend::getFriendId).collect(Collectors.toList())) :
                new ArrayList<>());
        data.put("groups", this.getGroups(userId).getData());
        return Res.ok(data, "获取通讯录成功!");
    }

    public Res register(ImUser imUser) {
//        Integer count = this.imUserMapper.selectCount(new LambdaQueryWrapper<ImUser>().eq(ImUser::getName, imUser.getName()));
//        if (count > 0) {
//            return Res.failure("该名字已被注册,换一个吧!");
//        }
        if (StringUtils.isEmpty(imUser.getId())) {
            imUser.setId(IdUtil.simpleUUID());
            imUser.setCreateTime(LocalDateTime.now());
            imUser.setStatus(ImUser.OFFLINE);
        }
        int insert1 = this.imUserMapper.insert(imUser);
        if (insert1 != 1) {
            return Res.failure("用户创建失败!");
        }
        ImCategory imCategory = new ImCategory();
        imCategory.setId(IdUtil.simpleUUID());
        imCategory.setUserId(imUser.getId());
        imCategory.setName("我的好友");
        int insert2 = imCategoryMapper.insert(imCategory);
        if (insert2 != 1) {
            return Res.failure("创建好友分类失败!");
        }
        ImFriend imFriend = new ImFriend();
        imFriend.setUserId(imUser.getId());
        imFriend.setFriendId("0");
        int insert3 = this.imFriendMapper.insert(imFriend);
        if (insert3 != 1) {
            return Res.failure("添加管理员好友失败!");
        }
        return Res.ok(imUser, imUser.getName() + " 注册成功!");
    }

    public Res joinGroup(ImGroupUser imGroupUser) {
        if (StringUtils.isEmpty(imGroupUser.getId())) {
            imGroupUser.setId(IdUtil.simpleUUID());
            imGroupUser.setCreateTime(LocalDateTime.now());
        }
        this.imGroupUserMapper.insert(imGroupUser);
        return Res.ok(null, "加入群组成功!");
    }

    public Res<List<ImUser>> getGroupUsers(String groupId) {
        return Res.ok(this.imGroupUserMapper.getGroupUsers(groupId), "获取群组用户成功!");
    }

}
