package com.zhang.service.imp;

import com.zhang.api.user.ipo.ParamAddMemberLevel;
import com.zhang.api.user.ipo.ParamModMemberLevel;
import com.zhang.api.user.opo.RetMemberLevelObj;
import com.zhang.common.DictCode;
import com.zhang.common.DictDb;
import com.zhang.common.ReturnValue;
import com.zhang.common.ServiceResult;
import com.zhang.dao.BMemberLevelDao;
import com.zhang.pojo.BMemberLevel;
import com.zhang.service.MemberLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public ServiceResult<List<RetMemberLevelObj>> getMemberLevel() {
        ServiceResult<List<BMemberLevel>> rs =
                bMemberLevelDao.getMemberLevel();
        if(rs.getCode() != DictCode.SUCCESS){
            return new ServiceResult<>(DictCode.FAIL,"查询失败");
        }
        List<BMemberLevel> list = rs.getResult();
        List<RetMemberLevelObj> ret = new ArrayList<>();
        for(BMemberLevel b:list){
            RetMemberLevelObj r = new RetMemberLevelObj();
            r.setLevelText(b.getLevelText());
            r.setLevelId(b.getLevelId());
            r.setLevelDistCount(b.getLevelDistCount());
            r.setPointMax(b.getPointMax());
            r.setPointMin(b.getPointMin());
            ret.add(r);
        }
        return new ServiceResult<List<RetMemberLevelObj>>(DictCode.SUCCESS,"查询成功").setResult(ret);
    }

    @Override
    public ServiceResult<BMemberLevel> addMemberLevel(
            ParamAddMemberLevel param) {
        BMemberLevel bMemberLevel = new BMemberLevel();
        bMemberLevel.setLevelName(param.getLevelName());
        bMemberLevel.setLevelDistCount(param.getLevelDistCount());
        bMemberLevel.setLevelText(param.getLevelText());
        bMemberLevel.setPointMin(param.getPointMin());
        bMemberLevel.setPointMax(param.getPointMax());
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
    public ReturnValue updateMemberLevel(ParamModMemberLevel param) {
        BMemberLevel bMemberLevel = new BMemberLevel();
        bMemberLevel.setLevelId(param.getLevelId());
        bMemberLevel.setLevelName(param.getLevelName());
        bMemberLevel.setLevelDistCount(param.getLevelDistCount());
        bMemberLevel.setLevelText(param.getLevelText());
        bMemberLevel.setPointMin(param.getPointMin());
        bMemberLevel.setPointMax(param.getPointMax());
        return bMemberLevelDao.updateMemberLevel(bMemberLevel);
    }
}
