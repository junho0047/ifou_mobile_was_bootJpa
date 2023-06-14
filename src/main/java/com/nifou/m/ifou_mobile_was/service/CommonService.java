package com.nifou.m.ifou_mobile_was.service;

import com.nifou.m.ifou_mobile_was.entity.common.*;
import com.nifou.m.ifou_mobile_was.repository.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommonService {
    @Autowired
    private DashBoard1Repository dashBoard1Repository;
    @Autowired
    private DashBoard2Repository dashBoard2Repository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DepRepository depRepository;
    @Autowired
    private AcqRepository acqRepository;
    @Autowired
    private TidRepository tidRepository;

    @Autowired
    private ColumnsRepository columnsRepository;
    public DashBoard1Entity getDashBoard1(String orgCd, String appdd) {
        DashBoard1Entity dashBoard = dashBoard1Repository.getDashBoard(orgCd, appdd);
        return dashBoard;
    }


    public DashBoard2Entity getDashBoard2(String orgCd, String expDd) {
        DashBoard2Entity dashBoard = dashBoard2Repository.getDashBoard2(orgCd, expDd);
        return dashBoard;
    }

    public ArrayList<MenuEntity> getMenu(String orgCd, String authSeq) {
        ArrayList<MenuEntity> menu = menuRepository.getMenu(orgCd, authSeq);
        return menu;
    }

    public ArrayList<DepEntity> getDep(String orgCd) {
        ArrayList<DepEntity> dep = depRepository.getDep(orgCd);
        return dep;
    }

    public ArrayList<AcqEntity> getAcq() {
        ArrayList<AcqEntity> acq = acqRepository.getAcq();
        return acq;
    }

    public ArrayList<TidEntity> getTid(String orgCd) {
        ArrayList<TidEntity> tid = tidRepository.getTid(orgCd);
        return tid;
    }

    public ArrayList<ColumnsEntity> getTbSysDomain(String orgCd, String pages) {
        ArrayList<ColumnsEntity> columns = columnsRepository.getTbSysDomain(orgCd, pages);
        return columns;
    }
}
