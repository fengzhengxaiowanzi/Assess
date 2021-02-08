package com.zhang.service.imp;

import com.zhang.common.DictCode;
import com.zhang.common.DictDb;
import com.zhang.common.ReturnValue;
import com.zhang.common.ServiceResult;
import com.zhang.dao.BMemberLevelDao;
import com.zhang.pojo.BMemberLevel;
import com.zhang.service.MemberLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zhang
 * @Description: com.zhang.service.imp
 * @Date：Created in 10:02 2021/2/8
 */
@Service
public class MemberLevelServiceImpl implements MemberLevelService {
    @Resource
    private BMemberLevelDao bMemberLevelDao;
    @Override
    public ServiceResult<List<BMemberLevel>> getMemberLevel() {
        return bMemberLevelDao.getMemberLevel();
    }

    @Override
    public ServiceResult<BMemberLevel> addMemberLevel(BMemberLevel bMemberLevel) {
        return bMemberLevelDao.addMemberLevel(bMemberLevel);
    }

    @Override
    public ReturnValue delMemberLevel(Integer levelId) {
        BMemberLevel bMemberLevel = new BMemberLevel();
        bMemberLevel.setLevelId(levelId);
        bMemberLevel.setStatus(DictDb.Status.UN_NORMAL);
        return bMemberLevelDao.updateMemberLevel(bMemberLevel);
    }

    @Override
    public ReturnValue updateMemberLevel(BMemberLevel bMemberLevel) {
        return bMemberLevelDao.updateMemberLevel(bMemberLevel);
    }
}
