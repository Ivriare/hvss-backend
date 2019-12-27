package entities;

import entities.content.HvssContent;
import entities.content.HvssContentElement;
import entities.content.HvssContentGroup;
import entities.file.HvssFileLocation;
import entities.user.HvssUser;
import entities.user.HvssUserToken;

public enum HvssTypeEnum {
    HvssContent(HvssContent.class),
    HvssContentElement(HvssContentElement.class),
    HvssContentGroup(HvssContentGroup.class),
    HvssFileLocation(HvssFileLocation.class),
    HvssUser(HvssUser.class),
    HvssUserToken(HvssUserToken.class);

    private final Class<?> type;
    private HvssTypeEnum(Class<?> type) {
        this.type = type;
    }

    public Class<?> getTypeClass() {
        return this.type;
    }
}
