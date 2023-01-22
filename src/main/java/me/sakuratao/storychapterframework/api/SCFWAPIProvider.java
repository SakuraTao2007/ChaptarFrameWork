package me.sakuratao.storychapterframework.api;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SCFWAPIProvider {

    @Getter
    private static SCFWAPI SCFWAPI;


    public void setSCFWAPI(SCFWAPI SCFWAPI) {
        SCFWAPIProvider.SCFWAPI = SCFWAPI;
    }

}
