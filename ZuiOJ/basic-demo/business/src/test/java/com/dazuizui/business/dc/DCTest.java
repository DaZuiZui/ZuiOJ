package com.dazuizui.business.dc;

import com.dazuizui.business.domain.bo.DcInfoBo;
import com.dazuizui.business.messageQueue.cofnig.MessageSource;
import com.dazuizui.business.service.dc.impl.DcInfoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootTest
public class DCTest {
    @Autowired
    private DcInfoImpl dcInfo;
    @Test
    public void dcTest(){
        DcInfoBo dcInfoBo = new DcInfoBo();
        dcInfoBo.setQuestionId(10101l);
        dcInfo.dcInfo(dcInfoBo);
    }
}
