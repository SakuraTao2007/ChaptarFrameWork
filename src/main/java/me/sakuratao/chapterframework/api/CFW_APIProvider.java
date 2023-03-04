package me.sakuratao.chapterframework.api;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CFW_APIProvider {

    @Getter
    private static CFW_API CFW_API;

    public void setCFW_API(CFW_API CFW_API) {
        CFW_APIProvider.CFW_API = CFW_API;
    }

}
