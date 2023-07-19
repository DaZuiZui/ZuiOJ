package com.dazuizui.business.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dazuizui.business.domain.TeamInfo;
import com.dazuizui.business.domain.User;

import java.util.ArrayList;
import java.util.List;


public class TeamListerner extends AnalysisEventListener<TeamInfo> {

    public List<TeamInfo> list = new ArrayList<>();

    @Override
    public void invoke(TeamInfo teamInfo, AnalysisContext analysisContext) {
        list.add(teamInfo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<TeamInfo> get(){
        return this.list;
    }

    public void clear(){
        this.list.clear();
    }

}
