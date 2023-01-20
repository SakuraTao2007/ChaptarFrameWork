package me.sakuratao.storychapterframework.api;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SCFWAPIProvider {

    private static SCFWAPI SCFWAPI;

    public void setSCFWAPI(SCFWAPI SCFWAPI) {
        SCFWAPIProvider.SCFWAPI = SCFWAPI;
    }

}
