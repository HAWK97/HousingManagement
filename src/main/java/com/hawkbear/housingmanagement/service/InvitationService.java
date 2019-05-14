package com.hawkbear.housingmanagement.service;

import com.github.pagehelper.PageHelper;
import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.data.pojo.House;
import com.hawkbear.housingmanagement.data.pojo.Invitation;
import com.hawkbear.housingmanagement.data.vo.InvitationVo;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.mapper.InvitationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class InvitationService {

    @Resource
    private InvitationMapper invitationMapper;

    @Resource
    private HouseService houseService;

    @Resource
    private ClientService clientService;

    public boolean addInvitation(Long houseId) {
        Example example = new Example(Invitation.class);
        example.createCriteria().andEqualTo("houseId", houseId)
               .andEqualTo("userId", UserHolder.get().getId());
        Invitation invitation = invitationMapper.selectOneByExample(example);
        if (invitation == null) {
            invitation = new Invitation();
            invitation.setCreateTime(new Date());
            invitation.setUpdateTime(new Date());
            invitation.setUserId(UserHolder.get().getId());
            invitation.setHouseId(houseId);
            Long sellerId = houseService.findHouseById(houseId).getSeller();
            invitation.setSellerId(sellerId);
            invitationMapper.insert(invitation);
            return true;
        }
        return false;
    }

    public List<Invitation> findInvitationsByHouseId(Long houseId) {
        Example example = new Example(Invitation.class);
        example.createCriteria().andEqualTo("houseId", houseId);
        return invitationMapper.selectByExample(example);
    }

    public Map<String, Object> getBuyerInvitationList(int page, int size) {
        PageHelper.startPage(page, size);
        Example example = new Example(Invitation.class);
        example.createCriteria().andEqualTo("userId", UserHolder.get().getId());
        List<Invitation> invitationList = invitationMapper.selectByExample(example);
        List<InvitationVo> invitationVoList = new ArrayList<>();
        for (Invitation invitation : invitationList) {
            InvitationVo invitationVo = new InvitationVo();
            invitationVo.setHouseId(invitation.getHouseId());
            House house = houseService.findHouseById(invitation.getHouseId());
            invitationVo.setIntroduce(house.getIntroduce());
            invitationVo.setAddress(house.getAddress());
            User seller = clientService.getUser(invitation.getSellerId());
            invitationVo.setSeller(seller.getNickname());
            invitationVo.setSellerPhone(seller.getPhone());
            invitationVoList.add(invitationVo);
        }
        //总条数
        int totalSize = invitationMapper.selectCountByExample(example);
        log.info("买家邀约总条数:{}", totalSize);
        //总页数
        int totalPage = totalSize % size == 0 ? totalSize / size : totalSize / size + 1;
        log.info("买家邀约总页数:{}", totalPage);
        if (page <= 0) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        log.info("修正买家邀约page参数:{}", page);
        Map<String, Object> res = new HashMap<>();
        res.put("invitationList", invitationVoList);
        res.put("currentPage", page);
        return res;
    }

    public Map<String, Object> getSellerInvitationList(int page, int size) {
        PageHelper.startPage(page, size);
        Example example = new Example(Invitation.class);
        example.createCriteria().andEqualTo("sellerId", UserHolder.get().getId());
        List<Invitation> invitationList = invitationMapper.selectByExample(example);
        List<InvitationVo> invitationVoList = new ArrayList<>();
        for (Invitation invitation : invitationList) {
            InvitationVo invitationVo = new InvitationVo();
            invitationVo.setHouseId(invitation.getHouseId());
            House house = houseService.findHouseById(invitation.getHouseId());
            invitationVo.setIntroduce(house.getIntroduce());
            invitationVo.setAddress(house.getAddress());
            User buyer = clientService.getUser(invitation.getUserId());
            invitationVo.setBuyer(buyer.getNickname());
            invitationVo.setBuyerPhone(buyer.getPhone());
            invitationVoList.add(invitationVo);
        }
        //总条数
        int totalSize = invitationMapper.selectCountByExample(example);
        log.info("卖家邀约总条数:{}", totalSize);
        //总页数
        int totalPage = totalSize % size == 0 ? totalSize / size : totalSize / size + 1;
        log.info("卖家邀约总页数:{}", totalPage);
        if (page <= 0) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        log.info("修正卖家邀约page参数:{}", page);
        Map<String, Object> res = new HashMap<>();
        res.put("invitationList", invitationVoList);
        res.put("currentPage", page);
        return res;
    }

    public void deleteInvitation(Long houseId) {
        Example example = new Example(Invitation.class);
        example.createCriteria().andEqualTo("houseId", houseId);
        invitationMapper.deleteByExample(example);
    }
}
