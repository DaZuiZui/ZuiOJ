package com.dazuizui.business.Initializer;

import com.dazuizui.basicapi.entry.InitializerData;
import com.dazuizui.basicapi.entry.LanguageCommand;
import com.dazuizui.basicapi.entry.ProgrammingLanguage;
import com.dazuizui.business.mapper.LanguageCommandMapper;
import com.dazuizui.business.mapper.ProgrammingLanguageMapper;
import com.dazuizui.business.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Bryan yang
 * 数据初始化
 */
@Component
public class InitializerApplication {
    @Autowired
    private ProgrammingLanguageMapper programmingLanguageMapper;
    @Autowired
    private LanguageCommandMapper languageCommandMapper;
    @Autowired
    private SystemMapper systemMapper;

    /**
     * 让系统支持gourp by排序
     */
    @PostConstruct
    public void supportGroupSorting(){
       // systemMapper.supportGroupSorting();
        System.out.println("已经支持Mysql8.0支持group by排序");
    }

    /**
     * 语言数据初始化
     */
    @PostConstruct
    public void initializerLanguageData(){
        //获取全部语言
        List<ProgrammingLanguage> allLanguage = programmingLanguageMapper.getAllLanguage();
        for (ProgrammingLanguage programmingLanguage : allLanguage) {
            //语言id
            long id = programmingLanguage.getId();

            //查看命令组
            List<LanguageCommand> languageCommands = languageCommandMapper.selectByLanguageId(id);
            System.err.println(languageCommands);
            /**
             * 将数据库中的程序指令全部搬移到缓存中
             */
            //最初的标记
            int firsttarger = languageCommands.get(0).getStatus();
            List<String> list = new ArrayList<>();
            for (LanguageCommand languageCommand : languageCommands) {
                if (languageCommand.getStatus() == firsttarger){
                    list.add(languageCommand.getCommand());
                }else{
                    //添加命令
                    HashMap<Integer, List<String>> map = InitializerData.langSystem.get(id);
                    if (map == null){
                        map = new HashMap<>();
                    }
                    map.put(firsttarger,new ArrayList<>(list));
                    InitializerData.langSystem.put(id,map);
                    list.clear();
                    list.add(languageCommand.getCommand());
                    firsttarger = languageCommand.getStatus();
                }
            }

            if (!list.isEmpty()){
                HashMap<Integer, List<String>> map = InitializerData.langSystem.get(id);
                map.put(firsttarger,new ArrayList<>(list));
                InitializerData.langSystem.put(id,map);
                list.clear();
            }


        }
        System.out.println(InitializerData.langSystem);
        System.out.println("编程语言环境初始化完成");

    }
}
